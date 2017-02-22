package netodevel.com.br.safety.client;


import netodevel.com.br.safety.domain.Suggestion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author NetoDevel
 */
public interface SuggestionClient {

    @GET("suggestions")
    Call<Suggestion> getSuggestion();

    @POST("suggestions")
    Call<Suggestion> createSuggestion(@Body Suggestion suggestion);

}
