package inc.fimp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class JoystickActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    RelativeLayout layout_joystick, layout_joystick2;
    TextView textView1, textView2,textView3, textView4,textView5;
    Switch aSwitch;
    int servor1Value,servor2Value,servor3Value,servor4Value;

    JoyStickClass leftJoystick;
    JoyStickClass rightJoystick;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if(res_id==R.id.action_about)
        {
            startActivity(new Intent(this, AboutUs.class));
        }

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
}
