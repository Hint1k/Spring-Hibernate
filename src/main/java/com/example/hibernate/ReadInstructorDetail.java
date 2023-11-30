package com.example.hibernate;

import com.example.hibernateEntity.Instructor;
import com.example.hibernateEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//one-to-one bi-directional example
public class ReadInstructorDetail {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory()) {

            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                int id = 8;
                InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
                System.out.println("InstructorDetail = " + instructorDetail);
                System.out.println("Instructor = "+ instructorDetail.getInstructor());
                session.getTransaction().commit();
            }
        }
    }
}
