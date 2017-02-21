package netodevel.com.br.safety;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.rogalabs.restfactory.RestFactory;
import com.rogalabs.restfactory.annotations.Rest;

import netodevel.com.br.safety.client.SuggestionClient;
import netodevel.com.br.safety.domain.Suggestion;
import netodevel.com.br.test_safety.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author NetoDevel
 */
public class SuggestionActivity extends Activity {

    private EditText inputName;
    private EditText inputDescription;
    private EditText inputReasonToLearn;

    @Rest(baseUrl = "https://teste-safety.herokuapp.com/api/v1/")
    private SuggestionClient suggestionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        RestFactory.make(this);
        initView();

        Intent intent = getIntent();
        final String name = intent.getStringExtra("username");

        findViewById(R.id.btn_send_suggestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Suggestion> suggestionCall = suggestionClient.createSuggestion(buildSuggestion(name));
                suggestionCall.enqueue(new Callback<Suggestion>() {
                    @Override
                    public void onResponse(Call<Suggestion> call, Response<Suggestion> response) {
                        Log.i("response", String.valueOf(response.code()));
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        intent.putExtra("username", name);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Suggestion> call, Throwable t) {
                        Log.d("Fail", "" , t);
                    }
                });
            }
        });
    }

    public Suggestion buildSuggestion(String userName) {
        Suggestion suggestion = new Suggestion();
        suggestion.setName(inputName.getText().toString());
        suggestion.setDescription(inputDescription.getText().toString());
        suggestion.setReasonToLearn(inputReasonToLearn.getText().toString());

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        suggestion.setImei(telephonyManager.getDeviceId());

        suggestion.setUsername(userName);

        return suggestion;
    }

    public void initView() {
        inputName = (EditText) findViewById(R.id.input_name);
        inputDescription = (EditText) findViewById(R.id.input_descricao);
        inputReasonToLearn = (EditText) findViewById(R.id.input_reason);
    }

}
