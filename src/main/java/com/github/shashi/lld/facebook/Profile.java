package com.github.shashi.lld.facebook;

import java.util.List;

public class Profile {
    private String gender;
    private byte[] profilePicture;
    private byte[] coverPhoto;
    private List<User> friends;

    private List<Integer> usersFollowed;
    private List<Integer> pagesFollowed;
    private List<Integer> groupsJoined;

    private List<Work> workExperience;
    private List<Education> educationInfo;
    private List<Place> places;

    public boolean addWorkExperience(Work work) {
        return false;
    }

    public boolean addEducation(Education education) {
        return false;
    }

    public boolean addPlace(Place place) {
        return false;
    }

    public boolean addProfilePicture(byte[] image) {
        return false;
    }

    public boolean addCoverPhoto(byte[] image) {
        return false;
    }

    public boolean addGender(String gender) {
        return false;
    }
}