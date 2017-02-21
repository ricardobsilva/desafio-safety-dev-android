package com.example.lucastimotio.developmentcommunity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lucastimotio.developmentcommunity.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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

            default:break;
        }

        return true;
    }

}
