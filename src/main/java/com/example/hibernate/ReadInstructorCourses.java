package com.example.hibernate;

import com.example.hibernateEntity.Course;
import com.example.hibernateEntity.Instructor;
import com.example.hibernateEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//one-to-many example
public class ReadInstructorCourses {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {

            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                int id=1;
                Instructor instructor = session.get(Instructor.class, id);
                System.out.println("Instructor:" + instructor);
                System.out.println("Courses: " + instructor.getCourses());
                session.getTransaction().commit();
            }
        }
    }
}
