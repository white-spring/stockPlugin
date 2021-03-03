package white;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinaStockTest {

    @Test
    void test1() {
        SinaStock sinaStock = new SinaStock();
        try {
            sinaStock.test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}