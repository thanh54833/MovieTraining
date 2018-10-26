package com.example.thanh.movietraining.retrofix.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haihung on 10/3/16.
 */
public class APIManager {

    public static final String API_BASE_URL = "http://training-movie.bsp.vn:82";
    public static String ACCESS_TOKEN = null;
    private static String APP_TOKEN = "dCuW7UQMbdvpcBDfzolAOSGFIcAec11a";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createServiceWithAuth(Class<S> serviceClass) {
        if (ACCESS_TOKEN != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("access_token", ACCESS_TOKEN)
                            .header("app_token", APP_TOKEN)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass) {

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("app_token", APP_TOKEN)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }

}
