package com.zjl.comp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhoujl
 * @date 2020/4/2
 */
public class DateUtil {
    private static final int TIME_ZONE_OFFSET = Calendar.getInstance().getTimeZone().getRawOffset();
    public static final String SDF_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.sss";

    public DateUtil() {

    }

    public static Date add(Date date, int type, int offset) {
        if (date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(type, offset);
            return cal.getTime();
        }
    }

    public static Date add(long time, int type, int offset) {
        Date date = new Date(time);
        return add(date, type, offset);
    }

    public static int compareForDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            int offset = getDayOffset(date1, date2);
            if (offset > 0) {
                return 1;
            } else {
                return offset < 0 ? -1 : 0;
            }
        } else {
            return -2;
        }
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        } else {
            String dateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
            return dateStr.replaceAll("((\\s|:)00)+$", "");
        }
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        } else if (format == null) {
            return format(date);
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(date);
            } catch (Exception var3) {
                return "";
            }
        }
    }

    public static Date getCurrentDay() {
        try {
            String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
        } catch (ParseException var3) {
            long tm = System.currentTimeMillis();
            tm -= (tm + (long) TIME_ZONE_OFFSET) % 86400000L;
            return new Date(tm);
        }
    }

    public static int getDayOffset(Date begin, Date end) {
        if (begin != null && end != null) {
            long day1 = (begin.getTime() + (long) TIME_ZONE_OFFSET) / 86400000L;
            long day2 = (end.getTime() + (long) TIME_ZONE_OFFSET) / 86400000L;
            return (int) (day2 - day1);
        } else {
            return 0;
        }
    }

    public static Date getFirstDateOfYear() {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(Calendar.getInstance().get(1) + "-01-01");
        } catch (ParseException var1) {
            throw new RuntimeException(var1);
        }
    }

    public static Date getFirstDateOfYear(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                cal.setTime(date);
            }

            return (new SimpleDateFormat("yyyy-MM-dd")).parse(Calendar.getInstance().get(1) + "-01-01");
        } catch (ParseException var2) {
            return null;
        }
    }

    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            return null;
        } else {
            String monthStr = (new SimpleDateFormat("yyyy-MM")).format(date);
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(monthStr + "-01"));
                return cal.getTime();
            } catch (ParseException var4) {
                return null;
            }
        }
    }

    public static Date getFirstDayOfNextMonth(Date date) {
        if (date == null) {
            return null;
        } else {
            String monthStr = (new SimpleDateFormat("yyyy-MM")).format(date);
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(monthStr + "-01"));
                cal.add(2, 1);
                return cal.getTime();
            } catch (ParseException var4) {
                return null;
            }
        }
    }

    public static int getHourOffset(Date begin, Date end) {
        if (begin != null && end != null) {
            long hour1 = (begin.getTime() + (long) TIME_ZONE_OFFSET) / 3600000L;
            long hour2 = (end.getTime() + (long) TIME_ZONE_OFFSET) / 3600000L;
            return (int) (hour2 - hour1);
        } else {
            return 0;
        }
    }

    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            return null;
        } else {
            String monthStr = (new SimpleDateFormat("yyyy-MM")).format(date);
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(monthStr + "-01"));
                cal.add(2, 1);
                cal.add(5, -1);
                return cal.getTime();
            } catch (ParseException var4) {
                return null;
            }
        }
    }

    public static Date getLastDayOfNextMonth(Date date) {
        if (date == null) {
            return null;
        } else {
            String monthStr = (new SimpleDateFormat("yyyy-MM")).format(date);
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(monthStr + "-01"));
                cal.add(2, 1);
                return cal.getTime();
            } catch (ParseException var4) {
                return null;
            }
        }
    }

    public static int getMinuteOffset(Date begin, Date end) {
        if (begin != null && end != null) {
            long minute1 = (begin.getTime() + (long) TIME_ZONE_OFFSET) / 60000L;
            long minute2 = (end.getTime() + (long) TIME_ZONE_OFFSET) / 60000L;
            return (int) (minute2 - minute1);
        } else {
            return 0;
        }
    }

    public static Date parse(String dateStr) {
        if (dateStr == null) {
            return null;
        } else {
            String format = "yyyy-MM-dd HH:mm:ss.sss".substring(0, dateStr.length());

            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(dateStr);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static Date parse(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        } else {
            if (format == null) {
                try {
                    return (new SimpleDateFormat("yyyy-MM-dd")).parse(dateStr);
                } catch (ParseException var4) {
                    ;
                }
            }

            try {
                return (new SimpleDateFormat(format)).parse(dateStr);
            } catch (ParseException var3) {
                return null;
            }
        }
    }

    public static Date toDate(Date date) {
        if (date == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd")).parse((new SimpleDateFormat("yyyy-MM-dd")).format(date));
            } catch (ParseException var2) {
                return null;
            }
        }
    }
}