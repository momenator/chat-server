package com.rafdi.chat.server.infra.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafdi.chat.server.infra.dao.UserDAO;
import com.rafdi.chat.server.model.user.User;

@Component
public class JDBCUserDAO implements UserDAO {
	private Connection conn;

	@Autowired
	public JDBCUserDAO(Connection conn) {
		this.conn = conn;

	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO `chat_server_db`.`chat_user`(`name`,`password`)VALUES (?,?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setBytes(2, user.getPassword());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User get(Object userName) {
		String sql = "SELECT id,name,password FROM chat_user WHERE name = ?";
		User user = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName.toString());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				byte[] password = rs.getBytes("password");
				long userId = rs.getLong("id");
				user = new User(name, password);
				user.setUserID(userId);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
}
