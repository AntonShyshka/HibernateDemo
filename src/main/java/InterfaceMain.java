import org.hibernate.annotations.common.reflection.XMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class InterfaceMain {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            emf = Persistence.createEntityManagerFactory("HibernateDemo");
            em = emf.createEntityManager();
            boolean close = true;
            try {
                while (close) {
                    System.out.println("Enter you choose:");
                    System.out.println("\t1: Create new student");
                    System.out.println("\t2: Create new group");
                    System.out.println("\t3: Add student in the group");
                    System.out.println("Enter 'E' for exit");
                    System.out.print("->");

                    String s = scanner.nextLine();
                    switch (s) {
                        case "1" -> Methods.addStudent(scanner);
                        case "2" -> Methods.addGroup(scanner);
                        case "3" -> Methods.addStudentInGroup(scanner);
                        case "4" -> Methods.printListOfGroupsWithStudentCounter();
                        case "E" -> close = false;
                    }
                }
            } finally {
                scanner.close();
                emf.close();
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
