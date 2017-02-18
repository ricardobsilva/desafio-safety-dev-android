package netodevel.com.br.test_safety.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import netodevel.com.br.test_safety.R;

/**
 * @author NetoDevel
 */
public class HomeFragment extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.home_layout);
    }
}
