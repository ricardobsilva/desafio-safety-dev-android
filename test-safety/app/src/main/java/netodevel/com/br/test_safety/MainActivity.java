package netodevel.com.br.test_safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rogalabs.lib.Callback;
import com.rogalabs.lib.LoginView;
import com.rogalabs.lib.model.SocialUser;

import org.jetbrains.annotations.NotNull;

import netodevel.com.br.test_safety.fragments.HomeFragment;

/**
 * @author NetoDevel
 */
public class MainActivity extends LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.facebook_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFacebook(new Callback() {
                    @Override
                    public void onSuccess(@NotNull SocialUser socialUser) {
                        buildHomeActivity();
                    }
                    @Override
                    public void onError(@NotNull Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }

        });

    }

    private void buildHomeActivity() {
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }
}
