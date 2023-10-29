package nasobesapi.database.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sandbox")
@ToString
@Data /** автоматически создает сетеры и гетеры для всех епременных */
public class SandboxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Column(name = "number")
    private Integer number;

    @NotNull
    @Column(columnDefinition = "text", name = "answer")
    private String answer;

    @NotNull
    @Column(name = "question")
    private String question;

    @NotNull
    @Column(name = "grade")
    private String grade;

    @NotNull
    @Column(name = "dev_specification")
    private String devSpecification;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
