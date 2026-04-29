package com.hostel.dashboard;

import com.hostel.complaint.ComplaintRepository;
import com.hostel.payment.PaymentRepository;
import com.hostel.room.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashboardController {

    private final RoomRepository roomRepository;
    private final ComplaintRepository complaintRepository;
    private final PaymentRepository paymentRepository;

    public DashboardController(RoomRepository roomRepository,
                               ComplaintRepository complaintRepository,
                               PaymentRepository paymentRepository) {
        this.roomRepository = roomRepository;
        this.complaintRepository = complaintRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/api/admin/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        return ResponseEntity.ok(Map.of(
                "totalRooms", roomRepository.count(),
                "occupiedRooms", roomRepository.findAll().stream().filter(r -> r.getOccupancy() != null && r.getOccupancy() > 0).count(),
                "pendingComplaints", complaintRepository.count(),
                "pendingPayments", paymentRepository.count()
        ));
    }
}
