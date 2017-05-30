package com.ruchitiwari.github.util;

/**
 * Created by Ruchi Tiwari on 29-05-2017.
 */

public abstract class AgeCalculate {
    protected abstract void getResult(String result);

    public AgeCalculate() {
    }

    public void calculateAGE(Year dateOfBirth, Year ageAtTheDateOf) {
        if (ageAtTheDateOf.year < dateOfBirth.year) {
            getResult("dateOfBirth can no be greater than ageAtTheDateOf.");
        }//Date of birth needs to be earlier than the age at date.

        long years = ageAtTheDateOf.year - (dateOfBirth.year + 1);
        long months = years * 12;
        long days = 0;

        //Calculate the days.
        for (int index = 0; index < years; index++) {
            int year = dateOfBirth.year + index;
            if (!isLeapYear(year)) {
                days += 365;
            } else {
                days += 366;
            }
        }

        long monthInclude;
        if (ageAtTheDateOf.month == 0) {
            monthInclude = (12 - dateOfBirth.month);
        } else {
            monthInclude = (12 - dateOfBirth.month) + (ageAtTheDateOf.month - 1);
        }


        long dayInclude = (daysInPriorMonth(dateOfBirth.month, dateOfBirth.year) - dateOfBirth.day) + (ageAtTheDateOf.day);

        for (int index = 12; index > dateOfBirth.month; index--) {
            days += daysInPriorMonth(index, dateOfBirth.year);
        }

        for (int index = 1; index < ageAtTheDateOf.month; index++) {
            days += daysInPriorMonth(index, dateOfBirth.year);
        }

        days += dayInclude;

        String age;
        age = "YOUR AGE IS " + years + " Years " + monthInclude + " Months and " +
                dayInclude + " Days(" + (daysInPriorMonth(dateOfBirth.month, dateOfBirth.year) - dateOfBirth.day) + " Days of " + dateOfBirth.month + "/" + dateOfBirth.year
                + " and " + ageAtTheDateOf.day + " Days of " + ageAtTheDateOf.month + "/" + ageAtTheDateOf.year + ")";
        age += "\n";
        age += "AGE IN DAYS : " + days + " Days.";
        age += "\n";
        age += "AGE IN HOURS : " + (days * 24) + " Hours.";
        age += "\n";
        age += "AGE IN SECONDS : " + ((days * 24) * 60) + " Minutes.";
        age += "\n";
        age += "AGE IN MILLISECONDS : " + (((days * 24) * 60) * 1000) + " Milli Seconds.";

        getResult(age);
    }

    public void calculateDOB(Year date, Year ageAt) {
        if (!isValidateNumber(date.day, 0, 31, "Day of cal") ||
                !isValidateNumber(date.year, 0, 2999, "Year of cal") ||
                !isValidateNumber(ageAt.year, 0, 1000, "Years aged") ||
                !isValidateNumber(ageAt.month, 0, 11, "Months aged") ||
                !isValidateNumber(ageAt.day, 0, 30, "Days aged")) {
            return;
        }

//	Year result1 = diffDateAgeAndOutput(date.year, date.month, date.day, ageAt.year, ageAt.month, ageAt.day, 1); // Assume 30-day months
        Year result2 = diffDateAgeAndOutput(date.year, date.month, date.day, ageAt.year, ageAt.month, ageAt.day, 2); // Borrow from month before death
//	Year result3 = diffDateAgeAndOutput(date.year, date.month, date.day, ageAt.year, ageAt.month, ageAt.day, 3); // Days alive in birth and death months

//	getResult(result1.toString());
        getResult(result2.toString());
//	getResult(result3.toString());
    }

    private boolean isValidateNumber(int num, int min, int max, String msg) {
        if (num < min || max < num) {
            getResult(msg + " not in range [" + min + "..." + max + "]");
            return false;
        }
        return true;
    }

    private Year diffDateAgeAndOutput(int yd, int md, int dd, int ya, int ma, int da, int method) {
        int db = dd - da,
                mb = md - ma,
                yb = yd - ya;

        if (db < 1) {
// Multiple ways to handle this!
            switch (method) {
                case 1: //ASSUME30:
// 1. can always borrow 30 days.
                    db += 30;
                    break;

                case 2: //BORROW:
// 2. Can borrow number of days in previous month.
                    db += daysInPriorMonth(md, yd);
//	  Will possible leave -1 or -2 for number of days
                    if (db < 1) {
// can still be negative if borrowed from February
                        md -= 1;
                        if (md < 0) {
                            md = 11;
                            yd -= 1;
                        }
                        db += daysInPriorMonth(md, yd);
                        mb -= 1;
                    }
                    break;

                case 3://EXACT:
// 3. can count days alive in birth month plus days alive in death month, -1
                    db += daysInPriorMonth(mb, yb);
                    if (db < 1) {
                        mb -= 1;
                        if (mb < 0) {
                            mb = 11;
                            yd -= 1;
                        }
                        db += daysInPriorMonth(mb, yb);
                    }
                    break;
            }
            mb -= 1;
        }
        if (mb < 0) {
// borrow 12 months from year of death
            mb += 12;
            yb -= 1;
        }

        return new Year(db, mb, yb);
    }

    private boolean isLeapYear(int year) {
        String x = String.valueOf((double) year / 4);
        return x.contains(".");
    }

    private int daysInPriorMonth(int month, int year) {
        if (month == 1) //January
        {
            return 31;
        }

        if (month == 2) //February
        {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }

        if (month == 3) //March
        {
            return 31;
        }

        if (month == 4) //April
        {
            return 30;
        }

        if (month == 5) //may
        {
            return 31;
        }

        if (month == 6) //June
        {
            return 30;
        }

        if (month == 7) //July
        {
            return 31;
        }

        if (month == 8) //august
        {
            return 31;
        }

        if (month == 9) // September
        {
            return 30;
        }

        if (month == 10) //October
        {
            return 31;
        }

        if (month == 11) //November
        {
            return 30;
        }

        if (month == 12) //December
        {
            return 31;
        }

        return 0;
    }
}
