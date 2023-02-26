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
        for (User user : users) {
            System.out.println("User " + user.getFirstName() + " " + user.getLastName());
            var roles = user.getUserRoles();
            for (UserRole ur : roles) {
                System.out.println("Role - " + ur.getRole().getName());
            }
        }
        context.close();
    }


    private static void addUserRole() {
        Session context = HiberSessionUtil.getSessionFactory().openSession();
        Transaction tx = context.beginTransaction();
        User user = context.get(User.class, 1); //new User();
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
            boolean isTrue = Byte.parseByte(in.nextLine()) == 1 ? true : false;
            QuestionItem qi = new QuestionItem();
            qi.setText(text);
            qi.setTrue(isTrue);
            qi.setQuestion(q);
            context.save(qi);
            System.out.println("0. Вийти\n1. Додати наступний варіант.");
            System.out.print("->_");
            action = in.nextLine();
        } while (!action.equals("0"));
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
            System.out.println("Role: " + role.getName());
        context.close();
    }

    private static void gameTest() {
        Scanner in = new Scanner(System.in);
        String action = "";
        do {

            System.out.println("=============== Head Menu ================");
            System.out.print("1)Play\n2)Add Question\n3)Remove Question\n4)Edit Question\n5)Show Questions\n0)Exit\nEnter: ");
            action = in.nextLine();
            if (action.equals("1")) {
                Start();
            } else if (action.equals("2")) {
                addQuestion();
            } else if (action.equals("3")) {
                RemoveQuestion();
            } else if (action.equals("4")) {
                EditQuestion();
            } else if (action.equals("5")) {
                showQuestions();
            }

        } while (!action.equals("0"));
    }

    private static void RemoveQuestion() {
        try {
            Scanner in = new Scanner(System.in);
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();
            Query query = context.createQuery("FROM Question");
            List<Question> listQuestion = query.list();

            System.out.println("============= RemoveQuestion ================");
            for (Question question : listQuestion) {
                System.out.println("Id: " + question.getId() + ") Назва: " + question.getName());
            }
            System.out.println("Введіть id питання якого хочете видалити:");
            System.out.print("Enter: ");
            int idC = in.nextInt();

            Query tempQuery = context.createQuery("FROM QuestionItem WHERE question_id= '" + idC + "'");
            List<QuestionItem> listQuestionItems = tempQuery.list();
            for (QuestionItem q : listQuestionItems) {

                context.remove(q);

            }
            Question question = context.get(Question.class, idC);
            context.remove(question);


            tx.commit();
            context.close();


        } catch (Exception ex) {
            System.out.println("Exception removeQuestion: " + ex.getMessage());
        }
    }

    private static void EditQuestion() {
        try {
            Scanner in = new Scanner(System.in);
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();
            Query query = context.createQuery("FROM Question");
            List<Question> listQuestion = query.list();
            System.out.println("============= Edit Question ================");
            for (Question question : listQuestion) {
                System.out.println("Id: " + question.getId() + " ) Назва: " + question.getName());
            }
            System.out.print("Ведіть Id питання якого хочете змінити: ");

            int idQuestion = Integer.parseInt(in.nextLine());
            Question editQuestion = context.get(Question.class, idQuestion);
            System.out.println("=============================================");
            System.out.println("Старе питання: " + editQuestion.getName());
            System.out.print("Ведіть нове питання: ");
            String newNameQuestion = in.nextLine();
            editQuestion.setName(newNameQuestion);
            context.save(editQuestion);
            Query tempQuery = context.createQuery("FROM QuestionItem WHERE question_id= '" + editQuestion.getId() + "'");
            List<QuestionItem> listQuestionItems = tempQuery.list();
            System.out.println("=============================================");

            for (QuestionItem q : listQuestionItems) {
                System.out.println("Id: " + q.getId() + " Відповідь: " + q.getText() + "   Is_true: " + q.isTrue());
            }
            System.out.println("Ведіть id відповіді яку хочете змінити");
            int idItemQuestion = Integer.parseInt(in.nextLine());
            QuestionItem editQuestionItem = context.get(QuestionItem.class, idItemQuestion);
            System.out.println("Ведіть нову відповідь");
            String newName = in.nextLine();
            editQuestionItem.setText(newName);
            System.out.println("Відповіть є => Правильна - 1, Невірна - 0");
            System.out.print("Введетіть =>: ");
            boolean isTrue = Byte.parseByte(in.nextLine()) == 1 ? true : false;
            editQuestionItem.setTrue(isTrue);
            context.save(editQuestionItem);


            tx.commit();
            context.close();


        } catch (Exception ex) {

        }
    }

    public static void editCategory() {
        try {
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();
            Scanner in = new Scanner(System.in);


            Query queue = context.createQuery("FROM Role");
            List<Role> roleList = queue.list();
            for (Role role : roleList) {
                System.out.println("ID: " + role.getId() + " Name: " + role.getName());
            }
            System.out.println("Enter Category id: ");
            int idC = Integer.parseInt(in.nextLine());
            System.out.println("Enter new category name: ");
            String idCN = in.nextLine();

            Role role = (Role) context.get(Role.class, idC);
            role.setName(idCN);
            context.update(role);
            tx.commit();

            context.close();


        } catch (Exception ex) {
            System.out.println("Exception addNewCategory: " + ex.getMessage());
        }

    }

    public static void removeCategory(int idC) {
        try {
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();
            Role role = (Role) context.get(Role.class, idC);
            context.remove(role);
            tx.commit();
            context.close();

            System.out.println("Successfully removed!");

        } catch (Exception ex) {
            System.out.println("Exception removeCategory: " + ex.getMessage());
        }

    }

    public static void addNewCategory(String newCategory) {
        try {
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Role role = new Role();
            role.setName(newCategory);
            context.save(role);
            System.out.println("Role: " + newCategory + " Added successfully !");
            context.close();
        } catch (Exception ex) {
            System.out.println("Exception addNewCategory: " + ex.getMessage());
        }

    }

    public static void ShowCategory() {
        try {
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Query queue = context.createQuery("FROM Role");
            List<Role> roleList = queue.list();
            for (Role role : roleList) {
                System.out.println("ID: " + role.getId() + " Name: " + role.getName());
            }
            context.close();


        } catch (Exception ex) {
            System.out.println("Exception addNewCategory: " + ex.getMessage());
        }

    }

    public static void Start() {
        try {
            Scanner in = new Scanner(System.in);
            Session context = HiberSessionUtil.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();
            Query query = context.createQuery("FROM Question");
            List<Question> listQuestion = query.list();
            int score = 0;

            for (Question question : listQuestion) {
                System.out.println("=============================");
                Query tempQuery = context.createQuery("FROM QuestionItem WHERE question_id= '" + question.getId() + "'");
                List<QuestionItem> listQuestionItems = tempQuery.list();
                System.out.println("Question: " + question.getName());
                for (QuestionItem questionItem : listQuestionItems) {
                    System.out.println(questionItem.getText());
                    System.out.println("True - 1, False - 2");
                    System.out.print("Enter : ");
                    boolean isTrue = Byte.parseByte(in.nextLine()) == 1 ? true : false;
                    if (isTrue == true && questionItem.isTrue() == true) {

                        score += 12;
                        System.out.println(score);
                    }
                }

            }
            int Size = listQuestion.size();
            double mark = score / Size;

            System.out.println("Score: " + mark);


        } catch (Exception ex) {

        }
    }
}
