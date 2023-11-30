package com.example.hibernate3;

import com.example.hibernateEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//many-to-many example
public class DeleteStudentsAndCourses {

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

                //deleting course and student separately
                // to make sure that no "CascadeType.Delete" works properly
//                int courseId = 10;
//                Course course = session.get(Course.class, courseId);
//                session.remove(course);

                int studentId = 2;
                Student student = session.get(Student.class, studentId);
                session.remove(student);

                session.getTransaction().commit();
            }
        }
    }
}