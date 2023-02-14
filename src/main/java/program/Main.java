package program;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HiberSessionUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //RoleWorks();
        //addQuestion();
        //showQuestions();
        //addUserRole();
        showUserInfo();
    }
    private static void showUserInfo() {
        Session context = HiberSessionUtil.getSessionFactory().openSession();
        Query query = context.createQuery("FROM User");
        List<User> users = query.list();
        for (User user: users) {
            System.out.println("User "+ user.getFirstName()+ " "+ user.getLastName());
            var roles = user.getUserRoles();
            for (UserRole ur : roles) {
                System.out.println("Role - "+ur.getRole().getName());
            }
        }
        context.close();
    }


    private static void addUserRole() {
        Session context = HiberSessionUtil.getSessionFactory().openSession();
        Transaction tx = context.beginTransaction();
        User user =context.get(User.class, 1); //new User();
        //user.setFirstName("Іван"); user.setLastName("Бегемот"); user.setPhone("+73892837843");
        //user.setEmail("ivam@gmail.com"); user.setPassword("123456");
        //context.save(user);
        Role role = context.get(Role.class, 1);
        UserRole ur = new UserRole();
        ur.setUser(user);
        ur.setRole(role);
        context.save(ur);
        tx.commit();
        context.close();
    }
    private static void addQuestion() {
        Scanner in = new Scanner(System.in);
        Session context = HiberSessionUtil.getSessionFactory().openSession();
        Transaction tx = context.beginTransaction();
        System.out.println("Вкажіть назву питання:");
        String questionText = in.nextLine();
        Question q = new Question();
        q.setName(questionText);
        context.save(q);
        String action = "";
        do {
            System.out.println("Варіант відпоіді:");
            String text = in.nextLine();
            System.out.println("Праивльно - 1, невірно - 0");
            boolean isTrue = Byte.parseByte(in.nextLine())==1 ? true : false;
            QuestionItem qi = new QuestionItem();
            qi.setText(text);
            qi.setTrue(isTrue);
            qi.setQuestion(q);
            context.save(qi);
            System.out.println("0. Вийти\n1. Додати наступний варіант.");
            System.out.print("->_");
            action = in.nextLine();
        }while(!action.equals("0"));
        tx.commit();
        context.close();
    }
    private static void showQuestions() {
        Session context = HiberSessionUtil.getSessionFactory().openSession();
        Query query = context.createQuery("FROM Question");
        List<Question> questions = query.list();
        for (Question q : questions)
            System.out.println(q.toString());
        context.close();
    }
    private static void RoleWorks() {
        Scanner in = new Scanner(System.in);
        Session context = HiberSessionUtil.getSessionFactory().openSession();
//        Role role = new Role();
//        System.out.println("Вкажіть назву ролі:");
//        String name = in.nextLine();
//        role.setName(name);
//        context.save(role);
        Query query = context.createQuery("FROM Role");
        List<Role> list = query.list();
        for (Role role : list)
            System.out.println("Role: "+ role.getName());
        context.close();
    }
}
