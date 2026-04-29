package com.hostel.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    @GetMapping("/api/student/payments")
    public ResponseEntity<List<Map<String, Object>>> studentPayments() {
        return ResponseEntity.ok(List.of(
                Map.of("id", 1, "amount", 5000, "dueDate", "2026-05-10", "status", "UNPAID")
        ));
    }

    @GetMapping("/api/admin/payments")
    public ResponseEntity<List<Map<String, Object>>> adminPayments() {
        return ResponseEntity.ok(List.of(
                Map.of("id", 1, "studentName", "Demo Student", "amount", 5000, "dueDate", "2026-05-10", "status", "UNPAID")
        ));
    }

    @PostMapping("/api/admin/payments")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of("message", "Payment record created", "data", request));
    }
}
