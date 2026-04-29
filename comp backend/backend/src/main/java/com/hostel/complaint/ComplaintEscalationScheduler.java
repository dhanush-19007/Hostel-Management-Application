package com.hostel.complaint;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ComplaintEscalationScheduler {

    @Scheduled(fixedDelay = 900000)
    public void escalateUnresolvedComplaints() {
        System.out.println("Scheduler running: checking unresolved complaints older than 48 hours.");
    }
}
