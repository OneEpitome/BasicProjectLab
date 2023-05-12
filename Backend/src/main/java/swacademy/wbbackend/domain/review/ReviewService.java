package swacademy.wbbackend.domain.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void createReview(String title, String content) {
        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        reviewRepository.save(review);
    }

    @Transactional
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Transactional
    public void deleteReviewById(Long id) {
        Review review = reviewRepository.findById(id).get();
        review.getWriter().getReviewList().remove(review);
        review.getSeat().getReviewList().remove(review);
        reviewRepository.delete(review);
    }
}
