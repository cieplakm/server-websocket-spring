package com.gft.challenge1.server.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface MeetingCalendar extends Iterable<LocalDate> {

    void setMeetingDays(DayOfWeek[] dayOfMeeting);

}
