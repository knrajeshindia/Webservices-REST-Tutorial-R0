/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MyPc
 */
public class Database {
    private static Map<Integer,Message> messages=new HashMap<>();
    private static Map<String,Profile> profiles=new HashMap<>();
    
    public static Map<String,Profile> getProfiles(){
        return profiles;
    }
    public static Map<Integer,Message> getMessages(){
        return messages;
    }
    //DUMMY FOR TESTING
//    public static void main(String[] args) {
//        System.out.println(messages.size());
//        System.out.println(profiles.size());
//    }
}
