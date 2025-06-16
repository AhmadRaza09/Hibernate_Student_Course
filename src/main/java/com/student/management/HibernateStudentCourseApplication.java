package com.student.management;

import com.student.management.models.Student;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;


@SpringBootApplication
public class HibernateStudentCourseApplication {
    private static final Logger logger = LoggerFactory.getLogger(HibernateStudentCourseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HibernateStudentCourseApplication.class, args);
    }

    @Bean
    public ApplicationRunner testValidation(Validator validator) {
        return args -> {
            Student s1 = new Student();
            s1.setName("a");

            Set<ConstraintViolation<Student>> violations = validator.validate(s1);

            for (ConstraintViolation<Student> violation : violations) {
                logger.error("{} {} {}",
                        violation.getPropertyPath(),
                        violation.getMessage(),
                        violation.getInvalidValue());
            }

        };
    }

}
