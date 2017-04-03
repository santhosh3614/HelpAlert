package hometutorial.nit.com.helpalert;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by nareshit on 26-Sep-16.
 */
public class Message {
    public  static void message(Context context, String message) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
}
