package com.example.hibernate2;

import com.example.hibernateEntity.Course;
import com.example.hibernateEntity.Instructor;
import com.example.hibernateEntity.InstructorDetail;
import com.example.hibernateEntity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//one-to-many example
public class ReadCourseAndReviews {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate2.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory()) {

            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                int id = 10;
                Course course = session.get(Course.class, id);
                System.out.println(">>Course: "+course);
                System.out.println(">>Reviews: "+course.getReviews());
                session.getTransaction().commit();
            }
        }
    }
}