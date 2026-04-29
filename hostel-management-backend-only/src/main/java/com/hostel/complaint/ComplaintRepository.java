package com.hostel.complaint;

import com.hostel.common.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStudentEmail(String studentEmail);
    List<Complaint> findByStatus(ComplaintStatus status);
}
