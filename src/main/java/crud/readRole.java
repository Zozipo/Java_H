package crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HiberSessionUtil;
import models.Role;
public class readRole   {
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
        Query query = session.createQuery("from Role");
        List<Role> roles = query.list();
        for(Role role : roles)
        {
            System.out.println("Role: "+role.getName());
        }
        session.getTransaction().commit();
        sessionFactory.close();
    }
}