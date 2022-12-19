/*
package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Setter
@Getter
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;
    private String QTitle;

    @Column(name = "QText", columnDefinition="BLOB")
    private byte[] QText;

    //Question = 1 -> Answer = 1 
    //Question 단방향으로 Answer 정보 참조 가능.
    @OneToOne
    @JoinColumn(name="ANSWER_ID")
    private Answer answer;

    //User = 1 : Order = many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="email")
    private User user;


}
*/