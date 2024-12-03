package com.github.shashi.lld.linkedin;

import java.util.List;

public class Profile {
    private String headline;
    private String about;
    private String gender;
    private List<Byte> profilePicture;
    private List<Byte> coverPhoto;

    private List<Experience> experiences;
    private List<Education> educations;
    private List<Skill> skills;
    private List<Achievement> achievements;
    private List<Recommendation> recommendations;
    private Analytics analytics;

    public boolean addExperience(Experience experience) {
        return false;
    }

    public boolean addEducation(Education education) {
        return false;
    }

    public boolean addSkill(Skill skill) {
        return false;
    }

    public boolean addAchievement(Achievement achievement) {
        return false;
    }
}