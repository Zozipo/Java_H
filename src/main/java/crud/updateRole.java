package crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HiberSessionUtil;
import models.Role;
public class updateRole {

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        //Create session factory object
        SessionFactory sessionFactory = HiberSessionUtil.getSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();

        Role role = (Role)session.get(Role.class, 2);
        role.setName("Admin2");
        System.out.println("Updated Successfully");
        session.getTransaction().commit();
        sessionFactory.close();
    }
}