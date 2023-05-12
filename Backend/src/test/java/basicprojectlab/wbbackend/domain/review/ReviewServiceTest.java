package basicprojectlab.wbbackend.domain.review;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import basicprojectlab.wbbackend.domain.member.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Test
    void createReview() {
        //given

        //when
        reviewService.createReview("title1", "good");

        //then
        assertThat(reviewRepository.findReviewByTitle("title1").get().getContent()).isEqualTo("good");
    }

    @Test
    @Transactional
    void updateReviewTest() {
        //given
        reviewService.createReview("Title1", "Content1");

        //when
        Review title1 = reviewRepository.findReviewByTitle("Title1").get();
        title1.setContent("Content2");

        //then
        Review newReview = reviewRepository.findReviewByTitle("Title1").get();
        assertThat(newReview.getContent()).isEqualTo("Content2");
    }
}