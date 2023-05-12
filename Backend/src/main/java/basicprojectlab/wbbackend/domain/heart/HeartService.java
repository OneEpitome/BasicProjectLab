//package swacademy.wbbackend.domain.heart;
//
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import swacademy.wbbackend.domain.member.Member;
//import swacademy.wbbackend.domain.review.Review;
//
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Service
//public class HeartService {
//    private HeartRepository heartRepository;
//
//    public boolean doesMemberHeartThisReview(Member member, Review review) {
//        Long memberId = member.getId();
//        Long reviewId = review.getId();
//        return heartRepository.findHeartByMemberIdAndReviewId(memberId, reviewId).isPresent();
//    }
//
//    public boolean insert(Member member, Review review) {
//        Long memberId = member.getId();
//        Long reviewId = review.getId();
//
//        if (heartRepository.findHeartByMemberIdAndReviewId(memberId, reviewId).isPresent()) {
//            return false;
//        }
//
//        Heart heart = new Heart();
//        heart.setMemberId(member);
//        heart.setReviewId(review);
//        heartRepository.save(heart);
//        return true;
//    }
//}
