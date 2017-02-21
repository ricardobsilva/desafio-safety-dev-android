package com.example.lucastimotio.developmentcommunity.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucastimotio.developmentcommunity.R;
import com.example.lucastimotio.developmentcommunity.api.SuggestionAPI;
import com.example.lucastimotio.developmentcommunity.domain.Suggestion;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuggestionActivity extends AppCompatActivity {

    private static final int REQUEST_READ_PHONE_STATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggestion, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent mIntent = new Intent(this, MainActivity.class);
        startActivity(mIntent);
        finish();
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                Intent mIntent = new Intent(this, MainActivity.class);

                startActivity(mIntent);

                finish();

                break;

            case R.id.action_save:

                final String[] username = {""};

                if (Profile.getCurrentProfile() != null) {
                    Profile profile = Profile.getCurrentProfile();
                    username[0] = profile.getFirstName() + " " + profile.getLastName();

                } else {
                    ProfileTracker profileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                            stopTracking();
                            Profile.setCurrentProfile(currentProfile);
                            Profile profile = Profile.getCurrentProfile();
                            username[0] = profile.getFirstName() + " " + profile.getLastName();

                        }
                    };
                    profileTracker.startTracking();
                }

                int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                }



                TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                String imei  = telephonyManager.getDeviceId(1);

                if (imei == null){

                    imei = "Enviado via emulador.";

                }


                EditText name = (EditText) findViewById(R.id.editTextName);
                EditText description = (EditText) findViewById(R.id.editTextDescription);
                EditText reason_to_learn = (EditText) findViewById(R.id.editTextReasonToLearn);

                Suggestion suggestion = new Suggestion(
                        name.getText().toString(),
                        description.getText().toString(),
                        imei,
                        reason_to_learn.getText().toString(),
                        username[0]);

                goCallApi(suggestion);

                goMain();

                break;

            default:break;
        }

        return true;
    }

    public void goCallApi(Suggestion suggestion){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://teste-safety.herokuapp.com/api/v1/suggestions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SuggestionAPI apiService =
                retrofit.create(SuggestionAPI.class);
        Call<Suggestion> call = apiService.post(suggestion);
        call.enqueue(new Callback<Suggestion>() {
            public Suggestion suggestion;
            @Override
            public void onResponse(Call<Suggestion> call, Response<Suggestion> response) {

                Context context = getApplicationContext();
                CharSequence text = "Sugestão recebida com sucesso!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
            @Override
            public void onFailure(Call<Suggestion> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }

    public void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
