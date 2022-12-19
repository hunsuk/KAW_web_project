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
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    private Long id;
    private String ATitle;
    @Column(name = "AText", columnDefinition="BLOB")
    private byte[] AText;

    //User = 1 : Order = many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="email")
    private User user;

}
