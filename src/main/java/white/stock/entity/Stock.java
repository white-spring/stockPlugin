package white.stock.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author baichunqiang
 */

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings({"AlibabaCommentsMustBeJavadocFormat"})
public class Stock {
    private String code;
    private String name;
    // 现价,开盘价,昨日收盘价
    private String price;
    private String openPrice;
    private String yesterdayPrice;
    // 今日最高价,最低价
    private String highPrice;
    private String lowestPrice;
    // 成交量
    private String turnover;
    // 成交金额（万元）
    private String turnoverPrice;
    // 竞标价格买1-买5
    private String buyPrice1;
    private String buyPrice2;
    private String buyPrice3;
    private String buyPrice4;
    private String buyPrice5;
    // 买数量1-5
    private String buyNum1;
    private String buyNum2;
    private String buyNum3;
    private String buyNum4;
    private String buyNum5;
    // 卖价1-5,数量1-5
    private String sellPrice1;
    private String sellPrice2;
    private String sellPrice3;
    private String sellPrice4;
    private String sellPrice5;
    private String sellNum1;
    private String sellNum2;
    private String sellNum3;
    private String sellNum4;
    private String sellNum5;

    private String date;
    private String time;
    private LocalDateTime dateTime;
}
