//package swacademy.wbbackend;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import swacademy.wbbackend.domain.member.Member;
//import swacademy.wbbackend.domain.member.MemberRepository;
//import swacademy.wbbackend.domain.review.Review;
//import swacademy.wbbackend.domain.review.ReviewRepository;
//import swacademy.wbbackend.domain.seat.Seat;
//import swacademy.wbbackend.domain.seat.SeatRepository;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class WbbackendApplicationTests {
//
//    @Autowired
//    private SeatRepository seatRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void createUserWhoHasReviewWhichTaggedBySeat() {
//        Seat seat = new Seat();
//        seatRepository.save(seat);
//
////        Member member = new Member();
//        member.setUsername("ilmo");
//        member.setPassword("123");
//        member.setCreatedAt(LocalDateTime.now());
//        memberRepository.save(member);
//
//        Review review = new Review();
//        review.setTitle("title");
//        review.setContent("content");
//        review.setSeat(seat);
//        review.setWriter(member);
//        reviewRepository.save(review);
//
//
//        assertThat(seatRepository.findAll().get(0).getReviewList().size()).isEqualTo(1);
//        assertThat(reviewRepository.findAll().get(0).getWriter().getUsername()).isEqualTo("ilmo");
//    }
//
//}
