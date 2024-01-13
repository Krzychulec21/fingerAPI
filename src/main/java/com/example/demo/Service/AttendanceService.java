package com.example.demo.Service;

import com.example.demo.Model.Attendance;
import com.example.demo.Repository.AttendanceRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void recordEntry (Attendance time) {
        attendanceRepository.save(time);
    }
    public void recordExit (Attendance time) {
        attendanceRepository.save(time);
    }

}
