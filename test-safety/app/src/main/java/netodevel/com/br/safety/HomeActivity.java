package netodevel.com.br.safety;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import netodevel.com.br.safety.controller.UserDataBaseController;
import netodevel.com.br.test_safety.R;

/**
 * @author NetoDevel
 */
public class HomeActivity extends Activity {

    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        Intent intent = getIntent();
        final String name = intent.getStringExtra("username");
        userName.setText(String.format("NAME : %s", name));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout(getBaseContext(), name);
            }
        });
    }

    public void initView() {
        userName = (TextView) findViewById(R.id.user_name);
    }

    private void logout(Context context, String userName) {
        UserDataBaseController userDataBaseController = new UserDataBaseController(context);
        userDataBaseController.delete(userName);
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

}
