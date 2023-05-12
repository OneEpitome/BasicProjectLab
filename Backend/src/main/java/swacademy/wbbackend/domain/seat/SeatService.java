package swacademy.wbbackend.domain.seat;

import org.springframework.stereotype.Service;
import swacademy.wbbackend.domain.review.Review;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void createSeat() {
        seatRepository.save(new Seat());
    }

    public List<Review> getReviewsBySeatId(Long seatId) {
        Seat seat = seatRepository.findById(seatId).get();
        List<Review> reviewList = seat.getReviewList();
        return reviewList;
    }
}
