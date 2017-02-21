package netodevel.com.br.safety;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rogalabs.restfactory.RestFactory;
import com.rogalabs.restfactory.annotations.Rest;

import netodevel.com.br.safety.client.SuggestionClient;
import netodevel.com.br.safety.controller.UserDataBaseController;
import netodevel.com.br.safety.domain.Suggestion;
import netodevel.com.br.test_safety.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author NetoDevel
 */
public class HomeActivity extends Activity {

    private TextView userName;
    private TextView suggestionTxt;

    @Rest(baseUrl = "https://teste-safety.herokuapp.com/api/v1/")
    private SuggestionClient suggestionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RestFactory.make(this);
        initView();

        Intent intent = getIntent();
        final String name = intent.getStringExtra("username");
        userName.setText(String.format("NAME : %s", name));

        findViewById(R.id.btn_suggestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<Suggestion> suggestion = suggestionClient.getSuggestion();
                suggestion.enqueue(new Callback<Suggestion>() {
                    @Override
                    public void onResponse(Call<Suggestion> call, Response<Suggestion> response) {
                        suggestionTxt.setText("Nome: " + response.body().getName() + "\n"
                                            + "Descrição: " + response.body().getDescription()+ "\n"
                                            + "Razão para aprender: " + response.body().getReasonToLearn() + "\n"
                                            + "Cadastrada por: " + response.body().getUsername());
                    }

                    @Override
                    public void onFailure(Call<Suggestion> call, Throwable t) {
                        Log.d("Error", "", t);
                    }
                });

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SuggestionActivity.class);
                intent.putExtra("username", name);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout(getBaseContext(), name);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    findViewById(R.id.btn_suggestion).callOnClick();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    public void initView() {
        userName = (TextView) findViewById(R.id.user_name);
        suggestionTxt = (TextView) findViewById(R.id.suggestion);
    }

    private void logout(Context context, String userName) {
        UserDataBaseController userDataBaseController = new UserDataBaseController(context);
        userDataBaseController.delete(userName);
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

}
