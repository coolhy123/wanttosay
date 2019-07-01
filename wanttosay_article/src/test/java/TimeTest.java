import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by heyong
 * 2019/7/1
 */


public class TimeTest {
    public static void main(String[] args) throws Exception {
        Date time=new Date();
        Calendar calendar= new GregorianCalendar();
        calendar.setTime(time);

        int year = calendar.YEAR;
        int month =  calendar.MONTH;
        int day = calendar.DATE;
        int hh=calendar.HOUR;
        int mm=calendar.MINUTE;
        int ss = calendar.SECOND;
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(hh);
        System.out.println(mm);
        System.out.println(ss);
        System.out.println(""+year+month+day+hh+mm+ss);

    }
}
