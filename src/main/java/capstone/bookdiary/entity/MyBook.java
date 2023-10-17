package capstone.bookdiary.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MyBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
