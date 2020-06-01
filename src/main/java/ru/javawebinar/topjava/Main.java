package ru.javawebinar.topjava;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {

        System.out.format("Hello TopJava Enterprise!");

        LocalDateTime localDateTime = LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0);
        System.out.println(localDateTime);
        LocalTime localTime = LocalTime.of( 10, 0);
        System.out.println(localTime);
        localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        System.out.println(localTime.compareTo(localDateTime.toLocalTime()) + "eqals" + 0);
        localTime = LocalTime.of( 13, 0);
        System.out.println(localTime.compareTo(localDateTime.toLocalTime()) + "biger"+ 1);
        localTime = LocalTime.of( 5, 0);
        System.out.println(localTime.compareTo(localDateTime.toLocalTime()) + "lower"+ -1);
    }
}
