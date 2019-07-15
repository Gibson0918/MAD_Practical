package sg.edu.np.week12regularexpression;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void OnClick2(View v){

        EditText text = findViewById(R.id.editText2);
        String userName = text.getText().toString();

        EditText text2 = findViewById(R.id.editText4);
        String password = text2.getText().toString();

        Pattern patternUser =Pattern.compile("^([a-zA-Z0-9]){6,12}$");
        Matcher matcherUser = patternUser.matcher(userName);

        //Pattern patternPW = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W_]).*$");
        Pattern patternPW = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[\\W_])[0-9A-Za-z\\W]*$");
        Matcher matcherPW = patternPW.matcher(password);

        if (matcherUser.matches() && matcherPW.matches() ){
            Toast tt = Toast.makeText(CreateNewUser.this ,"Account Created!", Toast.LENGTH_LONG);
            tt.show();

            //save data of User Name
            // SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS" ,MODE_PRIVATE ).edit();
            //  editor.putString("USER_NAME", userName);
            // editor.putString("PASSWORD", password);
            //  editor.apply();

            UserData ud = new UserData(userName,password);
            DbHandler db = new DbHandler(this,null,null,1);
            db.addUser(ud);
            CreateNewUser.this.finish();

        }

        else {
            Toast tt = Toast.makeText(CreateNewUser.this,"Account not created!", Toast.LENGTH_LONG);
            tt.show();
        }
    }

    public void onClick3(View v){
        CreateNewUser.this.finish();
    }
}
