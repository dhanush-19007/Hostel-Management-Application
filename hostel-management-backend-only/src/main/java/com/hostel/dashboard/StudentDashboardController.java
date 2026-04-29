package com.hostel.dashboard;

import com.hostel.complaint.ComplaintService;
import com.hostel.payment.PaymentService;
import com.hostel.room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentDashboardController {

    private final RoomService roomService;
    private final ComplaintService complaintService;
    private final PaymentService paymentService;

    public StudentDashboardController(RoomService roomService,
                                      ComplaintService complaintService,
                                      PaymentService paymentService) {
        this.roomService = roomService;
        this.complaintService = complaintService;
        this.paymentService = paymentService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();
        response.put("roomDetails", roomService.getStudentRoomDetails(email));
        response.put("complaints", complaintService.getComplaintsByStudent(email));
        response.put("payments", paymentService.getPaymentsByStudentEmail(email));
        return ResponseEntity.ok(response);
    }
}
