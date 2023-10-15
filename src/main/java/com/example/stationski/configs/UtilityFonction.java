package com.example.stationski.configs;

import java.time.LocalDate;
import java.time.Period;

public class UtilityFonction {

    private UtilityFonction() {
        throw new AssertionError("This class cannot be instantiated.");
    }

     public static int calculateAge(LocalDate dob)
    {

        LocalDate curDate = LocalDate.now();
        if (dob != null)
        {
            return Period.between(dob, curDate).getYears();
        }
        else
        {
            return 0;
        }
    }
}
