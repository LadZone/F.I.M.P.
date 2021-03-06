package inc.fimp;

/***
 * Developers: Tanav Sharma
 *             Alay Lad
 *             Hennok Tadesse
 *
 * Team Name: The A Team
 * Project Name: FIMP
 * Prof Name: Haki Sharifi
 * Course Code: CENG 319
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity
{



    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (!validate()) {
            CharSequence okButton = getApplicationContext().getString(R.string.okButton);
            CharSequence regiError = getApplicationContext().getString(R.string.regiError);
            CharSequence requError = getApplicationContext().getString(R.string.requError);
            CharSequence oneReq = getApplicationContext().getString(R.string.oneReq);
            CharSequence twoReq = getApplicationContext().getString(R.string.twoReq);

            final AlertDialog.Builder regError = new AlertDialog.Builder(SignupActivity.this);
                    regError.setTitle(regiError);
                    regError.setMessage(requError + "\n\n" + oneReq +"\n\n" + twoReq);
                    regError.setPositiveButton(okButton, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    }).create().show();


            //Toast.makeText(SignupActivity.this, error, Toast.LENGTH_SHORT).show();
            return;
        }else{

            //if validation is successful
            //progress bar shown, for inhanced UI
            CharSequence registering = getApplicationContext().getString(R.string.registeringBAR);
            progressDialog.setMessage(registering);
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                finish();
                                Intent i = new Intent(SignupActivity.this, UserActivity.class);
                                startActivity(i);
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                CharSequence okButton = getApplicationContext().getString(R.string.okButton);
                                CharSequence regiError = getApplicationContext().getString(R.string.regiError);
                                CharSequence requError = getApplicationContext().getString(R.string.requError);
                                CharSequence oneReq = getApplicationContext().getString(R.string.oneReq);
                                CharSequence twoReq = getApplicationContext().getString(R.string.twoReq);

                                final AlertDialog.Builder regError = new AlertDialog.Builder(SignupActivity.this);
                                regError.setTitle(regiError);
                                regError.setMessage(requError + "\n\n" + oneReq +"\n\n" + twoReq);
                                regError.setPositiveButton(okButton, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //do nothing
                                    }
                                }).create().show();


                                //Toast.makeText(SignupActivity.this, "Test: Registration Unsccessful", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }


    public boolean validate(){
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
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
