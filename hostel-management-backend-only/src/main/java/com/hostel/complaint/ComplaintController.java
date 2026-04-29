package com.hostel.complaint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/student/complaints")
    public ResponseEntity<Map<String, Object>> createComplaint(
            @RequestParam String email,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Complaint saved = complaintService.createComplaint(email, title, description, category, file);

        return ResponseEntity.ok(Map.of(
                "message", "Complaint created successfully",
                "id", saved.getId(),
                "studentEmail", saved.getStudentEmail(),
                "studentName", saved.getStudentName(),
                "title", saved.getTitle(),
                "description", saved.getDescription(),
                "category", saved.getCategory(),
                "fileName", saved.getFileName() == null ? "" : saved.getFileName(),
                "status", saved.getStatus(),
                "priority", saved.getPriority()
        ));
    }

    @GetMapping("/student/complaints")
    public ResponseEntity<List<Complaint>> getStudentComplaints(@RequestParam String email) {
        return ResponseEntity.ok(complaintService.getComplaintsByStudent(email));
    }

    @GetMapping("/staff/complaints")
    public ResponseEntity<List<Complaint>> getStaffComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/staff/complaints/{id}/resolve")
    public ResponseEntity<Complaint> resolveComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.resolveComplaint(id));
    }

    @PutMapping("/staff/complaints/{id}/dismiss")
    public ResponseEntity<Complaint> dismissComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.dismissComplaint(id));
    }
}
