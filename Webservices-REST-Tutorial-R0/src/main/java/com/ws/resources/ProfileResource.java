/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.resources;

import com.ws.model.Profile;
import com.ws.model.ProfileService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MyPc
 */
@Path("/profiles")
public class ProfileResource {
	
	private static ProfileService profileService;
	
   static { 
   profileService=ProfileService.getProfileService();   
   }


    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getProfiles(){        
    return profileService.getProfiles();
    }
    
    @GET
    @Path("/{profileName}")
    @Produces(MediaType.APPLICATION_XML)
    //Do research to clear the bugs when using JSON format-glassfish server bug ; change to tamcat !!!??
    public Profile getProfile(@PathParam("profileName") String profileName){
        return profileService.getProfile(profileName);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Profile addProfile(Profile profile){
        profileService.addProfile(profile);
        return profile;
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Profile updateProfile(Profile profile){
        profileService.updateProfile(profile);
        System.out.println(profile);
        return profile;
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{profileName}")
    public Profile removeProfile(@PathParam("profileName") String key){
    	return profileService.removeProfile(key);
    }
    
}
