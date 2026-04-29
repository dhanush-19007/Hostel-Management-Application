package com.hostel.room;

import com.hostel.room.dto.RoomRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/api/admin/rooms")
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @PostMapping("/api/student/room-requests")
    public ResponseEntity<Map<String, Object>> requestRoom(@RequestBody RoomRequestDto requestDto) {
        RoomRequestEntity saved = roomService.createRoomRequest(requestDto);
        return ResponseEntity.ok(Map.of(
                "message", "Room request submitted successfully",
                "id", saved.getId(),
                "roomType", saved.getRoomType(),
                "block", saved.getBlockName(),
                "preference", saved.getPreference(),
                "status", saved.getStatus()
        ));
    }

    @GetMapping("/api/student/room-requests")
    public ResponseEntity<List<RoomRequestEntity>> studentRequests() {
        return ResponseEntity.ok(roomService.getAllRoomRequests());
    }
}
