package hometutorial.nit.com.helpalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by nareshit on 22-Nov-16.
 */
public class SignUp extends Activity {
    EditText editText_firstname, editText_pass, et_confirm_pwd, editText_fullname, editText_lastname, et_email;
    Button button_signin,button_showdata;
    String value_age, value_gender, value_prgm;
    DataBaseClassAdapter dataBaseClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);
        editText_firstname = (EditText) findViewById(R.id.editText);
        editText_firstname.requestFocus();

        editText_pass = (EditText) findViewById(R.id.editText2);
        et_confirm_pwd = (EditText) findViewById(R.id.editText3);
        editText_fullname = (EditText) findViewById(R.id.editText4);
        editText_lastname = (EditText) findViewById(R.id.editText9);
        button_signin = (Button) findViewById(R.id.button4);
        //button_showdata = (Button) findViewById(R.id.button8);
        et_email = (EditText) findViewById(R.id.editText6);


        dataBaseClass = new DataBaseClassAdapter(this);
    }






            // for signin
    public void signIn(View view) {
        String first_name = editText_firstname.getText().toString();
        if (!StringUtils.isValidString(first_name)) {
            Toast.makeText(getApplicationContext(), "Enter a valid first name", Toast.LENGTH_LONG).show();
            return;
        }

        String pwd = editText_pass.getText().toString();
        if (!StringUtils.isValidString(pwd)) {
            Toast.makeText(getApplicationContext(), "Enter a valid password", Toast.LENGTH_LONG).show();
            return;
        }

        String confirm_pwd = et_confirm_pwd.getText().toString();
        if (!StringUtils.isValidString(confirm_pwd)) {
            Toast.makeText(getApplicationContext(), "Enter a valid cpwd", Toast.LENGTH_LONG).show();
            return;
        }


        if (!confirm_pwd.equals(pwd)) {
            Toast.makeText(getApplicationContext(), "confirm password and password are not matching", Toast.LENGTH_LONG).show();
            return;
        }

        String fullname = editText_fullname.getText().toString();
        if (!StringUtils.isValidString(fullname)) {
            Toast.makeText(getApplicationContext(), "Enter a valid full name", Toast.LENGTH_LONG).show();
            return;
        }

        String last_name = editText_lastname.getText().toString();
        if (!StringUtils.isValidString(last_name)) {
            Toast.makeText(getApplicationContext(), "Enter a valid last name", Toast.LENGTH_LONG).show();
            return;
        }


        String value_email = et_email.getText().toString();
        if (!StringUtils.isValidString(value_email)) {
            Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_LONG).show();
            return;
        }



       /* Toast.makeText(getApplicationContext(),NameforInsert+" "+AddforInsert+" "+ConforInsert+" "+SelectedClass+" "+SelectedArea+" "+Selectedsub,Toast.LENGTH_LONG).show();*/

        long id = dataBaseClass.insertData(first_name, last_name, fullname, pwd, confirm_pwd, value_email);
        if (id < 0) {
            Message.message(this, "unsuccessfull");
        } else {
            Message.message(this, "successfully insert a row");
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);

        }


    }
}