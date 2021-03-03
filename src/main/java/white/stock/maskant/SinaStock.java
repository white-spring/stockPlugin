package white.stock.maskant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import white.stock.entity.Stock;

/**
 * @author baichunqiang
 */
public class SinaStock implements StockService {
    private static final String SEPARATOR = ";";
    private static final String PREFIX = "hq_str_";
    private static final String SUFFIX = "=";
    private static final String FAILED_CODE = "FAILED";
    private static final String SEARCH_URL = "http://hq.sinajs.cn/list=";

    @Override
    public List<Stock> search(List<String> codes) {
        if (CollectionUtils.isEmpty(codes)) {
            return Collections.emptyList();
        }
        String param = String.join(",", codes);
        OkHttpClient client = new OkHttpClient();
        OkHttpClient eagerClient = client.newBuilder()
                .readTimeout(500, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .get()
                .url(SEARCH_URL + param)
                .build();
        ResponseBody responseBody = null;
        String result = null;
        try {
            responseBody = eagerClient.newCall(request).execute().body();
            if (responseBody != null) {
                result = responseBody.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return handleResponse(result);
    }

    List<Stock> handleResponse(String responseStr) {
        if (responseStr == null) {
            return Collections.emptyList();
        }
        List<Stock> stocks = new ArrayList<>();
        if (responseStr.contains(SEPARATOR)) {
            String[] sks = responseStr.trim().split(SEPARATOR);
            for (String sk : sks) {
                stocks.add(translate(sk.trim()));
            }
            return stocks;
        }
        stocks.add(translate(responseStr));
        return stocks;
    }

    /**
     * 接口返回数据例子
     * var hq_str_sh601006="大秦铁路, 27.55, 27.25, 26.91, 27.55, 26.20, 26.91, 26.92,
     * 22114263, 589824680, 4695, 26.91, 57590, 26.90, 14700, 26.89, 14300,
     * 26.88, 15100, 26.87, 3100, 26.92, 8900, 26.93, 14230, 26.94, 25150, 26.95, 15220, 26.96, 2008-01-11, 15:05:32";
     * <p>
     * 具体字段说明：
     * 0：”大秦铁路”，股票名字；
     * 1：”27.55″，今日开盘价；
     * 2：”27.25″，昨日收盘价；
     * 3：”26.91″，当前价格；
     * 4：”27.55″，今日最高价；
     * 5：”26.20″，今日最低价；
     * 6：”26.91″，竞买价，即“买一”报价；
     * 7：”26.92″，竞卖价，即“卖一”报价；
     * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
     * 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
     * 10：”4695″，“买一”申请4695股，即47手；
     * 11：”26.91″，“买一”报价；
     * 12：”57590″，“买二”
     * 13：”26.90″，“买二”
     * 14：”14700″，“买三”
     * 15：”26.89″，“买三”
     * 16：”14300″，“买四”
     * 17：”26.88″，“买四”
     * 18：”15100″，“买五”
     * 19：”26.87″，“买五”
     * 20：”3100″，“卖一”申报3100股，即31手；
     * 21：”26.92″，“卖一”报价
     * (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
     * 30：”2008-01-11″，日期；
     * 31：”15:05:32″，时间；
     *
     * @param oneStock 一只股票的内容
     * @return 股票详情
     */
    @Nonnull
    Stock translate(String oneStock) {
        if (StringUtils.isBlank(oneStock)) {
            return new Stock();
        }
        String code = oneStock.substring(oneStock.indexOf(PREFIX) + 7,oneStock.indexOf(SUFFIX));
        String values = oneStock.substring(oneStock.indexOf("\"") + 1, oneStock.lastIndexOf("\""));
        if (FAILED_CODE.equals(values)) {
            return new Stock();
        }
        String[] d = values.split(",");
        return Stock.builder()
                .code(code)
                .name(d[0])
                .openPrice(d[1])
                .yesterdayPrice(d[2])
                .price(d[3])
                .highPrice(d[4])
                .lowestPrice(d[5])
                .buyPrice1(d[6])
                .sellPrice1(d[7])
                .turnover(d[8])
                .turnoverPrice(d[9])
                .buyNum1(d[10])
                .buyPrice1(d[11])
                .buyNum2(d[12])
                .buyPrice2(d[13])
                .buyNum3(d[14])
                .buyPrice3(d[15])
                .buyNum4(d[16])
                .buyPrice4(d[17])
                .buyNum5(d[18])
                .buyPrice5(d[19])
                .sellNum1(d[20])
                .sellPrice1(d[21])
                .sellNum2(d[22])
                .sellPrice2(d[23])
                .sellNum3(d[24])
                .sellPrice3(d[25])
                .sellNum4(d[26])
                .sellPrice4(d[27])
                .sellNum5(d[28])
                .sellPrice5(d[29])
                .date(d[30])
                .time(d[31])
                .build();
    }

}
