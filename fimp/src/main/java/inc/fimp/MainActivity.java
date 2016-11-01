package inc.fimp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(getApplicationContext(), "You selected Contacted us option", Toast.LENGTH_SHORT).show();
        }

        if(res_id==R.id.action_settings){
            Toast.makeText(getApplicationContext(), "You selected Settings Option", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
