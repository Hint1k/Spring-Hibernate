package com.example.hibernate3;

import com.example.hibernateEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//many-to-many example
public class ReadStudentsAndCourses {

    public static void main(String[] args) {

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

                Course course2 = new Course("History");
                Course course3 = new Course("Physics");

                int studentId = 2;
                Student student = session.get(Student.class,studentId);
                System.out.println(">> Student:" + student);
                System.out.println(">> Courses: " + student.getCourses());

                session.getTransaction().commit();
            }
        }
    }
}