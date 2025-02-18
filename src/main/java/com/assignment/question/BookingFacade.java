package com.assignment.question;

import java.time.LocalDate;

import com.assignment.question.models.BookingConfirmation;
import com.assignment.question.models.PaymentStatus;
import com.assignment.question.services.AccommodationDetailsService;
import com.assignment.question.services.AvailabilityService;
import com.assignment.question.services.LoyaltyService;
import com.assignment.question.services.NotificationService;
import com.assignment.question.services.PaymentService;

// Part 1: Define Facade - Provides a unified interface to a set of interfaces in a complex system
public class BookingFacade {
    // subsystem classes object references
    private AvailabilityService availabilityService;
    private PaymentService paymentService;
    private NotificationService notificationService;
    private LoyaltyService loyaltyService;
    private AccommodationDetailsService accommodationDetailsService;
    
    // CTOR - DI (Dependency Injection)
    public BookingFacade(
        AvailabilityService availabilityService,
        PaymentService paymentService,
        NotificationService notificationService,
        LoyaltyService loyaltyService,
        AccommodationDetailsService accommodationDetailsService) {
            this.availabilityService = availabilityService;
            this.paymentService = paymentService;
            this.notificationService = notificationService;
            this.loyaltyService = loyaltyService;
            this.accommodationDetailsService = accommodationDetailsService;
    }

    // Define simple interfaces
    public Boolean checkAvailability(String accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return this.availabilityService.checkAvailability(accommodationId, checkInDate, checkOutDate);
    }

    public PaymentStatus makePayment(String userId, String accommodationId) {
        return this.paymentService.makePayment(userId, accommodationId);
    }

    public void sendBookingNotification(BookingConfirmation bookingConfirmation) {
        this.notificationService.sendBookingConfirmation(bookingConfirmation);
    }

    public void addLoyaltyPoints(String userId, String accommodationId) {
        this.loyaltyService.updateLoyaltyPoints(userId, this.paymentService.calculatePaymentAmount(accommodationId));
    }

    public void updateAccommodationDetails(String accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        this.accommodationDetailsService.updateAccommodationDetails(accommodationId, checkInDate, checkOutDate);
    }
}
