/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MyPc
 */
public class ProfileService {

	private static Map<String, Profile> profileMap = Database.getProfiles();
	private static ProfileService profileService = null;

	public static ProfileService getProfileService() {
		if (profileService == null) {
			profileService = new ProfileService();
			profileMap.put("Raj", new Profile((profileMap.size() + 1), "Raj", "Rajesh", "Narendran"));
			profileMap.put("Manju", new Profile((profileMap.size() + 1), "Manju", "Manjula", "Nair"));
			profileMap.put("Nandu", new Profile((profileMap.size() + 1), "Nandu", "Nandita", "Nair"));
			profileMap.put("Siddu", new Profile((profileMap.size() + 1), "Siddu", "Sidharth", "Nair"));
			profileMap.put("Neeru", new Profile((profileMap.size() + 1), "Neeru", "Nirupama", "Nair"));
			System.out.println(profileMap.size());
			printAll();
			return profileService;
		} else {
			return profileService;
		}

	}

	private ProfileService() {

	}

	public List<Profile> getProfiles() {
		//Understand and remember the below Map to LIst conversion
		return new ArrayList<Profile>(profileMap.values());
	}

	public Profile getProfile(String profileName) {
		return profileMap.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		System.out.println(profile);
		profile.setId(profileMap.size() + 1);
		profile.setCreated(new Date());
		profileMap.put(profile.getProfileName(), profile);
		System.out.println(profile);
		printAll();
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profileMap.size() == 0) {
			return null;
		}
		System.out.println(profile);
		profileMap.put(profile.getProfileName(), profile);
		printAll();
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profileMap.remove(profileName);
	}

	private static void printAll() {
		for (Map.Entry<String, Profile> entry : profileMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() + ":" + entry.getValue());
		}

	}

}
