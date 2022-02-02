package com.example.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class SuperHeroDto {

    @NotEmpty(message = "First name can not be empty")
    @NotNull(message = "Please provide a first name")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @NotNull(message = "Please provide a last name")
    private String lastName;

    @NotEmpty(message = "Hero name can not be empty")
    @NotNull(message = "Please provide a hero name")
    private String superHeroName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email address!")
    @NotNull(message = "Please provide a email")
    private String email;

    @PastOrPresent(message = "Birthday can not be in the future!")
    //@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date! Must be in format yyyy-mm-dd")
    @NotNull(message = "Please provide a birthday")
    //@com.example.exception.validator.LocalDate
    private LocalDate birthday;

    public SuperHeroDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuperHeroName() {
        return this.superHeroName;
    }

    public void setSuperHeroName(String superHeroName) {
        this.superHeroName = superHeroName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
