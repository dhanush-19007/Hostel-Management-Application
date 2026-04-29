package com.hostel.complaint;

import com.hostel.common.ComplaintStatus;
import com.hostel.common.Priority;
import com.hostel.user.User;
import com.hostel.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintService(ComplaintRepository complaintRepository, UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    public Complaint createComplaint(String email, String title, String description, String category, MultipartFile file) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Complaint complaint = new Complaint();
        complaint.setStudentEmail(user.getEmail());
        complaint.setStudentName(user.getFullName());
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setCategory(category);
        complaint.setFileName(file != null ? file.getOriginalFilename() : null);
        complaint.setPriority(Priority.MEDIUM);
        complaint.setStatus(ComplaintStatus.OPEN);
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getComplaintsByStudent(String email) {
        return complaintRepository.findByStudentEmail(email);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint resolveComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(ComplaintStatus.RESOLVED);
        return complaintRepository.save(complaint);
    }

    public Complaint dismissComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(ComplaintStatus.DISMISSED);
        return complaintRepository.save(complaint);
    }
}
