package com.gft.challenge1.server;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import com.gft.challenge1.server.calendar.MeetingCalendarTill2100;
import com.gft.challenge1.server.calendar.MeetingCalendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;


public class MeetingCalendarTill2100Tests {

    @Test
    public void testIfMeetingDaysIsTuesdayOrFriday(){
        //Monday
        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2016,9,19));

        //Tuesday
        LocalDate dateOfFirstMeeting = LocalDate.of(2016,9,20);
        //Friday
        LocalDate dateOfSecondMeeting = LocalDate.of(2016,9,23);

        assertThat(meetingCalendar.iterator().next()
                .equals(dateOfFirstMeeting));

        assertThat(meetingCalendar.iterator().next()
                .equals(dateOfSecondMeeting));

    }


    @Test
    public void setAnotherDayMeetingTest(){
        DayOfWeek[] meetingDays = {DayOfWeek.MONDAY};

        //Date of Monaday
        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2016,9,26));
        meetingCalendar.setMeetingDays(meetingDays);

        LocalDate dateOfFirstMeeting = LocalDate.of(2016,9,20);


        assertThat(meetingCalendar.iterator().next()
                .equals(dateOfFirstMeeting));

    }

    @Test
    public void shouldProduceIteratorWithMeetingDays(){
        //Monday
        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2016,9,19));

        DayOfWeek[] meetingDaysA = {DayOfWeek.TUESDAY};
        meetingCalendar.setMeetingDays(meetingDaysA);
        Iterator<LocalDate> i1 =  meetingCalendar.iterator();

        DayOfWeek[] meetingDaysB = {DayOfWeek.MONDAY};
        meetingCalendar.setMeetingDays(meetingDaysB);

        assertThat( i1.next().getDayOfWeek().equals(DayOfWeek.TUESDAY) );

    }

    @Test
    public void shouldProduceIndependentIterators(){
        //Monday
        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2016,9,19));

        DayOfWeek[] meetingDaysA = {DayOfWeek.TUESDAY, DayOfWeek.FRIDAY};
        meetingCalendar.setMeetingDays(meetingDaysA);
        Iterator<LocalDate> i1 =  meetingCalendar.iterator();
        Iterator<LocalDate> i2 =  meetingCalendar.iterator();

        assertThat( i1.next().getDayOfWeek().equals(DayOfWeek.TUESDAY) );
        assertThat( i2.next().getDayOfWeek().equals(DayOfWeek.TUESDAY) );

    }



}
