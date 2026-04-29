package com.hostel.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentRecord, Long> {
    List<PaymentRecord> findByStudentEmail(String studentEmail);
}
