package basicprojectlab.wbbackend.domain.seat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeatServiceTest {

    @Autowired
    private SeatRepository seatRepository;

    @Test
    void getSeat() {
        seatRepository.save(new Seat());
        seatRepository.findById(1L).get();
    }
}