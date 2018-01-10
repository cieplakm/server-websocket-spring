package com.gft.challenge1.server;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Iterator;

public class MeetingCalendar implements Iterable<LocalDate>{
    private LocalDate initialDate;
    private DayOfWeek[] meetingDays;

    public MeetingCalendar(int startYear, int startMonth, int startDay) {
        initialDate = LocalDate.of(startYear,startMonth,startDay);
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new LocalDateIterator(initialDate);
    }

    public void setMeetingDays(DayOfWeek[] meetingDays) {
        this.meetingDays = meetingDays;
    }


    class LocalDateIterator implements Iterator<LocalDate>{

        private LocalDate iterate;

        LocalDateIterator(LocalDate initialDate ) {
            this.iterate = initialDate;
        }

        @Override
        public boolean hasNext() {
            return iterate.isBefore(LocalDate.of(2100,1,1));//always will be Tuesday and Friday but prevent infinity looping - date before 1.1.2100
        }

        @Override
        public LocalDate next() {
            return getNextMeetingDay();
        }

        private boolean isMeetingDay(LocalDate date){
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            //meetings are in Tuesday and Friday
            if ( meetingDays != null ){

                for (DayOfWeek dow : meetingDays){
                    if (dayOfWeek == dow){
                        return true;
                    }
                }
                return false;

            }else {

                return  dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.FRIDAY ;

            }

        }

        private void setNextDay(){
            iterate = iterate.plusDays(1);
        }

        private LocalDate getNextMeetingDay() {
            while (true) {
                setNextDay();

                if (isMeetingDay(iterate)) {
                    return iterate;
                }
            }
        }

    }

}
