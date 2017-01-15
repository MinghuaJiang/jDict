package domain;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import util.DateUtilities;

public enum PeriodState
{
    ONE_DAY
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            return TWO_DAYS;
        }

        @Override
        public String calculateTargetDay(String today)
        {
            Date date = DateUtilities.parseYYYYMMDD(today);
            Date newDate = DateUtils.addDays(date, 1);
            return DateUtilities.YYYYMMDD(newDate);
        }
    },
    TWO_DAYS
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            return FOUR_DAYS;
        }

        @Override
        public String calculateTargetDay(String today)
        {
            Date date = DateUtilities.parseYYYYMMDD(today);
            Date newDate = DateUtils.addDays(date, 2);
            return DateUtilities.YYYYMMDD(newDate);
        }
    },
    FOUR_DAYS
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            return SEVEN_DAYS;
        }

        @Override
        public String calculateTargetDay(String today)
        {
            // TODO Auto-generated method stub
            Date date = DateUtilities.parseYYYYMMDD(today);
            Date newDate = DateUtils.addDays(date, 4);
            return DateUtilities.YYYYMMDD(newDate);
        }
    },
    SEVEN_DAYS
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            return FIFTEEN_DAYS;
        }

        @Override
        public String calculateTargetDay(String today)
        {
            // TODO Auto-generated method stub
            Date date = DateUtilities.parseYYYYMMDD(today);
            Date newDate = DateUtils.addDays(date, 7);
            return DateUtilities.YYYYMMDD(newDate);
        }
    },
    FIFTEEN_DAYS
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            return THIRTY_DAYS;
        }

        @Override
        public String calculateTargetDay(String today)
        {
            // TODO Auto-generated method stub
            Date date = DateUtilities.parseYYYYMMDD(today);
            Date newDate = DateUtils.addDays(date, 15);
            return DateUtilities.YYYYMMDD(newDate);
        }
    },
    THIRTY_DAYS
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            return COMPLETE;
        }

        @Override
        public String calculateTargetDay(String today)
        {
            Date date = DateUtilities.parseYYYYMMDD(today);
            Date newDate = DateUtils.addDays(date, 30);
            return DateUtilities.YYYYMMDD(newDate);
        }
    },
    COMPLETE
    {

        @Override
        public PeriodState getNextState()
        {
            // TODO Auto-generated method stub
            throw new IllegalStateException("Unsupported operation");
        }

        @Override
        public String calculateTargetDay(String today)
        {
            // TODO Auto-generated method stub
            throw new IllegalStateException("Unsupported operation");
        }
    };

    public abstract String calculateTargetDay(String today);

    public abstract PeriodState getNextState();

}
