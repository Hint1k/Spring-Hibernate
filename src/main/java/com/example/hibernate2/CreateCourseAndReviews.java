package com.example.hibernate2;

import com.example.hibernateEntity.Course;
import com.example.hibernateEntity.Instructor;
import com.example.hibernateEntity.InstructorDetail;
import com.example.hibernateEntity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//one-to-many example
public class CreateCourseAndReviews {

    public static void main(String[] args) {

        Course course = new Course("History");
        Review review1 = new Review("It is awesome");
        Review review2 = new Review("It is just bad");
        Review review3 = new Review("It is so so");
        course.addReview(review1);
        course.addReview(review2);
        course.addReview(review3);

        try (SessionFactory factory = new Configuration()
                .configure("hibernate2.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory()) {

            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                session.persist(course);
                session.getTransaction().commit();
            }
        }
    }
}