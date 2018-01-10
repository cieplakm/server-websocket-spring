package com.gft.challenge1.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingCalendarTests {

    @Test
    public void iterateThroughMeetingDaysTest(){
        MeetingCalendar meetingCalendar = new MeetingCalendar(2016,9,19);

        assertThat(meetingCalendar).extractingResultOf("toString").contains(LocalDate.of(2016,9,20).toString());
        assertThat(meetingCalendar).extractingResultOf("toString").contains(LocalDate.of(2016,9,23).toString());
        assertThat(meetingCalendar).extractingResultOf("toString").contains(LocalDate.of(2016,9,27).toString());
        assertThat(meetingCalendar).extractingResultOf("toString").contains(LocalDate.of(2016,9,30).toString());
        assertThat(meetingCalendar).extractingResultOf("toString").doesNotContain(LocalDate.of(2016,9,21).toString());
        assertThat(meetingCalendar).extractingResultOf("toString").doesNotContain(LocalDate.of(2016,9,22).toString());
        assertThat(meetingCalendar).extractingResultOf("toString").doesNotContain(LocalDate.of(2016,9,24).toString());

    }
}
