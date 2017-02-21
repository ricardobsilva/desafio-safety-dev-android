package com.example.lucastimotio.developmentcommunity.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucastimotio.developmentcommunity.R;
import com.example.lucastimotio.developmentcommunity.activity.MainActivity;
import com.example.lucastimotio.developmentcommunity.api.SuggestionAPI;
import com.example.lucastimotio.developmentcommunity.domain.Suggestion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucastimotio on 19/02/17.
 */

public class PressVolumeLowButton extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://teste-safety.herokuapp.com/api/v1/suggestions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SuggestionAPI apiService = retrofit.create(SuggestionAPI.class);

        Call<Suggestion> call = apiService.get();
        call.enqueue(new Callback<Suggestion>() {
            public Suggestion suggestion;
            @Override
            public void onResponse(Call<Suggestion> call, Response<Suggestion> response) {
                int statusCode = response.code();
                Suggestion suggestion = response.body();

                TextView textViewNameLang = (TextView) ((Activity) context).findViewById(R.id.textViewNameLang);
                textViewNameLang.setText(suggestion.getName());

                TextView textViewDescription = (TextView) ((Activity) context).findViewById(R.id.textViewDescription);
                textViewDescription.setText(suggestion.getDescription());

                TextView textViewReasonToLearn = (TextView) ((Activity) context).findViewById(R.id.textViewReasonToLearn);
                textViewReasonToLearn.setText(suggestion.getReason_to_learn());

                TextView textViewUsername = (TextView) ((Activity) context).findViewById(R.id.textViewUserName);
                textViewUsername.setText(" " + suggestion.getUsername());

                TextView textViewCreated = (TextView) ((Activity) context).findViewById(R.id.textViewCreated);
                textViewCreated.setText(" " + suggestion.getCreated_at().substring(8,10) + "-" + suggestion.getCreated_at().substring(5,8) + suggestion.getCreated_at().substring(0,4));

            }
            @Override
            public void onFailure(Call<Suggestion> call, Throwable t) {
                // Log error here since request failed
            }
        });

    }

}
