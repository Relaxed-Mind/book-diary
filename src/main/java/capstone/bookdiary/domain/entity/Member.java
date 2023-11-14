package capstone.bookdiary.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity{
    //TODO: 컬럼 추가 및 세부지정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String password;
    private Character isUsed;
    private String role;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.isUsed = 'Y';
        this.role = "ROLE_USER";
    }
}
