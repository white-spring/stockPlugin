package white.stock.maskant;

import java.util.List;
import white.stock.entity.Stock;

/**
 * @author baichunqiang
 */
public interface StockService {
    /**
     * 查询股票信息
     * @param codes 股票代码集合，比如sh00001
     * @return
     */
    List<Stock> search(List<String> codes);
}
