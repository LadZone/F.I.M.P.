/*************************************
 * Team Name: The Team A
 * Group Names: Alay Lad, Tanav Sharma, Hennok Tadesse
 */

package inc.fimp.loginregisterfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private Button buttonRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

   // private DatabaseReference databaseReference;
   // private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
            //profile act here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

      //  databaseReference = FirebaseDatabase.getInstance().getReference();
      //  editTextName = (EditText)findViewById(R.id.editTextFullName);

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(MainActivity.this, "Please enter an email", Toast.LENGTH_SHORT).show();
            //stoping the fuction
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(MainActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok
        //we will first show a progress bar to the user

        progressDialog.setMessage(getString(R.string.register));
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //user is successfully registerted
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                            }else{
                               Toast.makeText(MainActivity.this, "Registration Failed! Password must 6 characters or more!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister ){
            registerUser();
        }

        if(view == textViewSignin){
            //will open signin act
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
