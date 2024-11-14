package com.github.shashi.lld.meetingscheduler;

import java.util.List;

public class MeetingScheduler {
    private User organizer;
    private Calendar calendar;
    private List<MeetingRoom> rooms;

    // Scheduler is a singleton class that ensures it will have only one active instance at a time
    private static MeetingScheduler scheduler = null;

    // Created a static method to access the singleton instance of Scheduler class
    public static MeetingScheduler getInstance() {
        if (scheduler == null) {
            scheduler = new MeetingScheduler();
        }
        return scheduler;
    }

    public boolean scheduleMeeting(List<User> users, Interval interval) {
        return false;
    }

    public boolean cancelMeeting(List<User> users, Interval interval) {
        return false;
    }

    public boolean bookRoom(MeetingRoom room, int numberOfPersons, Interval interval) {
        return false;
    }

    public boolean releaseRoom(MeetingRoom room, Interval interval) {
        return false;
    }

    public MeetingRoom checkRoomsAvailability(int numberOfPersons, Interval interval) {
        return null;
    }
}