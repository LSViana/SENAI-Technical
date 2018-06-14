package sstorage.mobile.senai.com.sstorage.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.model.Movement;
import sstorage.mobile.senai.com.sstorage.model.Patrimony;
import sstorage.mobile.senai.com.sstorage.model.PatrimonyItem;
import sstorage.mobile.senai.com.sstorage.model.User;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.Authentication;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.Login;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.UserNames;
import sstorage.mobile.senai.com.sstorage.model.ViewModel.UserPassword;

public interface ApiContext {

    // Users
    @GET("/users")
    Call<User[]> getUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") Long id);

    @POST("/users")
    Call<User> createUser(@Body User user);

    @DELETE("/users/{id}")
    Call<ResponseBody> deleteUser(@Path("id") Long id);

    @PUT("/users/{id}")
    Call<User> updateUser(@Path("id") Long id, @Body User user);

    @POST("/users/changename")
    Call<UserNames> changeUserNames(@Body UserNames userNames);

    @POST("/users/changepassword")
    Call<ResponseBody> changeUserPassword(@Body UserPassword userPassword);

    // Environments
    @POST("/environments")
    Call<Environment> createEnvironment(@Body Environment environment);

    @GET("/environments")
    Call<Environment[]> getEnvironments();

    @GET("/environments/{id}")
    Call<Environment> getEnvironment(@Path("id") Long id);

    @DELETE("/environments/{id}")
    Call<ResponseBody> deleteEnvironment(@Path("id") Long id);

    @PUT("/environment/{id}")
    Call<Environment> updateEnvironment(@Path("id") Long id, @Body Environment environment);

    // Patrimony Categories
    // NOT YET :)

    // Patrimonies
    @GET("/patrimonies")
    Call<Patrimony[]> getPatrimonies();

    @GET("/patrimonies/{id}")
    Call<Patrimony> getPatrimony(@Path("id") Long id);

    @GET("/patrimonies/items/{id}")
    Call<PatrimonyItem[]> getPatrimonyItemsByPatrimony(@Path("id") Long id);

    @POST("/patrimonies")
    Call<Patrimony> createPatrimony(@Body Patrimony patrimony);

    @PUT("/patrimonies/{id}")
    Call<Patrimony> updatePatrimony(@Path("id") Long id, @Body Patrimony patrimony);

    @DELETE("/patrimonies/{id}")
    Call<ResponseBody> deletePatrimony(@Path("id") Long id);

    // Patrimony Items
    @GET("/patrimonyitems")
    Call<PatrimonyItem[]> getPatrimonyItems();

    @POST("/patrimonyitems/{idPatrimonyItem}/moveto/{idEnvironment}")
    Call<Movement> movePatrimonyItem(@Path("idPatrimonyItem") Long idPatrimonyItem, @Path("idEnvironment") Long idEnvironment);

    @GET("/patrimonyitems/{id}/moves")
    Call<Movement[]> movePatrimonyItem(@Path("id") Long id);

    @GET("/patrimonyitems/{id}/requestdown")
    Call<Movement[]> requestDownPatrimonyItem(@Path("id") Long id);

    @GET("/patrimonyitems/{id}/turndown")
    Call<Movement[]> turnDownPatrimonyItem(@Path("id") Long id);

    @GET("/patrimonyitems/{id}")
    Call<PatrimonyItem> getPatrimonyItem(@Path("id") Long id);

    @POST("/patrimonyitems")
    Call<PatrimonyItem> createPatrimonyItem(@Body PatrimonyItem patrimonyItem);

    @DELETE("/patrimonyitems/{id}")
    Call<ResponseBody> deletePatrimonyItem(@Path("id") Long id);

    // Request Token
    @POST("/auth/jwt")
    Call<Authentication> requestToken(@Body Login login);

}
