package com.hostel.room;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoomController {

    @GetMapping("/api/admin/rooms")
    public ResponseEntity<List<Map<String, Object>>> getRooms() {
        return ResponseEntity.ok(List.of(
            Map.of("id", 1, "roomNumber", "A-101", "capacity", 3, "occupancy", 2, "blockName", "Block A")
        ));
    }

    @PostMapping("/api/admin/rooms")
    public ResponseEntity<Map<String, Object>> createRoom(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Room created", "data", request));
    }

    @PutMapping("/api/admin/rooms/{id}")
    public ResponseEntity<Map<String, Object>> updateRoom(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Room updated", "id", id, "data", request));
    }

    @PostMapping("/api/student/room-requests")
    public ResponseEntity<Map<String, Object>> requestRoom(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Room request submitted", "data", request));
    }

    @GetMapping("/api/student/room-requests")
    public ResponseEntity<List<Map<String, Object>>> studentRequests() {
        return ResponseEntity.ok(List.of(Map.of("id", 1, "requestedRoomType", "DOUBLE", "status", "PENDING")));
    }
}
