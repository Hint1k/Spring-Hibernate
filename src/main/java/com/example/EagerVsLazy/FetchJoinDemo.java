package com.example.EagerVsLazy;

import com.example.hibernateEntity.Course;
import com.example.hibernateEntity.Instructor;
import com.example.hibernateEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//eager vs lazy loading
public class FetchJoinDemo {

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

                //using "join fetch" before closing the session
                String string = "select ins from Instructor ins "
                        + "join fetch ins.courses "
                        +"where ins.id=:InstructorId";
                Query<Instructor> query = session.createQuery(string,Instructor.class);
                query.setParameter("InstructorId",id);
                Instructor instructor = query.getSingleResult();

                System.out.println(">>Instructor:" + instructor);

                session.getTransaction().commit();

                System.out.println("Session is closed");
                session.close();

                //testing the lazy loading after the session is already closed
                System.out.println(">>Courses: " + instructor.getCourses());
            }
        }
    }
}
