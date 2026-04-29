package com.hostel.room.dto;

public class RoomPreferenceRequest {
    private String email;
    private String roomType;
    private Integer preferredFloor;
    private String roommatePreference;
    private String specialPreference;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getPreferredFloor() {
        return preferredFloor;
    }

    public void setPreferredFloor(Integer preferredFloor) {
        this.preferredFloor = preferredFloor;
    }

    public String getRoommatePreference() {
        return roommatePreference;
    }

    public void setRoommatePreference(String roommatePreference) {
        this.roommatePreference = roommatePreference;
    }

    public String getSpecialPreference() {
        return specialPreference;
    }

    public void setSpecialPreference(String specialPreference) {
        this.specialPreference = specialPreference;
    }
}
