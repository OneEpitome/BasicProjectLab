package swacademy.wbbackend.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swacademy.wbbackend.domain.member.Member;
import swacademy.wbbackend.domain.seat.Seat;

@Getter
@Setter
@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "writer_id", insertable = false, updatable = false)
    private Long creatorId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private Member writer;

    @Column(name = "seat_id", insertable = false, updatable = false)
    private Long seatId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    public Review(String title, String content, Member writer, Seat seat, String fileName) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.seat = seat;
        this.fileName = fileName;
    }

    public void setWriter(Member member) {
        this.writer = member;
        member.getReviewList().add(this);
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
        seat.getReviewList().add(this);
    }
}
