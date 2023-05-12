//package swacademy.wbbackend.domain.member;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import swacademy.wbbackend.domain.review.Review;
//import swacademy.wbbackend.domain.review.ReviewRepository;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class MemberServiceTest {
//
//    private static final Logger log = LoggerFactory.getLogger(MemberServiceTest.class);
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Test
//    public void createUser() {
//        //given

//        //when
//        memberService.createMember("ilmo", "123");
//
//        //then
//        Member member = memberRepository.findMemberByUsername("ilmo").get();
//        assertThat(member).isNotNull();
//    }
//
//    @Test
//    public void createUserWhoHasReview() {
//
//        //given
//        Member member = new Member();
//        Review review = new Review();
//        review.setTitle("title1");
//        review.setContent("content1");
//        member.setUsername("ilmo");
//        member.setPassword("123");
//        member.setCreatedAt(LocalDateTime.now());
//        review.setWriter(member); // or member.addReview(review)
//
//        //when
//        memberRepository.save(member); // member 를 save 하면 cascade 에 의해서 review 도 persist 된 후 저장된다.
//
//        //then
//        log.info("saved title is : {}", reviewRepository.findAll().get(0).getTitle());
//        assertThat(memberRepository.findMemberByUsername("ilmo").get().getReviewList().get(0).getTitle()).isEqualTo("title1");
//        assertThat(reviewRepository.findAll().get(0).getTitle()).isEqualTo("title1");
//    }
//
//    @Test
//    public void addReviewToUser() {
//        //given
//        Member member = new Member();
//        member.setUsername("ilmo");
//        member.setPassword("123");
//        member.setCreatedAt(LocalDateTime.now());
//
//        Review review = new Review();
//        review.setTitle("title1");
//        review.setContent("content1");
//        review.setWriter(member); // or member.addReview(review)
//        memberRepository.save(member);
//
//        //when
//        Review newReview = new Review();
//        newReview.setTitle("title2");
//        newReview.setContent("content2");
//        newReview.setWriter(member);
//        reviewRepository.save(newReview);
//
//        //then
//        assertThat(memberRepository.findMemberByUsername("ilmo").get().getReviewList().size()).isEqualTo(2);
//    }
//
//    @Test
//    void findMemberByUsername() {
//        Member member = new Member();
//        member.setUsername("ilmo");
//        member.setPassword("123");
//        member.setCreatedAt(LocalDateTime.now());
//        memberRepository.save(member);
//
//        assertThat(memberRepository.findMemberByUsername("ilmo").get().getUsername()).isEqualTo("ilmo");
//    }
//
//    @AfterEach
//    void cleanup() {
//        memberRepository.deleteAll();
//        reviewRepository.deleteAll();
//    }
//}