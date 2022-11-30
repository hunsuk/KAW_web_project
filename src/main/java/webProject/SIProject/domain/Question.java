package webProject.SIProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@Setter
@Getter
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    //Question = 1 -> Answer = 1 
    //Question 단방향으로 Answer 정보 참조 가능.
    @OneToOne
    @JoinColumn(name="ANSWER_ID")
    private Answer answer;

    //User = 1 : Order = many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="code")
    private User user;

}