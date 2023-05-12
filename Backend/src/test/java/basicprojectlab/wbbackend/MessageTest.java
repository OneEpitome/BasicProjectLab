//package swacademy.wbbackend;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.MessageSource;
//
//import java.util.Locale;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//public class MessageTest {
//
//    @Autowired
//    MessageSource ms;
//
//    @Test
//    @DisplayName("메세지의 안녕이 정상 출력되는 지 확인합니다.")
//    void HelloMessageTest() {
//        String hello = ms.getMessage("hello", null, null);
//        assertThat(hello).isEqualTo("안녕");
//    }
//
//    @Test
//    @DisplayName("메세지의 안녕이 정상 출력되는 지 확인합니다.")
//    void HelloMessageENTest() {
//        String hello = ms.getMessage("hello", null, Locale.ENGLISH);
//        assertThat(hello).isEqualTo("hello_en");
//    }
//}
