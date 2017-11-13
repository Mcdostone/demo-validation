package net.tncy.demo;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private static Validator validator;

    @Before
    public void setup() {
        BasicConfigurator.configure();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        PersonTest.validator = factory.getValidator();
    }

    @Test
    public void testBasicConstructor() {
        Person p = new Person("Eliot", "Anderson", new Date(), 18, "LA");
        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testAnonymousPerson() {
        Person p = new Person("", "", null, "");
        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(4, constraintViolations.size());
    }

    @Test
    public void testConstructorWithNullFirstname() {
        Person p = new Person(null, "Berry", new Date(), 18, "London");
        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testConstructorForChild() {
        Person p = new Person("Claire", "Crapanzano", new Date(), 5, "Berlin");
        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testSecondConstructor() {
        LocalDate birth = LocalDate.of(1995, 10, 23);
        Date date = Date.from(birth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Person p = new Person("Victor", "Chelou", date, "La vôgie");

        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testCalculateAge() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LocalDate birth = LocalDate.of(1993, 5, 26);
        Date date = Date.from(birth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Method m = Person.class.getDeclaredMethod("calculateAge", Date.class);
        m.setAccessible(true);
        Object o = m.invoke(null, date);
        assertEquals(24, o);
    }

}
