package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtilities
{

    private static Map<String, String> dateMap = new HashMap<String, String>();

    public static Date parseYYYYMMDD(String yyyyMMdd)
    {
        try
        {
            SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
            return YYYYMMDD.parse(yyyyMMdd);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parseMMDDYYYY(String mmDDyyyy)
    {
        try
        {
            SimpleDateFormat MMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");
            return MMDDYYYY.parse(mmDDyyyy);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String YYYYMMDD(Date date)
    {
        SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        return YYYYMMDD.format(date);
    }

    public static String YYYYMMDDHHMMSS(Date date)
    {
        SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return YYYYMMDDHHMMSS.format(date);
    }

    public static String YYYYMMDD_HHMMSSSSS(Date date)
    {
        SimpleDateFormat YYYYMMDD_HHMMSSSSS = new SimpleDateFormat(
                "yyyyMMdd_HHmmssSSS");
        return YYYYMMDD_HHMMSSSSS.format(date);
    }

    public static String YYYYMMDDHHMMSSSSS(Date date)
    {
        SimpleDateFormat YYYYMMDDHHMMSSSSS = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");
        return YYYYMMDDHHMMSSSSS.format(date);
    }

    public static String DMMMYY(Date date)
    {
        SimpleDateFormat DMMMYY = new SimpleDateFormat("d-MMM-yy");
        return DMMMYY.format(date);
    }

    public static Date DMMMYY(String date) throws ParseException
    {
        SimpleDateFormat DMMMYY = new SimpleDateFormat("d-MMM-yy");
        return DMMMYY.parse(date);
    }

    public static Date DDMMMYY(String date) throws ParseException
    {
        SimpleDateFormat DDMMMYY = new SimpleDateFormat("ddMMMyy");
        return DDMMMYY.parse(date);
    }

    public static String DDMMMYY(Date date) throws ParseException
    {
        SimpleDateFormat DDMMMYY = new SimpleDateFormat("ddMMMyy");
        return DDMMMYY.format(date);
    }

    public static String YYYY_MM_DD(Date date)
    {
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
        return YYYY_MM_DD.format(date);
    }

    public static Date YYYY_MM_DD(String date) throws ParseException
    {
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
        return YYYY_MM_DD.parse(date);
    }

    public static Date MM_DD_YYYY(String date) throws ParseException
    {
        SimpleDateFormat MM_DD_YYYY = new SimpleDateFormat("MM/dd/yyyyy");
        return MM_DD_YYYY.parse(date);
    }

    public static String diffTime(Date start, Date end)
    {
        long l = end.getTime() - start.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        StringBuilder sb = new StringBuilder();
        if (day > 0)
            sb.append(day + "d ");
        if (hour > 0)
            sb.append(hour + "h ");
        if (min > 0)
            sb.append(min + "m ");
        if (s > 0)
            sb.append(s + "s ");
        else
            sb.append("0s ");
        return sb.toString();
    }

    public static String getMonthEndDate(String yyyyMM)
    {
        try
        {
            if (yyyyMM == null)
                return null;
            else if (dateMap.containsKey(yyyyMM))
                return dateMap.get(yyyyMM);

            int year = Integer.parseInt(yyyyMM.substring(0, 4));
            int month = Integer.parseInt(yyyyMM.substring(4, 6));

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);

            SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
            String monthEnd = YYYYMMDD.format(cal.getTime());
            dateMap.put(yyyyMM, monthEnd);

            return monthEnd;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}