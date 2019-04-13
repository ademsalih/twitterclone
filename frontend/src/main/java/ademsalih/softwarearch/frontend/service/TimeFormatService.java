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

    public String format(Calendar inputCal) {

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

}
