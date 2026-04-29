package com.hostel.complaint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/api/student/complaints")
    public ResponseEntity<Map<String, Object>> createComplaint(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Complaint saved = complaintService.createComplaint(title, description, category, file);

        return ResponseEntity.ok(Map.of(
                "message", "Complaint created successfully",
                "id", saved.getId(),
                "title", saved.getTitle(),
                "description", saved.getDescription(),
                "category", saved.getCategory(),
                "fileName", saved.getFileName() == null ? "" : saved.getFileName(),
                "status", saved.getStatus(),
                "priority", saved.getPriority()
        ));
    }

    @GetMapping("/api/student/complaints")
    public ResponseEntity<List<Complaint>> getStudentComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @GetMapping("/api/admin/complaints")
    public ResponseEntity<List<Complaint>> getAdminComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @GetMapping("/api/staff/complaints")
    public ResponseEntity<List<Complaint>> getStaffComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }
}
