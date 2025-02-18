package com.assignment.question;

import com.assignment.question.models.BookingConfirmation;
import com.assignment.question.models.BookingResult;
import com.assignment.question.models.PaymentStatus;
import com.assignment.question.services.*;

import java.time.LocalDate;

// Part 2: Make use of Facade
public class BookingManager {

    // private AvailabilityService availabilityService;
    // private PaymentService paymentService;
    // private NotificationService notificationService;
    // private LoyaltyService loyaltyService;
    // private AccommodationDetailsService accommodationDetailsService;

    private BookingFacade bookingService;

    public BookingManager(AvailabilityService availabilityService, PaymentService paymentService,
                          NotificationService notificationService, LoyaltyService loyaltyService,
                          AccommodationDetailsService accommodationDetailsService) {
        // this.availabilityService = availabilityService;
        // this.paymentService = paymentService;
        // this.notificationService = notificationService;
        // this.loyaltyService = loyaltyService;
        // this.accommodationDetailsService = accommodationDetailsService;

        this.bookingService = new BookingFacade(
            availabilityService,
            paymentService,
            notificationService,
            loyaltyService,
            accommodationDetailsService
        );
    }

    /*
     * OBSERVE: Instead of interacting with the different services directly, we are now making use of
     * a single interface to communicate with the complex system
     */
    public BookingResult bookAccommodation(String userId, String accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        // boolean isAvailable = availabilityService.checkAvailability(accommodationId, checkInDate, checkOutDate);
        boolean isAvailable = this.bookingService.checkAvailability(accommodationId, checkInDate, checkOutDate);

        if (!isAvailable) {
            return BookingResult.notAvailable("Accommodation not available for the given dates");
        }

        // PaymentStatus paymentStatus = paymentService.makePayment(userId, accommodationId);
        PaymentStatus paymentStatus = this.bookingService.makePayment(userId, accommodationId);
        if (paymentStatus != PaymentStatus.SUCCESS) {
            return BookingResult.paymentFailed("Payment failed with status: " + paymentStatus);
        }

        BookingConfirmation confirmation = new BookingConfirmation(userId, accommodationId, checkInDate, checkOutDate);
        // notificationService.sendBookingConfirmation(confirmation);
        this.bookingService.sendBookingNotification(confirmation);

        // loyaltyService.updateLoyaltyPoints(userId, paymentService.calculatePaymentAmount(accommodationId));
        this.bookingService.addLoyaltyPoints(userId, accommodationId);
        
        // accommodationDetailsService.updateAccommodationDetails(accommodationId, checkInDate, checkOutDate);
        this.bookingService.updateAccommodationDetails(accommodationId, checkInDate, checkOutDate);

        return BookingResult.success(confirmation);
    }

}