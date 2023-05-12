package swacademy.wbbackend.domain.seat;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.*;

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