/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MyPc
 */
@XmlRootElement
public class Message {
    
    private int id;
    private String message;
    private Date created;
    private String author;

    public Message() {
    }

    public Message(int id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", message=" + message + ", created=" + created + ", author=" + author + '}';
    }
    
}
