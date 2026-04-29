package com.hostel.user;

import com.hostel.common.AllocationStatus;
import com.hostel.common.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean firstLogin = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AllocationStatus allocationStatus = AllocationStatus.NOT_ALLOCATED;

    private String allocatedRoomNumber;
    private String allocatedBlock;
    private Integer allocatedFloor;
    private String roommatePreference;
    private String preferredRoomType;
    private Integer preferredFloor;
    private String specialPreference;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public AllocationStatus getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(AllocationStatus allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public String getAllocatedRoomNumber() {
        return allocatedRoomNumber;
    }

    public void setAllocatedRoomNumber(String allocatedRoomNumber) {
        this.allocatedRoomNumber = allocatedRoomNumber;
    }

    public String getAllocatedBlock() {
        return allocatedBlock;
    }

    public void setAllocatedBlock(String allocatedBlock) {
        this.allocatedBlock = allocatedBlock;
    }

    public Integer getAllocatedFloor() {
        return allocatedFloor;
    }

    public void setAllocatedFloor(Integer allocatedFloor) {
        this.allocatedFloor = allocatedFloor;
    }

    public String getRoommatePreference() {
        return roommatePreference;
    }

    public void setRoommatePreference(String roommatePreference) {
        this.roommatePreference = roommatePreference;
    }

    public String getPreferredRoomType() {
        return preferredRoomType;
    }

    public void setPreferredRoomType(String preferredRoomType) {
        this.preferredRoomType = preferredRoomType;
    }

    public Integer getPreferredFloor() {
        return preferredFloor;
    }

    public void setPreferredFloor(Integer preferredFloor) {
        this.preferredFloor = preferredFloor;
    }

    public String getSpecialPreference() {
        return specialPreference;
    }

    public void setSpecialPreference(String specialPreference) {
        this.specialPreference = specialPreference;
    }
}
