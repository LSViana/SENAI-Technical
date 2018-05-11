package intro.api.senai.com.apiusage.utils;

import intro.api.senai.com.apiusage.models.CEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPRestService {

    @GET("{cep}")
    Call<CEP> get(@Path("cep") Long id);

}
