//package swacademy.wbbackend.domain.heart;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import swacademy.wbbackend.domain.member.Member;
//import swacademy.wbbackend.domain.review.Review;
//
//@Getter @Setter
//@NoArgsConstructor
//@Entity
//public class Heart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @Column(name = "member_id")
//    private Long memberId;
//
//    @Column(name = "review_id")
//    private Long reviewId;
//
//    public void setMemberId(Long memberId) {
//        this.memberId = memberId;
//    }
//
//    public void setMemberId(Member member) {
//        this.memberId = member.getId();
//    }
//
//    public void setReviewId(Long reviewId) {
//        this.reviewId = reviewId;
//    }
//
//    public void setReviewId(Review review) {
//        this.reviewId = review.getId();
//    }
//}