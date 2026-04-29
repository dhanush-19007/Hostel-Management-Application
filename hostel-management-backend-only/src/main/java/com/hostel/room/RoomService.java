package com.hostel.room;

import com.hostel.common.AllocationStatus;
import com.hostel.room.dto.RoomPreferenceRequest;
import com.hostel.user.User;
import com.hostel.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Map<String, Object> allocateRoom(RoomPreferenceRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!user.isFirstLogin()) {
            throw new RuntimeException("Room preferences already submitted and room already allocated");
        }

        user.setPreferredRoomType(request.getRoomType());
        user.setPreferredFloor(request.getPreferredFloor());
        user.setRoommatePreference(request.getRoommatePreference());
        user.setSpecialPreference(request.getSpecialPreference());

        Room allocatedRoom = findBestAvailableRoom(request.getRoomType(), request.getPreferredFloor());
        if (allocatedRoom == null) {
            throw new RuntimeException("No rooms available for the given preferences");
        }

        allocatedRoom.setOccupancy(allocatedRoom.getOccupancy() + 1);
        roomRepository.save(allocatedRoom);

        user.setAllocatedRoomNumber(allocatedRoom.getRoomNumber());
        user.setAllocatedBlock(allocatedRoom.getBlockName());
        user.setAllocatedFloor(allocatedRoom.getFloorNumber());
        user.setAllocationStatus(AllocationStatus.ALLOCATED);
        user.setFirstLogin(false);
        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Room allocated successfully");
        response.put("studentEmail", user.getEmail());
        response.put("studentName", user.getFullName());
        response.put("roomNumber", allocatedRoom.getRoomNumber());
        response.put("block", allocatedRoom.getBlockName());
        response.put("floor", allocatedRoom.getFloorNumber());
        response.put("roomType", allocatedRoom.getRoomType());
        response.put("roommatePreference", user.getRoommatePreference());
        response.put("specialPreference", user.getSpecialPreference());
        response.put("allocationStatus", user.getAllocationStatus());
        response.put("firstLogin", user.isFirstLogin());
        return response;
    }

    private Room findBestAvailableRoom(String roomType, Integer preferredFloor) {
        List<Room> preferredRooms = roomRepository.findByRoomTypeAndFloorNumberOrderByOccupancyAsc(roomType, preferredFloor);
        for (Room room : preferredRooms) {
            if (room.getOccupancy() < room.getCapacity()) {
                return room;
            }
        }

        List<Room> typeRooms = roomRepository.findByRoomTypeOrderByOccupancyAsc(roomType);
        for (Room room : typeRooms) {
            if (room.getOccupancy() < room.getCapacity()) {
                return room;
            }
        }

        List<Room> allRooms = roomRepository.findAllByOrderByOccupancyAsc();
        for (Room room : allRooms) {
            if (room.getOccupancy() < room.getCapacity()) {
                return room;
            }
        }

        return null;
    }

    public Map<String, Object> getStudentRoomDetails(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("studentEmail", user.getEmail());
        response.put("studentName", user.getFullName());
        response.put("firstLogin", user.isFirstLogin());
        response.put("allocationStatus", user.getAllocationStatus());
        response.put("allocatedRoomNumber", user.getAllocatedRoomNumber() == null ? "" : user.getAllocatedRoomNumber());
        response.put("allocatedBlock", user.getAllocatedBlock() == null ? "" : user.getAllocatedBlock());
        response.put("allocatedFloor", user.getAllocatedFloor() == null ? 0 : user.getAllocatedFloor());
        response.put("preferredRoomType", user.getPreferredRoomType() == null ? "" : user.getPreferredRoomType());
        response.put("preferredFloor", user.getPreferredFloor() == null ? 0 : user.getPreferredFloor());
        response.put("roommatePreference", user.getRoommatePreference() == null ? "" : user.getRoommatePreference());
        response.put("specialPreference", user.getSpecialPreference() == null ? "" : user.getSpecialPreference());
        return response;
    }
}