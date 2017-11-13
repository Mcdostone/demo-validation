package net.tncy.demo;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
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
    public void testConstructorWithNullFirstname() {
        Person p = new Person("Eliot", null, new Date(), 18, "LA");
        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testConstructorForChild() {
        Person p = new Person("Claire", "Crapanzano", new Date(), 5, "LA");
        Set<ConstraintViolation<Person>> constraintViolations = PersonTest.validator.validate(p);
        assertEquals(1, constraintViolations.size());
    }

}
