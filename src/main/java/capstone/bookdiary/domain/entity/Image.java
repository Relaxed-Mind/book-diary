package capstone.bookdiary.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Image extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @OneToOne(fetch = FetchType.LAZY)
    private Scrap scrap;

    private String imageUrl;

    public Image(Scrap scrap, String imageUrl) {
        this.scrap = scrap;
        this.imageUrl = imageUrl;
    }
}
