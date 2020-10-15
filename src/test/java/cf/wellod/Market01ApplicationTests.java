package cf.wellod;

import cf.wellod.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Market01ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(DateUtil.getCurrDateDay());

    }

}
