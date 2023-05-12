package basicprojectlab.wbbackend.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import basicprojectlab.wbbackend.domain.review.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "{NotBlank.member.username}")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    // String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "review_list")
    private List<Review> reviewList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Role authority;

    public String getAuthority() {
        return this.authority.name();
    }
}
