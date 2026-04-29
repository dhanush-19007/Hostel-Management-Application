package com.hostel.room;

import com.hostel.room.dto.RoomPreferenceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/admin/rooms")
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @PostMapping("/student/room-preferences")
    public ResponseEntity<Map<String, Object>> allocateRoom(@RequestBody RoomPreferenceRequest request) {
        return ResponseEntity.ok(roomService.allocateRoom(request));
    }

    @GetMapping("/student/room-details")
    public ResponseEntity<Map<String, Object>> getStudentRoomDetails(@RequestParam String email) {
        return ResponseEntity.ok(roomService.getStudentRoomDetails(email));
    }
}
