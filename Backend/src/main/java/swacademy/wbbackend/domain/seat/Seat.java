package swacademy.wbbackend.domain.seat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import swacademy.wbbackend.domain.review.Review;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonManagedReference
    @Column(name = "review_list")
    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviewList = new ArrayList<>();
}
