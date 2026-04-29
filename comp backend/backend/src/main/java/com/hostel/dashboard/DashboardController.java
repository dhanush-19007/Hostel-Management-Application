package com.hostel.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashboardController {

    @GetMapping("/api/admin/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard() {
        return ResponseEntity.ok(Map.of(
                "totalRooms", 120,
                "occupiedRooms", 94,
                "pendingComplaints", 12,
                "escalatedComplaints", 3,
                "pendingPayments", 18
        ));
    }
}
