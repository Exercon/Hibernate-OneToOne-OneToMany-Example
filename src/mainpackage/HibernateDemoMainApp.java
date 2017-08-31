package mainpackage;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateDemoMainApp {
    public static void main(String... args){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //Get Instructor with the ID 4 and get Course's related to that Instructor.
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            Instructor tempInstructor = session.get(Instructor.class,4);
            List<Course> list = tempInstructor.getCourses();
            session.getTransaction().commit();
            for(Course i : list)
                System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
}