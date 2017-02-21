package netodevel.com.br.safety;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rogalabs.lib.Callback;
import com.rogalabs.lib.LoginView;
import com.rogalabs.lib.model.SocialUser;

import org.jetbrains.annotations.NotNull;

import netodevel.com.br.safety.controller.UserDataBaseController;
import netodevel.com.br.safety.domain.User;
import netodevel.com.br.safety.R;

/**
 * @author NetoDevel
 */
public class MainActivity extends LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildLoggedActivity(getBaseContext());

        findViewById(R.id.facebook_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFacebook(new Callback() {
                    @Override
                    public void onSuccess (@NotNull SocialUser socialUser) {
                        saveUserDataBase(getBaseContext(), socialUser.getName());
                        buildHomeActivity(socialUser.getName());
                    }
                    @Override
                    public void onError(@NotNull Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        });
    }

    private void buildHomeActivity(String userName) {
        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }

    private void saveUserDataBase(Context context, String userName) {
        UserDataBaseController userDataBaseController = new UserDataBaseController(context);
        if (userDataBaseController.validateLogged()== false) {
            userDataBaseController.save(userName);
        }
    }

    private void buildLoggedActivity(Context context) {
        UserDataBaseController userDataBaseController = new UserDataBaseController(context);
        if (userDataBaseController.validateLogged()){
            User user = userDataBaseController.findOne();
            buildHomeActivity(user.getName());
        }
    }

}
