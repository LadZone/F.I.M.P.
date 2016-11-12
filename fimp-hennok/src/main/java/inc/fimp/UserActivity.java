package inc.fimp;

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

public class UserActivity extends AppCompatActivity {

    Button controller;
    Button camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        controller=(Button)findViewById(R.id.btn_controller);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CharSequence profile = getApplicationContext().getString(R.string.profile);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(profile);

        controller.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,JoystickActivity.class);
                startActivity(intent);
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
        if(res_id==R.id.action_contact)
        {
            //just for testing purposes
            Toast.makeText(getApplicationContext(), "You selected Contacted us option", Toast.LENGTH_SHORT).show();
        }

        if(res_id==R.id.action_settings){
            //just for testing purposes
            Toast.makeText(getApplicationContext(), "You selected Settings Option", Toast.LENGTH_SHORT).show();
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
    }


    public void getInfo(){

    }










































































    /****
     *   rootView = (ViewGroup) findViewById(R.id.buttonContainer);

     Button addArm = (Button) findViewById(R.id.btnAddArm);
     addArm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    getInfo();
    addButton();
    }
    });
     */

   /* public void addButton(){

        // create an aditional button
        Button button = new Button(MainActivity.this); // Need to provide the context, the Activity
        button.setText("Added!"); // for example
        rootView.addView(button);


    }*/
}
