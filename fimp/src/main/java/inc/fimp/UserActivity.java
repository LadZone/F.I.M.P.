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
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private FirebaseAuth firebaseAuth;


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private EditText userInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       // mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

      //  mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);

       // mDrawerLayout.addDrawerListener(mToggle);
       // mToggle.syncState();

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }



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


        if(res_id==R.id.nav_logout)
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


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int res_id = item.getItemId();


        if(res_id==R.id.nav_logout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(UserActivity.this, LoginActivity.class));

        }

        if(res_id==R.id.nav_bio){
            //will alow the user to add a bio
            final TextView userinputtext = (TextView) findViewById(R.id.userinputtext);

            View view = (LayoutInflater.from(UserActivity.this)).inflate(R.layout.user_input, null);

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(UserActivity.this);
            alertBuilder.setView(view);
            final EditText userInput = (EditText) view.findViewById(R.id.userinput);

            alertBuilder.setCancelable(true)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userinputtext.setText(userInput.getText());

                        }
                    });
            Dialog dialog = alertBuilder.create();
            dialog.show();

        }

        if(res_id==R.id.nav_camera){
            startActivity(new Intent(UserActivity.this, CamActivity.class));
            Toast.makeText(UserActivity.this, "Camera", Toast.LENGTH_SHORT).show();
        }

       else if(res_id==R.id.nav_joystick){
            startActivity(new Intent(UserActivity.this, JoystickActivity.class));
            Toast.makeText(UserActivity.this, "Joystick", Toast.LENGTH_SHORT).show();
        }

        else if(res_id==R.id.nav_home){
            startActivity(new Intent(UserActivity.this, UserActivity.class));
           Toast.makeText(UserActivity.this, "Home", Toast.LENGTH_SHORT).show();
        }

        else if(res_id==R.id.nav_logout){
            onBackPressed();
        }

        else  if(res_id==R.id.nav_AboutUs){
            startActivity(new Intent(UserActivity.this, AboutUs.class));
            Toast.makeText(UserActivity.this, "About Us", Toast.LENGTH_SHORT).show();
        }

        else  if(res_id==android.R.id.home){ // linked with getSupportActionBar().setDisplayHomeAsUpEnabled();
            firebaseAuth.signOut();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
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

        firebaseAuth.signOut();
    }




}
