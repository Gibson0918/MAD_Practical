package sg.edu.np.week12regularexpression;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView newUser = findViewById(R.id.textView3);
        newUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Intent intent = new Intent(MainActivity.this, CreateNewUser.class);
                    startActivity(intent);
                }
                    return false;
            }
        });
    }

    public void onClick1(View v){

        EditText text = findViewById(R.id.editText);
        String userName = text.getText().toString();

        EditText text2 = findViewById(R.id.editText3);
        String password = text2.getText().toString();


        DbHandler db = new DbHandler(this,null,null ,1);
        if (db.findUser(userName) == null ){
            Toast tt = Toast.makeText(MainActivity.this,"No such Account!", Toast.LENGTH_LONG);
            tt.show();
        }
        else {
                UserData user = db.findUser(userName);


                String pass = user.getMyPassword();
                String userId = user.getMyUserName();

                if (userName.equals(userId) && password.equals(pass)) {
                    Toast tt = Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_LONG);
                    tt.show();
                } else {
                    Toast tt = Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_LONG);
                    tt.show();
                }
        }
    }
}
