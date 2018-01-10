package com.gft.challenge1.server.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;


public class MeetingCalendarTill2100 implements MeetingCalendar {
    private LocalDate initialDate;
    private DayOfWeek[] meetingDays;

    public MeetingCalendarTill2100(LocalDate startDate) {
        initialDate = startDate;
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
            //always will be Tuesday and Friday but prevent infinity looping - date before 1.1.2100
            return iterate.isBefore(LocalDate.of(2100,1,1));
        }

        @Override
        public LocalDate next() {
            return getNextMeetingDay();
        }

        private boolean isMeetingDay(LocalDate date){
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            //meetings are in Tuesday and Friday or days from meetingDays
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
