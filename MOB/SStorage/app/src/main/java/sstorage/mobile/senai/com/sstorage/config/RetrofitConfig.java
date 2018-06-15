package sstorage.mobile.senai.com.sstorage.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sstorage.mobile.senai.com.sstorage.data.ApiContext;
import sstorage.mobile.senai.com.sstorage.utils.AppUtils;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                // Defines the base URL to the application
                .baseUrl(AppUtils.APIROOT)
                // Adding the JSON Converter that will work together with Retrofit GSON Converter
                .addConverterFactory(GsonConverterFactory.create())
                // Finalizes the building chain
                .build();
    }

    public ApiContext getApiContext() {
        return retrofit.create(ApiContext.class);
    }
}
