package basicprojectlab.wbbackend.domain.web.servlet;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import basicprojectlab.wbbackend.domain.member.Member;
import basicprojectlab.wbbackend.domain.member.MemberCreateForm;
import basicprojectlab.wbbackend.domain.member.MemberService;
import basicprojectlab.wbbackend.domain.review.Review;
import basicprojectlab.wbbackend.domain.review.ReviewService;
import basicprojectlab.wbbackend.domain.seat.Seat;
import basicprojectlab.wbbackend.domain.seat.SeatRepository;
import basicprojectlab.wbbackend.domain.seat.SeatService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final SeatRepository seatRepository;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private final SeatService seatService;
    private final MessageSource ms;

    @GetMapping("/api/username")
    public String getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    @GetMapping("/api/userId")
    public Long getUserId() {
        return memberService.findMemberIdByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("/api/register")
    public ResponseEntity<Object> createMember(@Validated @RequestBody MemberCreateForm form, BindingResult bindingResult,
                                               HttpServletResponse resp, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            log.info("{}", fieldErrors);
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }

        //성공로직
        memberService.createMember(form.getUsername(), form.getPassword());
        return ResponseEntity.ok("회원가입 성공");
    }

//    @GetMapping("/api/login")
//    public void returnToLogin(HttpServletResponse resp) throws IOException {
//        resp.sendRedirect("/login");
//    }

    @PostMapping("/api/create/review")
    public void createReview(@RequestParam String title, @RequestParam String content,
                             @RequestParam("seat") Long seatId, HttpServletResponse resp,
                             @RequestParam MultipartFile image) throws IOException {

        Member writer = memberService.findMemberByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Seat seat = seatRepository.findById(seatId).get();

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/images/";
//        String projectPath = System.getProperty("user.dir") + "\\ src\\main\\resources\\static\\files";
//        String projectPath = System.getProperty("user.dir") + "/static/images/";
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + image.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        image.transferTo(saveFile);

        Review review = new Review(title, content, writer, seat, fileName);
        reviewService.createReview(review);
        resp.sendRedirect("/");

        // save 시 리턴되는 것은 그냥 내가 넣은 엔티티 그 자체.
        // DB 에 매핑 시킨 값을 가져오려면 find 해야함.
        // 쿼리 수 줄이려면 그냥 setSeatId, setWriterId 해주는게 나을지도 아 그런데 seatId 는 insertable false 라 안됨.
    }

    @GetMapping("/api/seat/{seatId}")
    public List<Review> getReviewsBySeatId(@PathVariable Long seatId) {
        return seatService.getReviewsBySeatId(seatId);
    }

    @GetMapping("/api/get/review/{reviewId}")
    public Review getReview(@PathVariable Long reviewId) {
        return reviewService.findReviewById(reviewId);
    }

    @Transactional
    @PostMapping("/api/edit/review/{reviewId}")
    public void editReview(@PathVariable Long reviewId, @RequestParam String title, @RequestParam String content, HttpServletResponse response) throws IOException {
        Review review = reviewService.findReviewById(reviewId);
        if (SecurityContextHolder.getContext().getAuthentication().getName() != review.getWriter().getUsername()) {
            // 프론트 단에서 수정 버튼이 표시 되지 않지만, api 주소 보여도 로그인 안되면 수정 안되도록 설정.
            return;
        }
        review.setTitle(title);
        review.setContent(content);
        response.sendRedirect("/"); // 어떤 좌석의 리뷰를 수정했는지 URI 가져와서 리다이렉트 돌리도록 수정해야함
    }

    @DeleteMapping("/api/delete/review/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId, HttpServletResponse resp) throws IOException {
        Review review = reviewService.findReviewById(reviewId);
        if (SecurityContextHolder.getContext().getAuthentication().getName() != review.getWriter().getUsername()) {
           // 프론트 단에서 삭제 버튼이 표시 되지 않지만, api 주소 보여도 로그인 안되면 삭제 안되도록 설정.
            return;
        }
        String filePath = System.getProperty("user.dir") +
                "/out/production/resources/static/images/" + review.getFileName();
        File file = new File(filePath);
        file.delete();
        reviewService.deleteReviewById(reviewId);
    }

//    @GetMapping("/api/images/{imageName}")
//    public ResponseEntity<byte[]> showImage(@PathVariable String imageName) {
//        File file=new File(System.getProperty("user.dir") + "/out/production/resources/static/images/", imageName);
//        ResponseEntity<byte[]> result = null;
//        try {
//            HttpHeaders headers=new HttpHeaders();
//            headers.add("Content-Type", Files.probeContentType(file.toPath()));
//            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    @GetMapping("/api/images/{imageName}")
    public Resource downloadImage(@PathVariable String imageName) throws
            MalformedURLException {
        return new UrlResource("file:" + System.getProperty("user.dir") + "/src/main/resources/static/images/" + imageName);
//        return new UrlResource("file:" + System.getProperty("user.dir") + "/static/images/" + imageName);
    }

    @PostConstruct
    public void setup() {
        seatRepository.save(new Seat());
        seatRepository.save(new Seat());
    }
}
