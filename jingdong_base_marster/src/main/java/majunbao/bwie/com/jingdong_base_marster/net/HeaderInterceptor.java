package majunbao.bwie.com.jingdong_base_marster.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import majunbao.bwie.com.jingdong_base_marster.utils.SharedPreferencesUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HeaderInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

//
//        Request.Builder builder = chain.request().newBuilder().addHeader("userId", (String) SharedPreferencesUtils.getParam("userId","")).addHeader("sessionId",(String) SharedPreferencesUtils.getParam("sessionId",""));
//        return chain.proceed(builder.build());

        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间
        Logger.d(String.format("发送请求 %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        Logger.d(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers()));
        return response;

    }
}
