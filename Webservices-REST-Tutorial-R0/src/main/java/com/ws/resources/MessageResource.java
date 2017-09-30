/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.resources;

import com.ws.model.Message;
import com.ws.model.MessageFilterBean;
import com.ws.model.MessageService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MyPc
 */
@Path("/messages")
public class MessageResource {

	MessageService messageService = MessageService.getMessageService();

	/**
	 * SIMPLE MAPPING-STRING
	 * 
	 * @return
	 * @GET @Produces(MediaType.TEXT_PLAIN) //Choose response
	 *      type-text,json,html,xml etc... public String getMessages(){ return
	 *      "getMesages()"; }
	 */
	/*
	
	*//**
		 * @SuppressWarnings({ "unchecked", "rawtypes" })
		 * 
		 * @GET public List<Message> getMessages( @QueryParam("year") int
		 *      year, @QueryParam("start") int start, @QueryParam("size") int size){
		 *      System.out.println("YEAR:"+year); System.out.println("START:"+start);
		 *      System.out.println("SIZE:"+size);
		 * 
		 *      if(year>0) { return messageService.getMesssagesForYear(year); }
		 *      if(start>=0 && size>0) { return
		 *      messageService.getMesssagesPaginated(start,size); }
		 * 
		 *      return messageService.getMessages();
		 * 
		 *      }
		 */

	// REVISED using @BeanParam
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filter) {

		if (filter.getYear() > 0) {
			return messageService.getMesssagesForYear(filter.getYear());
		}
		if (filter.getStart() >= 0 && filter.getSize() >= 0) {
			return messageService.getMesssagesPaginated(filter.getStart(), filter.getSize());
		}

		return messageService.getMessages();

	}

	@GET
	@Path("/{id}")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	// Do research to clear the bugs when using JSON format-glassfish server bug ;
	// change to tamcat !!!??
	public Message getMessage(@PathParam("id") int id) {
		return messageService.getMessage(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		messageService.addMessage(message);
		return message;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(Message message) {
		messageService.updateMessage(message);
		System.out.println(message);
		return message;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Message removeMessage(@PathParam("id") int id) {
		return messageService.removeMessage(id);
	}

}
