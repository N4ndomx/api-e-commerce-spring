package com.nandomx.travelUno.util;

import java.time.LocalDateTime;
import java.util.Random;

public class TravelOneUtil {

    private static final Random RANDOM = new Random();

    public static LocalDateTime getDateSoon() {
        var randomHours = RANDOM.nextInt(5-2)+2;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }

    public static LocalDateTime getDateDateLater() {
        var randomHours = RANDOM.nextInt(12-6)+6;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }
}
