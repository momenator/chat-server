package com.rafdi.chat.server.infra.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rafdi.chat.server.infra.dao.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.user.User;

public class JDBCChatRoomDAO implements ChatRoomDAO {
	private Connection conn;

	public JDBCChatRoomDAO(String name, String password)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/chat_server_db", name, password);

	}

	@Override
	public ChatRoom getChatRoom(String chatRoomName) {
		String sql = "SELECT id,chatroom_name FROM chat_room WHERE chatroom_name = ?";
		ChatRoom chatRoom = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, chatRoomName.toString());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String currentChatRoomName = rs.getString("chatroom_name");
				long id = rs.getLong("id");
				chatRoom = new ChatRoom(currentChatRoomName);
				chatRoom.setChatRoomID(id);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			List<Message> messagesInChatRoom = getMessages(chatRoom);
			chatRoom.setMessages(messagesInChatRoom);
		} catch (InvalidMessageException e) {
			e.printStackTrace();
		}

		return chatRoom;
	}

	@Override
	public void saveChatRoom(ChatRoom chatRoom) {
		String sql = "INSERT INTO `chat_server_db`.`chat_room`(`chatroom_name`) VALUES (?);";
		if (getChatRoom(chatRoom.getChatRoomName()) == null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, chatRoom.getChatRoomName());
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveMessage(ChatRoom chatRoom, Message message) {
		String sql = "INSERT INTO chat_server_db.message ( text,"
				+ "timestamp,user_id,chat_room_id) VALUES " + " (?, ?, ?, ?);";

		long ChatRoomId = chatRoom.getChatRoomID();
		long userId = message.getUser().getUserID();
		String currentMessage = message.getMessage();
		Date date = message.getTimestamp();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, currentMessage);
			stmt.setDate(2, new java.sql.Date(date.getTime()));
			stmt.setLong(3, userId);
			stmt.setLong(4, ChatRoomId);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private List<Message> getMessages(ChatRoom chatRoom)
			throws InvalidMessageException {
		String sql = "SELECT text, timestamp, chat_user.name FROM message inner join chat_user on message.user_id = chat_user.id WHERE chat_room_id=?";
		List<Message> messages = new ArrayList<Message>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, chatRoom.getChatRoomID());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString("text");
				Date date = rs.getDate("timestamp");
				String userName = rs.getString("name");
				Message message = new Message(text, new User(userName, null));
				messages.add(message);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
}
