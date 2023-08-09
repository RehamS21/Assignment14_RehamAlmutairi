package com.example.assignment14_p1.Model;

import jakarta.annotation.security.DenyAll;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "Your id can't be empty")
    @Size(min = 3 , message = "Your id must be more than 2 ")
    private String id;

    @NotEmpty(message = "Your name can't be empty")
    @Size(min = 5 , max = 15,message = "Your name must be more than 4 ")
    private String name;

    @NotNull(message = "Your age can't be empty")
    @Positive(message = "Your age must be a positive number and greater than zero")
    @Min(25)
    private int age;

    @NotEmpty(message = "Your position can't be empty")
    @Pattern(regexp = "\\b(supervisor)|\\b(coordinator)")
    private String position;

    @AssertFalse(message = "should be false value")
    private boolean onLeave;

    @NotNull(message = "Employment year should not be empty")
    @Positive(message = "Your employment year must be a positive number")
    @Min(value = 2000 , message = "your employment year should be start from 2000")
    @Max(value = 2023, message = "your employment year should not be exceed than 2023")
    private int employmentYear;

    @NotNull(message = "Your annual leave can't be empty")
    @Positive(message = "Your annual leave must be a positive number and not equal to zero")
    private int annualLeave;

}
