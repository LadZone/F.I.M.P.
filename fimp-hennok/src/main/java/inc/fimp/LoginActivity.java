package inc.fimp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
  //  private static final String TAG = "LoginActivity";
  //  private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_uname) EditText _userName;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {

        CharSequence noExist = getApplicationContext().getString(R.string.noExist);

        String uname = _userName.getText().toString();
        String password = _passwordText.getText().toString();

        if (!validate()) {
            Toast.makeText(LoginActivity.this, "Incorrect Entries, Please review your enteries!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            String pword = helper.searchPass(uname);
            if(password.equals(pword)){
                Intent i = new Intent(this,UserActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(LoginActivity.this, noExist, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public boolean validate() {
        boolean valid = true;

        String uname = _userName.getText().toString();
        String password = _passwordText.getText().toString();

        if (uname.isEmpty() || uname.length()<5) {
            _userName.setError("enter a valid user name");
            valid = false;
        } else {
            _userName.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}