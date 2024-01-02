import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Methods {
    static EntityManager em = InterfaceMain.em;

    protected static void addStudent(Scanner scanner) {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter student age:");
        int age = scanner.nextInt();

        em.getTransaction().begin();
        try {
            Students students = new Students(name, surname, age);
            em.persist(students);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    protected static void addGroup(Scanner scanner) {
        System.out.println("Enter group name:");
        String groupName = scanner.nextLine();

        em.getTransaction().begin();
        try {
            Groups groups = new Groups(groupName);
            em.persist(groups);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    protected static List<Students> allStudents() {
        Query query = em.createQuery("SELECT s FROM Students s", Students.class);
        return (List<Students>) query.getResultList();
    }

    protected static List<Groups> allGroups() {
        Query query = em.createQuery("SELECT g FROM Groups g", Groups.class);
        return (List<Groups>) query.getResultList();
    }

    protected static void addStudentInGroup(Scanner scanner) {
        List<Students> studentsList = allStudents();
        List<Groups> groupsList = allGroups();

        Students student;
        Groups group;

        System.out.println("Enter student ID:");
        int studentId = scanner.nextInt();
        System.out.println("Enter group ID:");
        int groupId = scanner.nextInt();
        int studentIdList = studentId - 1;
        int groupIdList = groupId - 1;
        student = studentsList.get(studentIdList);
        group = groupsList.get(groupIdList);

        em.getTransaction().begin();
        try {
            group.addStudent(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    protected static void printListOfGroupsWithStudentCounter() {
        List<Groups> groupsList = allGroups();

        for (Groups g : groupsList) {
            System.out.print("Name of course: " + g.getNameOfCourse() +
                    ", on this course " + g.getStudentsList().size() +
                    " students is studying\n");
        }
    }
}