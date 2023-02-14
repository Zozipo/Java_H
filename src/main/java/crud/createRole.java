package crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HiberSessionUtil;
import models.Role;
public class createRole {

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        //Create student entity object
        Role role = new Role();
        role.setName("Admin");

        //Create session factory object
        SessionFactory sessionFactory = HiberSessionUtil.getSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();

        session.save(role);
        System.out.println("Inserted Successfully");
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}