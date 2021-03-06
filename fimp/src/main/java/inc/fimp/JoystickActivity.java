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
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class JoystickActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;


    RelativeLayout layout_joystick, layout_joystick2;
    TextView textView1, textView2,textView3, textView4,textView5;
    Switch aSwitch;
    int servor1Value,servor2Value,servor3Value,servor4Value;

    JoyStickClass leftJoystick;
    JoyStickClass rightJoystick;

    SQLiteDatabase sql;
    JoystickDB db;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        aSwitch = (Switch) findViewById(R.id.switch1);

        textView1.setText(getString(R.string.servor1)+0);
        textView2.setText(getString(R.string.servor2)+0);
        textView3.setText(getString(R.string.servor3)+0);
        textView4.setText(getString(R.string.servor4)+0);
        textView5.setText(getString(R.string.claw_open));

        layout_joystick = (RelativeLayout) findViewById(R.id.layout_joystick1);
        layout_joystick2 = (RelativeLayout) findViewById(R.id.layout_joystick2);

        leftJoystick = new JoyStickClass(getApplicationContext()
                , layout_joystick, R.drawable.image_button);

        rightJoystick = new JoyStickClass(getApplicationContext()
                , layout_joystick2, R.drawable.image_button);

        leftJoystick.setStickSize(150, 150);
        leftJoystick.setLayoutAlpha(150);
        leftJoystick.setStickAlpha(100);
        leftJoystick.setOffset(90);
        leftJoystick.setMinimumDistance(50);


        rightJoystick.setStickSize(150, 150);
        rightJoystick.setLayoutAlpha(150);
        rightJoystick.setStickAlpha(100);
        rightJoystick.setOffset(90);
        rightJoystick.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                leftJoystick.drawStick(arg1);
                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {

                    servor1Value = leftJoystick.getX(); //Value for servor1Value to be sent to database
                    servor2Value = leftJoystick.getY(); //Value for servor2Value to be sent to database
                    addDB();

                    textView1.setText(getString(R.string.servor1) + servor1Value);
                    textView2.setText(getString(R.string.servor2) + servor2Value);
                } else if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    textView1.setText(getString(R.string.servor1)+0);
                    textView2.setText(getString(R.string.servor2)+0);
                }
                return true;
            }
        });

        layout_joystick2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                rightJoystick.drawStick(arg1);
                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {

                    servor3Value = rightJoystick.getX(); //Value for servor3Value to be sent to database
                    servor4Value = rightJoystick.getY(); //Value for servor4Value to be sent to database
                    addDB();

                    textView3.setText(getString(R.string.servor3) + servor3Value);
                    textView4.setText(getString(R.string.servor4)+ servor4Value);
                } else if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    textView3.setText(getString(R.string.servor3)+0);
                    textView4.setText(getString(R.string.servor4)+0);
                }
                return true;
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    textView5.setText(getString(R.string.claw_close));
                else
                    textView5.setText(getString(R.string.claw_open));
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

        if(res_id==R.id.action_logout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(JoystickActivity.this, LoginActivity.class));

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
            startActivity(new Intent(JoystickActivity.this, LoginActivity.class));

        }

        if(res_id==R.id.nav_bio){
            //will alow the user to add a bio
            final TextView userinputtext = (TextView) findViewById(R.id.userinputtext);

            View view = (LayoutInflater.from(JoystickActivity.this)).inflate(R.layout.user_input, null);

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(JoystickActivity.this);
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
            startActivity(new Intent(JoystickActivity.this, CamActivity.class));
            Toast.makeText(JoystickActivity.this, "Camera", Toast.LENGTH_SHORT).show();
        }

        else if(res_id==R.id.nav_joystick){
            startActivity(new Intent(JoystickActivity.this, JoystickActivity.class));
            Toast.makeText(JoystickActivity.this, "Joystick", Toast.LENGTH_SHORT).show();
        }

        else if(res_id==R.id.nav_home){
            startActivity(new Intent(JoystickActivity.this, UserActivity.class));
            Toast.makeText(JoystickActivity.this, "Home", Toast.LENGTH_SHORT).show();
        }

        else if(res_id==R.id.nav_logout){
            onBackPressed();
        }

        else  if(res_id==R.id.nav_AboutUs){
            startActivity(new Intent(JoystickActivity.this, AboutUs.class));
            Toast.makeText(JoystickActivity.this, "About Us", Toast.LENGTH_SHORT).show();
        }

        else  if(res_id==android.R.id.home){ // linked with getSupportActionBar().setDisplayHomeAsUpEnabled();
            firebaseAuth.signOut();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addDB(){
        db = new JoystickDB(this);
        sql = db.getWritableDatabase();
        db.putInformation(sql, servor1Value, servor2Value, servor3Value, servor4Value);
    }

}
