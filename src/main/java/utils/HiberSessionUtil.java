package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HiberSessionUtil {
    private static SessionFactory sessionFactory;
    public HiberSessionUtil() {}
    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null)
        {
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(QuestionItem.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(UserRole.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory();
            } catch(Exception ex) {
                System.out.println("Помилка підлкючення "+ex.toString());
            }
        }
        return sessionFactory;
    }
}
