package inc.fimp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.register);

    }

    public void onRegisterClick(View v){

        CharSequence noMatch = getApplicationContext().getString(R.string.noMatch);

        if(v.getId()==R.id.register){

            EditText FullName = (EditText)findViewById(R.id.fname);
            EditText UserName = (EditText)findViewById(R.id.uname);
            EditText Email = (EditText)findViewById(R.id.email);
            EditText Pass1 = (EditText)findViewById(R.id.pass);
            EditText Pass2 = (EditText)findViewById(R.id.cnfrmPass);

            //converting to strings
            String FullNameStr = FullName.getText().toString();
            String UserNameStr = UserName.getText().toString();
            String EmailStr = Email.getText().toString();
            String Pass1Str = Pass1.getText().toString();
            String Pass2Str = Pass2.getText().toString();

            if(!Pass1Str.equals(Pass2Str)){
                Toast.makeText(RegisterActivity.this, noMatch, Toast.LENGTH_SHORT).show();
            }

        }

    }
}
