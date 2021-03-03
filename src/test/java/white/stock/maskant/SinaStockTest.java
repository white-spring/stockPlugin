package white.stock.maskant;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import white.stock.entity.Stock;

class SinaStockTest {

    @Test
    void query() {
        success();
        failed();
    }

    private void success() {
        SinaStock sinaStock = new SinaStock();
        List<String> codes = new ArrayList<>();
        codes.add("sh601001");
        codes.add("sh601003");
        List<Stock> stocks = sinaStock.search(codes);
        Assertions.assertEquals(stocks.size(), 2);
    }

    private void failed() {
        SinaStock sinaStock = new SinaStock();
        List<String> codes = new ArrayList<>();
        codes.add("391928");
        List<Stock> stocks = sinaStock.search(codes);
        Assertions.assertEquals(new Stock(),stocks.get(0));
    }
}