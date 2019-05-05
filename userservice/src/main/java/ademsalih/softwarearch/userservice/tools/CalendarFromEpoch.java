package ademsalih.softwarearch.userservice.tools;

import java.util.Calendar;
import java.util.Date;

public class CalendarFromEpoch {

    private long epoch;

    public CalendarFromEpoch(long epoch) {
        this.epoch = epoch;
    }

    public Calendar get() {
        Date dateTime = new Date((long)epoch*1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return calendar;
    }



}
