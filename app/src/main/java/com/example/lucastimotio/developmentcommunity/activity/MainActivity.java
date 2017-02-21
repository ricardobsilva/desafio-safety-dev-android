package com.example.lucastimotio.developmentcommunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lucastimotio.developmentcommunity.R;
import com.example.lucastimotio.developmentcommunity.api.SuggestionAPI;
import com.example.lucastimotio.developmentcommunity.domain.Suggestion;
import com.example.lucastimotio.developmentcommunity.util.PressVolumeLowButton;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPost();
            }
        });

        goCheckLogin();

        goCallApi();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            goLogout();
            return true;
        }else if(id == R.id.action_about){
            goAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goPost(){
        Intent intent = new Intent(this, SuggestionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goAbout(){
        Intent intent = new Intent(this, AboutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goLogout() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    public void goCallApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://teste-safety.herokuapp.com/api/v1/suggestions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SuggestionAPI apiService =
                retrofit.create(SuggestionAPI.class);
        Call<Suggestion> call = apiService.get();
        call.enqueue(new Callback<Suggestion>() {
            public Suggestion suggestion;
            @Override
            public void onResponse(Call<Suggestion> call, Response<Suggestion> response) {
                int statusCode = response.code();
                Suggestion suggestion = response.body();

                TextView textViewNameLang = (TextView) findViewById(R.id.textViewNameLang);
                textViewNameLang.setText(suggestion.getName());

                TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);
                textViewDescription.setText(suggestion.getDescription());

                TextView textViewReasonToLearn = (TextView) findViewById(R.id.textViewReasonToLearn);
                textViewReasonToLearn.setText(suggestion.getReason_to_learn());

                TextView textViewUsername = (TextView) findViewById(R.id.textViewUserName);
                textViewUsername.setText(" " + suggestion.getUsername());

                TextView textViewCreated = (TextView) findViewById(R.id.textViewCreated);
                textViewCreated.setText(" " + suggestion.getCreated_at().substring(8,10) + "-" + suggestion.getCreated_at().substring(5,8) + suggestion.getCreated_at().substring(0,4));

            }
            @Override
            public void onFailure(Call<Suggestion> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }

    public void goCheckLogin(){

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }
        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            ProfilePictureView photo = (ProfilePictureView) findViewById(R.id.image);
            photo.setProfileId(profile.getId());
            TextView name = (TextView) findViewById(R.id.textViewName);
            name.setText("Olá, " + profile.getFirstName().toString() + " " + profile.getLastName().toString() + ".");
        } else {
            ProfileTracker profileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                    stopTracking();
                    Profile.setCurrentProfile(currentProfile);
                    Profile profile = Profile.getCurrentProfile();
                    ProfilePictureView photo = (ProfilePictureView) findViewById(R.id.image);
                    photo.setProfileId(profile.getId());
                    TextView name = (TextView) findViewById(R.id.textViewName);
                    name.setText("Olá, " + profile.getFirstName().toString() + " " + profile.getLastName().toString() + ".");
                }
            };
            profileTracker.startTracking();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            Intent itent = new Intent(this, PressVolumeLowButton.class);
            PressVolumeLowButton eventPress = new PressVolumeLowButton();
            eventPress.onReceive(this, itent);
        }
        return true;
    }
}
