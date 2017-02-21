package netodevel.com.br.safety.client;


import netodevel.com.br.safety.domain.Suggestion;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author NetoDevel
 */
public interface SuggestionClient {

    @GET("suggestions")
    Call<Suggestion> getSuggestion();

}
