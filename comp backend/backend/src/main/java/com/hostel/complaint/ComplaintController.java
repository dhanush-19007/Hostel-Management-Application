package com.hostel.complaint;

import com.hostel.common.ComplaintStatus;
import com.hostel.common.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ComplaintController {

    @PostMapping("/api/student/complaints")
    public ResponseEntity<Map<String, Object>> createComplaint(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return ResponseEntity.ok(Map.of(
                "message", "Complaint created successfully",
                "title", title,
                "description", description,
                "category", category,
                "fileName", file != null ? file.getOriginalFilename() : null,
                "status", ComplaintStatus.OPEN.name(),
                "priority", Priority.MEDIUM.name()
        ));
    }

    @GetMapping("/api/student/complaints")
    public ResponseEntity<List<Map<String, Object>>> studentComplaints() {
        return ResponseEntity.ok(List.of(
                Map.of("id", 1, "title", "Water leakage", "status", "OPEN", "priority", "MEDIUM")
        ));
    }

    @GetMapping("/api/admin/complaints")
    public ResponseEntity<List<Map<String, Object>>> adminComplaints() {
        return ResponseEntity.ok(List.of(
                Map.of("id", 1, "title", "Water leakage", "status", "OPEN", "priority", "MEDIUM", "assignedStaff", "Unassigned")
        ));
    }

    @PutMapping("/api/admin/complaints/{id}/assign")
    public ResponseEntity<Map<String, Object>> assignComplaint(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Complaint assigned", "id", id, "data", request));
    }

    @PutMapping("/api/admin/complaints/{id}/status")
    public ResponseEntity<Map<String, Object>> updateComplaintStatus(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Complaint status updated", "id", id, "data", request));
    }

    @GetMapping("/api/staff/complaints")
    public ResponseEntity<List<Map<String, Object>>> staffComplaints() {
        return ResponseEntity.ok(List.of(
                Map.of("id", 1, "title", "Water leakage", "status", "ASSIGNED")
        ));
    }

    @PutMapping("/api/staff/complaints/{id}/status")
    public ResponseEntity<Map<String, Object>> staffUpdateStatus(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Staff complaint status updated", "id", id, "data", request));
    }
}
