import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "groups_students")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_of_course", nullable = false)
    private String nameOfCourse;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private static final List<Students> studentsList = new ArrayList<>();

    public Groups() {
    }

    public Groups(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public List<Students> getStudentsList() {
        return studentsList;
    }

    public void addStudent(Students student) {
        student.setGroup(this);
        studentsList.add(student);
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }
}