package com.gft.challenge1.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingCalendarTests {

    @Test
    public void iterateThroughMeetingDaysTest(){
        MeetingCalendar meetingCalendar = new MeetingCalendar(2016,9,19);
        DayOfWeek[] meetingsDayOfWeek = {DayOfWeek.FRIDAY, DayOfWeek.TUESDAY};

        assertThat(meetingCalendar)
                .extractingResultOf("getDayOfWeek")
                .contains(meetingsDayOfWeek);

        DayOfWeek[] meetingsDayOfWeek2 = {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY};

        meetingCalendar.setMeetingDays(meetingsDayOfWeek2);

        assertThat(meetingCalendar)
                .extractingResultOf("getDayOfWeek")
                .contains(meetingsDayOfWeek2);

        assertThat(meetingCalendar)
                .extractingResultOf("getDayOfWeek")
                .doesNotContain(meetingsDayOfWeek);


    }
}
