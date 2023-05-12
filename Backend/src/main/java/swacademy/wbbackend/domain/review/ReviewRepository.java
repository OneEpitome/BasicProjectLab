package swacademy.wbbackend.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findReviewByTitle(String title);
    Optional<Review> findReviewBySeatId(Long seatId);
}
