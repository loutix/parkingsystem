package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {


    public static final int HOUR_IN_MILLISECONDS = 60 * 60 * 1000;

    public void calculateFare(Ticket ticket) {
        this.calculateFare(ticket, false);
    }

    public void calculateFare(Ticket ticket, boolean isDiscount) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        double duration = (double) (outHour - inHour) / HOUR_IN_MILLISECONDS;

        // free under 30mn (0,5 Hour)
        duration = duration < Fare.FREE_HOUR ? 0 : duration;
        // if isDiscount == true  Fare * 0.95 else Fare * 1
        double discountFare = !isDiscount ? 1 : Fare.DISCOUNT_FIDELITY;

        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * discountFare);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * discountFare);
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown Parking Type");
        }
    }
}