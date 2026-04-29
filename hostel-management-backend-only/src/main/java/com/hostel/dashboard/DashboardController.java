package com.hostel.dashboard;

import com.hostel.complaint.ComplaintRepository;
import com.hostel.payment.PaymentRepository;
import com.hostel.room.RoomRepository;
import com.hostel.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final RoomRepository roomRepository;
    private final ComplaintRepository complaintRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public DashboardController(RoomRepository roomRepository,
                               ComplaintRepository complaintRepository,
                               PaymentRepository paymentRepository,
                               UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.complaintRepository = complaintRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/staff/dashboard")
    public ResponseEntity<Map<String, Object>> getStaffDashboard() {
        return ResponseEntity.ok(Map.of(
                "totalRooms", roomRepository.count(),
                "totalStudents", userRepository.count(),
                "totalComplaints", complaintRepository.count(),
                "totalPayments", paymentRepository.count()
        ));
    }
}
