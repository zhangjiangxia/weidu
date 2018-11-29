package com.bw.movie.model.http;

import android.content.Context;
import android.content.SharedPreferences;

import com.bw.movie.model.utils.app.APP;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpUtil {
    private static OkHttpUtil ourInstance ;

    public ApiService apiService;

    public static OkHttpUtil getInstance() {
        Context context = null;

        if (context == null) {
            APP app = APP.getApp();
            context = app.getApplicationContext();
        }
        if(ourInstance == null){
            synchronized (OkHttpUtil.class){
                if (ourInstance==null){
                    ourInstance = new OkHttpUtil(context);
                }
            }
        }
        return ourInstance;
    }

    private OkHttpUtil(final Context context) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder();
                        SharedPreferences sp = context.getSharedPreferences("userInfo", 0);
                        final String userId = sp.getString("userId", null);
                        final String sessionId = sp.getString("sessionId", null);
                        builder.addHeader("ak", "0110010010000");
                        builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                        if (userId != null) {
                            if (sessionId != null) {
                                builder.addHeader("userId", userId);
                                builder.addHeader("sessionId", sessionId);
                            }
                        }
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.GETURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            Response response = chain.proceed(request);
            long l = System.nanoTime();
            return response;
        }
    }

//    public final ApiService apiService;
//    private static OkHttpUtil ourInstance;
//
//    public static OkHttpUtil getInstance() {
//        Context context = null;
//        if (context == null) {
//            APP app = APP.getApp();
//            context = app.getApplicationContext();
//        }
//        if (ourInstance == null) {
//            synchronized (OkHttpUtil.class) {
//                if (ourInstance == null) {
//                    ourInstance = new OkHttpUtil(context);
//                }
//            }
//        }
//        return ourInstance;
//    }
//
//    public OkHttpUtil(final Context context) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor(new LoggingInterceptor())
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        Request.Builder builder = request.newBuilder();
//                        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                        int userId = sp.getInt("userId", 0);
//                        String s = String.valueOf(userId);
//                        String sessionId = sp.getString("sessionId", "");
//                        //Log.i("sessionId", "intercept: ======="+sessionId);
//                        if (s != null) {
//                            if (sessionId != null) {
//                                builder.addHeader("userId", s);
//                                builder.addHeader("sessionId", sessionId);
////                                Log.i("hhhh", "intercept: sssss" + userId + "yang" + sessionId);
//                            }
//                        }
//
//                        Request build = builder.build();
//                        return chain.proceed(build);
//                    }
//                }).addNetworkInterceptor(new LoggingInterceptor())
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(Api.GETURL)
//                .client(okHttpClient)
//                .build();
//        apiService = retrofit.create(ApiService.class);
//    }
//
//    class LoggingInterceptor implements Interceptor {
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
//            Request request = chain.request();
//            long t1 = System.nanoTime();//请求发起的时间
//            Response response = chain.proceed(request);
//            long l = System.nanoTime();
//            return response;
//
//        }
//    }

}

