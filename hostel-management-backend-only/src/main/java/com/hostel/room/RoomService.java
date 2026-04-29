package com.hostel.room;

import com.hostel.room.dto.RoomRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomRequestRepository roomRequestRepository;

    public RoomService(RoomRepository roomRepository, RoomRequestRepository roomRequestRepository) {
        this.roomRepository = roomRepository;
        this.roomRequestRepository = roomRequestRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public RoomRequestEntity createRoomRequest(RoomRequestDto dto) {
        RoomRequestEntity request = new RoomRequestEntity();
        request.setRoomType(dto.getRoomType());
        request.setBlockName(dto.getBlock());
        request.setPreference(dto.getPreference());
        request.setStatus("PENDING");
        return roomRequestRepository.save(request);
    }

    public List<RoomRequestEntity> getAllRoomRequests() {
        return roomRequestRepository.findAll();
    }
}
