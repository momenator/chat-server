package com.rafdi.chat.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.service.ChatRoomService;

@RestController
public class ChatroomController {
	private ChatRoomService chatRoomService;

	@Autowired
	public void setChatRoomService(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.POST)
	public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom)
			throws InvalidChatRoomException {
		System.out.println(chatRoom.getChatRoomName());
		return chatRoomService.createChatRoom(chatRoom.getChatRoomName());

	}

	@RequestMapping(value = "/chatroom/{name}", method = RequestMethod.GET)
	public ChatRoom getChatRoomDetails(@PathVariable String name)
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException {
		System.out.println(name);
		return chatRoomService.getChatRoom(name);

	}

	@RequestMapping(value = "/chatroom/{name}/messages", method = RequestMethod.GET)
	public List<Message> getMessages(@PathVariable String name)
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException {
		System.out.println(name);
		return chatRoomService.getMessages(name);

	}

	@RequestMapping(value = "/chatroom/{name}/messages", method = RequestMethod.POST)
	public List<Message> sendMessage(@PathVariable String name,
			@RequestBody Message message) throws InvalidChatRoomException,
			InvalidChatRoomRepositoryException, InvalidMessageException {
		System.out.println(name);
		chatRoomService.sendMessage(message.getMessage(), name,
				message.getUser());
		return chatRoomService.getMessages(name);

	}

}
