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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CharSequence profile = getApplicationContext().getString(R.string.profile);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(profile);

        Button joystick = (Button) findViewById(R.id.joystick);
        joystick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, JoystickActivity.class));
            }
        });

        Button camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, CamActivity.class));
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);



        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();

        if(res_id==R.id.action_controller)
        {
            startActivity(new Intent(UserActivity.this, JoystickActivity.class));
        }

        if(res_id==R.id.action_camera)
        {
            startActivity(new Intent(UserActivity.this, CamActivity.class));
        }

        if(res_id==R.id.action_about)
        {
            startActivity(new Intent(this, AboutUs.class));
        }

        if(res_id==R.id.action_logout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(UserActivity.this, LoginActivity.class));

        }

        if(res_id==android.R.id.home){ // linked with getSupportActionBar().setDisplayHomeAsUpEnabled();
            onBackPressed();
        }


        return true;
    }


    /**
     * This function is responsible for asking the user to confirm
     * if they would like to quit the app. This function executes
     * when the user either presses the back key on the phone, or
     * the back key on the app.
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        UserActivity.super.onBackPressed();
                    }
                }).create().show();

       // firebaseAuth.signOut();
    }

}
