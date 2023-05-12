package basicprojectlab.wbbackend.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "place")
public class Place {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;
}
