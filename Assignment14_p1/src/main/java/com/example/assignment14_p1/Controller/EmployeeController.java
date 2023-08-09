package com.example.assignment14_p1.Controller;

import com.example.assignment14_p1.ApiResponse.ApiResponse;
import com.example.assignment14_p1.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Employee> getAllEmployee(){
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addNewEmployee(@RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(200).body("Add new employee successfully");
    }

    @PutMapping ("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index,@RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body("updated employee successfully");
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
        if(index == 0)
            return ResponseEntity.status(400).body("No employee to delete it ");
        else if(index > employees.size())
            return ResponseEntity.status(400).body("The index of employee exceed the exist employee");

        String name = employees.get(index).getName();
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("The employee with name '"+name+"' removed successfully"));
    }

    // when the employee want the annual leave

    @PutMapping("/employeeLeave/{index}")
    public ResponseEntity employeeLeave(@PathVariable int index){
        if((employees.get(index).isOnLeave()) )
            return ResponseEntity.status(400).body(new ApiResponse("Can't take a leave because either already you leaved"));
        else if(employees.get(index).getAnnualLeave() == 0)
            return ResponseEntity.status(400).body(new ApiResponse("you did not have annual leaves"));

        employees.get(index).setOnLeave(true);
        int annual_leave = employees.get(index).getAnnualLeave();
        employees.get(index).setAnnualLeave(--annual_leave);

        return ResponseEntity.status(200).body(new ApiResponse("You are leaved and we decrease your annual leaves by 1. " +
                "Your data after update : {Annual leaves  = "+employees.get(index).getAnnualLeave()+", On leave = "+
                employees.get(index).isOnLeave()));
    }
}
