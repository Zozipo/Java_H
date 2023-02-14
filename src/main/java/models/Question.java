package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "question")
    private List<QuestionItem> questionItems;
    public Question() {
        questionItems=new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (QuestionItem qi : this.questionItems) {
            sb.append(qi.getText()).append(", ");
        }
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionItems=" + sb.toString() +
                '}';
    }
}
