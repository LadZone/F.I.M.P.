package inc.fimp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.login){

            EditText user_name = (EditText) findViewById(R.id.uname);
            String str = user_name.getText().toString();

            Intent i = new Intent(this,UserActivity.class);
            i.putExtra("username",str);
            startActivity(i);
        }
        if(v.getId() == R.id.regAct){
            Intent i = new Intent(this,RegisterActivity.class);
            startActivity(i);
        }
    }


}
