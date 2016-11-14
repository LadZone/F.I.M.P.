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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class AboutUs extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
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
            startActivity(new Intent(AboutUs.this, LoginActivity.class));

        }

        if(res_id==android.R.id.home){ // linked with getSupportActionBar().setDisplayHomeAsUpEnabled();
            onBackPressed();
        }


        return true;
    }
}
