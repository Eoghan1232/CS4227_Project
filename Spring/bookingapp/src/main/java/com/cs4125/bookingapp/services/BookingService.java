package com.cs4125.bookingapp.services;

import com.cs4125.bookingapp.model.entities.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    String searchBooking(int id);
    List<String> searchAllBookings(int userid);
    String addBooking(Booking b, String discountCode);
    String updateTransaction(Booking b);
    String cancelBooking(Booking b);
}