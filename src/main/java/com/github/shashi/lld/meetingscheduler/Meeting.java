package com.github.shashi.lld.meetingscheduler;

import java.util.List;

public class Meeting {
    private int id;
    private int participantsCount;
    private List<User> participants;
    private Interval interval;
    private MeetingRoom room;
    private String subject;

    public void addParticipants(List<User> participants) {

    }
}