package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Employee;
import com.example.demo.Service.AttendanceService;
import com.example.demo.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @PostMapping("/entry")
    public ResponseEntity<String> recordEntry(@RequestBody Long employeeId ) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Attendance record = new Attendance();
            record.setEmployee(employeeOptional.get());
            record.setEntryTime(LocalDateTime.now());

            attendanceService.recordEntry(record);
            return ResponseEntity.ok("Wejście zatwierdzone dla ID = " + employeeId);
        } else {
            return ResponseEntity.badRequest().body("Nie znaleziono pracownika o ID: " + employeeId);
        }
    }

    @PostMapping("/exit")
    public ResponseEntity<String> recordExit(@RequestBody Long employeeId) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Attendance record = new Attendance();
            record.setEmployee(employeeOptional.get());
            record.setExitTime(LocalDateTime.now());

            attendanceService.recordExit(record);
            return ResponseEntity.ok("Wyjście zatwierdzone ");
        } else {
            return ResponseEntity.badRequest().body("niee znaleziono pracownika o ID: " + employeeId);
        }
    }

    //TODO: tutaj jest dodanie rekordu wejscia/wyjscia z dupy bez weryfikacji na razie, trzeba bedzie if walnac najpierw w ych metodach
}


