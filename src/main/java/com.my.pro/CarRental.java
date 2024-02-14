package com.my.pro;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

public class CarRental {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y H:m");
        ArrayList<RentalTime> rentalTimes = new ArrayList<>();

        rentalTimes.add(new RentalTime(sdf.parse("03/05/2020 19:00"), sdf.parse("03/05/2020 20:29")));
        rentalTimes.add(new RentalTime(sdf.parse("03/05/2020 20:30"), sdf.parse("03/05/2020 22:00")));
        rentalTimes.add(new RentalTime(sdf.parse("03/05/2020 22:10"), sdf.parse("03/05/2020 22:30")));

        System.out.println(CarRental.canScheduleAll(rentalTimes));
    }

    public static Boolean canScheduleAll(Collection<RentalTime> rentalTimeCollections) {
        List<RentalTime> sortedCollection = rentalTimeCollections
                .stream()
                .sorted(Comparator.comparing(RentalTime::getStart))
                .collect(Collectors.toUnmodifiableList());

        return IntStream.range(0, sortedCollection.size() - 1)
                .allMatch(i -> sortedCollection.get(i).getEnd().before(sortedCollection.get(i + 1).getStart()));
    }

    @Getter
    @AllArgsConstructor
    static class RentalTime {
        private final Date start;
        private final Date end;
    }
}