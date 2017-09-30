package com.ws.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import com.ws.model.Message;

public class RestClient {

	static Client client;
	static List<Message> messageList;
	static WebTarget baseTarget;
	static WebTarget allMessagesTarget;
	static WebTarget singleMessageTarget=null;

	static Builder builder;
	static String url = "http://localhost:8181/Webservices-REST-Tutorial-R0/webapi/";

	public static void main(String[] args) {
		client = ClientBuilder.newClient();
		baseTarget = client.target(url);
		allMessagesTarget = baseTarget.path("messages");
		singleMessageTarget = allMessagesTarget.path("{id}");
		// SINGLE MESSGE BY ID
		getMessageById(8);

		// Add a message -@POST
		Message message = new Message(10, "HOWDY", "Rajesh");
		addMessage(message);

		// UPDATE a message -@PUT
		message = new Message(10, "GOOD MORNING", "Rajesh");
		updateMessage(message);

		// DELETE MESSAGE-BY ID
		deleteMessage(10);

		// GET List<Messages> for condition
		getMessagesForYear(2017);

		// GET List<Messages> for PAGINATION condition
		getMessagesPaginated(5, 2);

	}

	private static void getMessagesPaginated(int start, int size) {
		messageList = allMessagesTarget.queryParam("start", start).queryParam("size", size)
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Message>>() {
				});
		for (Message message : messageList) {
			System.out.println(message);
		}

	}

	// DELETE MESSAGE BY ID
	private static void deleteMessage(int id) {
		Message message = singleMessageTarget.resolveTemplate("id", id).request().delete(Message.class);
		System.out.println(message);
	}

	private static void updateMessage(Message message) {
		Response putResponse = allMessagesTarget.request().post(Entity.json(message));
		String putMessage = putResponse.readEntity(Message.class).getMessage();
		System.out.println("Message UPDATED(@PUT) as :" + putMessage);

	}

	static void getMessageById(int id) {

		/*
		 * builder=target.request(); Response response=builder.get(); Message
		 * message=response.readEntity(Message.class);
		 * 
		 * //Direct injection of class type in GET method Message
		 * message=builder.get(Message.class); System.out.println(message);
		 */

		// Detailed type request
		Message message = singleMessageTarget.resolveTemplate("id", id).request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println(message);

	}

	static void getMessagesForYear(int year) {
		// GETTING LIST<MESSSAGE> FROM THE REST SERVER
		messageList = allMessagesTarget.queryParam("year", year).request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>() {
				});
		for (Message message : messageList) {
			System.out.println(message);
		}

	}

	// POST METHOD
	static void addMessage(Message message) {
		Response postResponse = allMessagesTarget.request().post(Entity.json(message));
		System.out.println("STATUS: " + postResponse.getStatus());
		String returnMessage = postResponse.readEntity(Message.class).getMessage();
		System.out.println("MESSAGE CREATED (@POST) AS :" + returnMessage);

	}

}
