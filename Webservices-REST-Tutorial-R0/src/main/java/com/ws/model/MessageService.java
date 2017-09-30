/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ws.exceptions.DataNotFoundException;
import com.ws.exceptions.ErrorCode;

/**
 *
 * @author MyPc
 */
public class MessageService {

	private static Map<Integer, Message> messageMap = Database.getMessages();
	private static MessageService messageService = null;

	public static MessageService getMessageService() {
		if (messageService == null) {
			messageService = new MessageService();
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Hi how are you", "Rajesh"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Howdy", "James"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Howdy", "Sandy"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Good evening", "Sandy"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Hi how are you", "Rajesh"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Howdy", "James"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Howdy", "Sandy"));
			messageMap.put((messageMap.size() + 1), new Message((messageMap.size() + 1), "Good evening", "Sandy"));
			System.out.println(messageMap.size());
			printAll();
			return messageService;
		} else {
			return messageService;
		}

	}

	private MessageService() {

	}

	public List<Message> getMessages() {
		/**
		 * SIMPLE - HARDCODED IMPLEMENTATION Message m1=new Message(100, "Hi how are
		 * you", "Rajesh"); Message m2=new Message(101, "Howdy", "James"); List
		 * messageList=new ArrayList<Message>(); messageList.add(m1);
		 * messageList.add(m2); return messageList;
		 */
		return new ArrayList<Message>(messageMap.values());
	}

	// FIND MESSAGES FOR SPECIFIC YEAR
	public List<Message> getMesssagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messageMap.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}

	// PAGINATE MESSAGES
	public List<Message> getMesssagesPaginated(int start, int size) {
		List<Message> messageList = new ArrayList<>(messageMap.values());
		System.out.println(messageList.size());
		if ((start + size) <= messageList.size())
			return messageList.subList(start, (start + size));
		return messageList.subList(start, messageList.size());
	}

	/*
	 * //METHOD UPDATED TO INCLUDE CUSTOM EXCEPTION HANDLING METHODS public Message
	 * getMessage(int id) { Message message=messageMap.get(id); if(message==null) {
	 * //THROW CUSTOM EXCEPTION throw new
	 * DataNotFoundException("Message with ID: "+id+" not found"); } return
	 * messageMap.get(id); }
	 */

	// METHOD UPDATED TO INCLUDE JAX RS IN-BUILT EXCEPTION WITH CUSTOME ERROR
	// MESSAGE
	public Message getMessage(int id) {
		ErrorCode errorMessage = new ErrorCode("Message NOT found");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage.getErrorMessage()).build();
		Message message = messageMap.get(id);
		if (message == null) {
			// THROW IN-BUILT EXCEPTION WITH CUSTOM ERROR MESSAGE
			throw new WebApplicationException(response);
		}
		return messageMap.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messageMap.size() + 1);
		message.setCreated(new Date());
		messageMap.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (messageMap.size() == 0) {
			return null;
		}
		message.setCreated(new Date());
		messageMap.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(int id) {
		return messageMap.remove(id);
	}

	private static void printAll() {
		for (Map.Entry<Integer, Message> entry : messageMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}

	}

}
