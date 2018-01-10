package com.gft.challenge1.server;


import com.gft.challenge1.server.calendar.MeetingCalendarTill2100;
import com.gft.challenge1.server.calendar.MeetingCalendar;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;


import static org.assertj.core.api.Assertions.*;

public class MyFirstMeetingCalendarTest {

    @Test
    public void testIfMeetingDaysIsTuesdayOrFriday(){
        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2016,9,19));

        LocalDate dateOfFirstMeeting = LocalDate.of(2016,9,20);
        LocalDate dateOfSecondMeeting = LocalDate.of(2016,9,23);

        assertThat(meetingCalendar.iterator().next()
                .equals(dateOfFirstMeeting));

        assertThat(meetingCalendar.iterator().next()
                .equals(dateOfSecondMeeting));

    }

    @Test
    public void testIfThereIsNoMeetingsAfter2100(){
        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2099,12,31));

        assertThat(meetingCalendar.iterator().hasNext()).isEqualTo(false);
    }

    @Test
    public void setAnotherDayMeetingTest(){
        DayOfWeek[] meetingDays = {DayOfWeek.MONDAY};

        MeetingCalendar meetingCalendar = new MeetingCalendarTill2100(LocalDate.of(2016,9,26));
        meetingCalendar.setMeetingDays(meetingDays);

        LocalDate dateOfFirstMeeting = LocalDate.of(2016,9,20);


        assertThat(meetingCalendar.iterator().next()
                .equals(dateOfFirstMeeting));



    }



}
