package com.hostel.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/student/payments")
    public ResponseEntity<List<PaymentRecord>> getStudentPayments(@RequestParam String email) {
        return ResponseEntity.ok(paymentService.getPaymentsByStudentEmail(email));
    }

    @GetMapping("/staff/payments")
    public ResponseEntity<List<PaymentRecord>> getStaffPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}
