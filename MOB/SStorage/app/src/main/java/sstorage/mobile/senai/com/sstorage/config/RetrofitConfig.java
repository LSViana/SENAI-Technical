package sstorage.mobile.senai.com.sstorage.config;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;

public class RetrofitConfig {

    private Retrofit retrofit;
    private String token;

    public RetrofitConfig() {
        this(null);
    }

    public RetrofitConfig(final String token) {
        Retrofit.Builder builder = new Retrofit.Builder()
                // Defines the base URL to the application
                .baseUrl(AppUtils.API_ROOT)
                // Adding the JSON Converter that will work together with Retrofit GSON Converter
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
                                .create()
                ));
        // Dealing with Token
        this.token = token;
        if(token != null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder b = chain.request().newBuilder();
                        b.addHeader("Accept", "application/json");
                        b.addHeader("Authorization", token);
                        return chain.proceed(b.build());
                    }
                }).build();
            // Adding OkHttpClient
            builder.client(okHttpClient);
        }

        this.retrofit = builder
                // Finalizes the building chain
                .build();
    }

    public ApiContext getApiContext() {
        return retrofit.create(ApiContext.class);
    }
}
