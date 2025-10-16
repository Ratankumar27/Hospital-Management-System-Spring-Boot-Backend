package com.ratankumar.gmail.hospitalManagement;

import com.ratankumar.gmail.hospitalManagement.entity.Appointment;
import com.ratankumar.gmail.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void appointmentTest() {
        Appointment appointment =
                Appointment.builder().appointmentTime(LocalDateTime.of(2025, 6, 18, 14,0))
                        .reason("Flu").build();

        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);

        System.out.println(newAppointment);
    }

    @Test
    public void testReAssign() {
        Appointment appointment =
                Appointment.builder().appointmentTime(LocalDateTime.of(2025, 6, 18, 14,0))
                        .reason("Flu").build();

        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);

        System.out.println(newAppointment);

        Appointment updateAppointment = appointmentService.reAssignAppointToDiffDoctr(1L, 2L);

        System.out.println(updateAppointment);
    }
}
