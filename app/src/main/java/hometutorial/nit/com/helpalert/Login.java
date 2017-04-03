package hometutorial.nit.com.helpalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by nareshit on 26-Nov-16.
 */

public class Login extends Activity
{
       EditText email, password;
    Button blogin,bsignup;
    DataBaseClassAdapter dataBaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        email = (EditText) findViewById(R.id.editText7);
        password = (EditText) findViewById(R.id.editText8);
        blogin = (Button) findViewById(R.id.button7);
        bsignup = (Button) findViewById(R.id.button);
        email.requestFocus();
        dataBaseClass = new DataBaseClassAdapter(this);
    }

    public void signUp(View view) {
        Intent intensignup = new Intent(this, SignUp.class);
        startActivity(intensignup);

    }
    public void myLogin(View view) {
        String enteredemail = email.getText().toString().trim();
        String enteredpwd = password.getText().toString().trim();


        if (enteredemail.equalsIgnoreCase("admin") & enteredpwd.equalsIgnoreCase("admin")) {
            Toast.makeText(this, "Username and password is correct",
                    Toast.LENGTH_SHORT).show();

            Intent intentadmin = new Intent(this, Admin.class);
            startActivity(intentadmin);
        }
        else{
            boolean f=  dataBaseClass.isAuthenticated(enteredemail, enteredpwd);

            if (f) {
                Toast.makeText(this, "email and password is correct",
                        Toast.LENGTH_SHORT).show();
                Intent intentadmin = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentadmin);


            }

            else {
                Toast.makeText(getApplicationContext(),"email and password is NOT correct",Toast.LENGTH_SHORT).show();
            }

        }


    }




}

