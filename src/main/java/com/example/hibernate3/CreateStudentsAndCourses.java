package com.example.hibernate3;

import com.example.hibernateEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//many-to-many example
public class CreateStudentsAndCourses {

    public static void main(String[] args) {

        Course course1 = new Course("Data Structures and Algorithms");
        Course course2 = new Course("History");
        Course course3 = new Course("Physics");
        Student student1 = new Student("John","Doe", "jd@test.com");
        Student student2 = new Student("Mary","Sue", "ms@test.com");
        Student student3 = new Student("Gary","Smith", "gs@test.com");
        course1.addStudent(student1);
        course1.addStudent(student2);
        course1.addStudent(student3);
        course2.addStudent(student2);
        course3.addStudent(student2);

        try (SessionFactory factory = new Configuration()
                .configure("hibernate3.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {

            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                session.persist(course1);
                session.persist(course2);
                session.persist(course3);
                session.getTransaction().commit();
            }
        }
    }
}