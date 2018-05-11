package intro.api.senai.com.apiusage.retrofit.config;

import intro.api.senai.com.apiusage.utils.AppUtils;
import intro.api.senai.com.apiusage.utils.CEPRestService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                // Defining URL to be considered as base
            .baseUrl(AppUtils.API_CEP_URL)
                // Adding Jackson JSON Library
            .addConverterFactory(JacksonConverterFactory.create())
                // Returning the Retrofit Object
            .build();
    }

    public CEPRestService getCEPRest() {
        return retrofit.create(CEPRestService.class);
    }

}
