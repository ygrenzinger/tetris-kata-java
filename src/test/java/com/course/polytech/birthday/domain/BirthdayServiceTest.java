package com.course.polytech.birthday;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;

public class BirthdayServiceTest {

    Clock clock = Mockito.mock(Clock.class);
    EmployeeRepository employeeRepository =
    BirthdayService birthdayService = new BirthdayService(clock, );

    @Test
    public void should_send_email_for_birthday() {

    }
}
