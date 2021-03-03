package white;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author baichunqiang
 */
public class SinaStock {
    public void test() throws IOException {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient eagerClient = client.newBuilder()
                .readTimeout(500, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .get()
                .url("http://hq.sinajs.cn/list=sz300170")
                .build();
        ResponseBody responseBody = eagerClient.newCall(request).execute().body();
        System.out.println(responseBody.string());
    }
}
