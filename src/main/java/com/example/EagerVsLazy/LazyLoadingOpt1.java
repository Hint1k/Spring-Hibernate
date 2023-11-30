package com.example.EagerVsLazy;

import com.example.hibernateEntity.Course;
import com.example.hibernateEntity.Instructor;
import com.example.hibernateEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//eager vs lazy loading
public class LazyLoadingOpt1 {

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
                System.out.println(">>Instructor:" + instructor);

                // calling the getter method while the session is still open
                System.out.println(">>Courses: " + instructor.getCourses());
                session.getTransaction().commit();

                session.close();

                //testing the lazy loading after the session is already closed
                System.out.println(">>Courses: " + instructor.getCourses());
            }
        }
    }
}
