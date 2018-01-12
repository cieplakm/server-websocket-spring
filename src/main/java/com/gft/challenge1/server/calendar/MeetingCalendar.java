package com.gft.challenge1.server.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface MeetingCalendar extends Iterable<LocalDate> {

    /** I want to change meeting days **/
    void setMeetingDays(DayOfWeek[] dayOfMeeting);

}
