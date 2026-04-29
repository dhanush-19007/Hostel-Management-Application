package com.hostel.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/api/student/payments")
    public ResponseEntity<List<PaymentRecord>> getStudentPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/api/admin/payments")
    public ResponseEntity<List<PaymentRecord>> getAdminPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}
