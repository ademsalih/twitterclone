package ademsalih.softwarearch.frontend.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeFormatService {

    private final Calendar now = Calendar.getInstance();

    public TimeFormatService() {

    }

    public String formatTimeAgo(Calendar inputCal) {

        long secOld = inputCal.getTime().getTime() / 1000;

        long secNow = now.getTime().getTime() / 1000;

        long diff = secNow - secOld;

        if (diff == 0) {
            return "now";
        } else if (diff < 60) {
            return diff + "s";

        } else if ((diff/60) < 60) {
            diff/=60;
            return diff + "m";

        } else if (diff/60/60 < 24) {
            diff = diff/60/60;
            return diff + "h";

        } else if (diff/60/60/24 < 7) {
            diff = diff/60/60/24;
            return diff + "d";
        }

        Date date = inputCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        return sdf.format(date);
    }

    public String formatJoinDate(String input) {



        String date = input.split("T")[0];


        String year = date.split("-")[0];
        int month = Integer.valueOf(date.split("-")[1]);

        String returnVal = "";

        switch (month) {
            case 1:
                returnVal = "January";
                break;
            case 2:
                returnVal = "February";
                break;
            case 3:
                returnVal = "March";
                break;
            case 4:
                returnVal = "April";
                break;
            case 5:
                returnVal = "May";
                break;
            case 6:
                returnVal = "June";
                break;
            case 7:
                returnVal = "July";
                break;
            case 8:
                returnVal = "August";
                break;
            case 9:
                returnVal = "September";
                break;
            case 10:
                returnVal = "October";
                break;
            case 11:
                returnVal = "November";
                break;
            case 12:
                returnVal = "December";
                break;
            default:
                    break;

        }


        return returnVal + ", " + year;
    }

}
