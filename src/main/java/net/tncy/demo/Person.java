package net.tncy.demo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Person {

    @NotNull
    @Size(min=1)
    private String firstname;
    @NotNull
    @Size(min=1)
    private String lastname;
    @NotNull
    private Date birthday;
    @NotNull
    @Min(value=18)
    private Integer age;
    private String cityzenship;

    public Person(String firstname, String lastname, Date birthday, int age, String cityzenship) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.age = age;
        this.cityzenship = cityzenship;
    }

    public Person(String firstname, String lastname, Date birthday, String cityzenship) {
        this(firstname, lastname, birthday, calculateAge(birthday), cityzenship);
    }

    private static int calculateAge(Date birthday) {
        if (birthday != null) {
            LocalDate now = LocalDate.now();
            LocalDate birth = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birth, now).getYears();
        }
        return 0;
    }

}
