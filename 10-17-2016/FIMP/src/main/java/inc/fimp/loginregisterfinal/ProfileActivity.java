/*************************************
 * Team Name: The Team A
 * Group Names: Alay Lad, Tanav Sharma, Hennok Tadesse
 */

package inc.fimp.loginregisterfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;

    private Button buttonJoystick;
    private Button buttonCamera;
    private Button buttonLogout;

    private DatabaseReference databaseReference;
    private EditText editTextFullName;
    private EditText editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //editTextAge = (EditText) findViewById(R.id.editTextAge);
        //editTextFullName = (EditText) findViewById(R.id.editTextFullName);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonJoystick = (Button) findViewById(R.id.buttonJoystick);
        buttonCamera = (Button) findViewById(R.id.buttonCamera);

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome "+user.getEmail());

        buttonLogout.setOnClickListener(this);
        buttonJoystick.setOnClickListener(this);
        buttonCamera.setOnClickListener(this);
    }

    private void saveUserName(){

        /*String name = editTextFullName.getText().toString().trim();
        //String age = editTextAge.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(ProfileActivity.this, "Registration Complete!", Toast.LENGTH_SHORT).show();
        */

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));

    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogout ){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonJoystick){
            //finish();
            startActivity(new Intent(this, JoystickActivity.class));
        }

        if(view == buttonCamera){


        }

        /*if(view == buttonSave){
            saveUserName();
        }*/



    }
}
