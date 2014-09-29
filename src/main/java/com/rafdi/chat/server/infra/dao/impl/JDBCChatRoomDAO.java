package com.rafdi.chat.server.infra.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafdi.chat.server.infra.dao.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.user.User;

@Component
public class JDBCChatRoomDAO implements ChatRoomDAO {
	private Connection conn;

	@Autowired
	public JDBCChatRoomDAO(Connection conn) {
		this.conn = conn;

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
		if (chatRoom != null) {
			try {
				List<Message> messagesInChatRoom = getMessages(chatRoom);
				chatRoom.setMessages(messagesInChatRoom);
			} catch (InvalidMessageException e) {
				e.printStackTrace();
			}
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
		String sql = "SELECT text, timestamp, chat_user.id as userID, chat_user.name FROM message inner join chat_user on message.user_id = chat_user.id WHERE chat_room_id=?";
		List<Message> messages = new ArrayList<Message>();
		if (chatRoom == null) {
			return messages;
		}
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, chatRoom.getChatRoomID());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString("text");
				Date date = rs.getDate("timestamp");
				long userID = rs.getLong("userID");
				String userName = rs.getString("name");

				User user = new User();
				user.setName(userName);
				user.setUserID(userID);
				Message message = new Message(text, user);

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
