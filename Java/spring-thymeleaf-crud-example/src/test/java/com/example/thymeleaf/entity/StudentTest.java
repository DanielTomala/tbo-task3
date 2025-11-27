package com.example.thymeleaf.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void createStudentWithCorrectData() {
        assertDoesNotThrow(() -> {
            var student = new Student();
            student.setId("1");
            student.setName("Andrzej");
            student.setEmail("andrzej@stud.pl");
            student.setBirthday(LocalDate.of(2000, 2, 13));
            student.setAddress(new Address());
        });
    }

    @Test
    void forbidCreateStudentWithNulls() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId(null);
            student.setName(null);
            student.setEmail(null);
            student.setBirthday(null);
            student.setAddress(null);
        });
    }

    @Test
    void forbidCreateStudentWithReallyOldBirthday() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId("1");
            student.setName("Andrzej");
            student.setEmail("andrzej@stud.pl");
            student.setBirthday(LocalDate.of(986, 1, 1));
            student.setAddress(new Address());
        });
    }

    @Test
    void forbidCreateStudentWithFutureBirthday() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId("1");
            student.setName("Andrzej");
            student.setEmail("andrzej@stud.pl");
            student.setBirthday(LocalDate.of(2030, 1, 1));
            student.setAddress(new Address());
        });
    }

    @Test
    void forbidCreateStudentWithIncorrectEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId("1");
            student.setName("Andrzej");
            student.setEmail("andrzej[at]stud.pl");
            student.setBirthday(LocalDate.of(2000, 2, 13));
            student.setAddress(new Address());
        });
    }

    @Test
    void forbidCreateStudentWithSqlInjection() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId("1");
            student.setName("Andrzej; DROP TABLE Students;");
            student.setEmail("andrzej@stud.pl");
            student.setBirthday(LocalDate.of(2000, 2, 13));
            student.setAddress(new Address());
        });
    }

    @Test
    void forbidCreateStudentWithXss() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId("1");
            student.setName("<script>alert('Ups!')</script>");
            student.setEmail("andrzej@stud.pl");
            student.setBirthday(LocalDate.of(2000, 2, 13));
            student.setAddress(new Address());
        });
    }

    @Test
    void forbidCreateStudentWithExtremeData() {
        assertThrows(IllegalArgumentException.class, () -> {
            var student = new Student();
            student.setId("1".repeat(10000));
            student.setName("Andrzej".repeat(30));
            student.setEmail("andrzej@stud.pl");
            student.setBirthday(LocalDate.of(2000, 2, 13));
            student.setAddress(new Address());
        });
    }

}