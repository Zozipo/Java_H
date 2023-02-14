package crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HiberSessionUtil;
import models.Role;
public class deleteRole {

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
        Role role = (Role)session.load(Role.class, 2);
        session.delete(role);
        System.out.println("Deleted Successfully");
        session.getTransaction().commit();
        sessionFactory.close();
    }
}
