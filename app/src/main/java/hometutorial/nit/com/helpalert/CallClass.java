package hometutorial.nit.com.helpalert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CallClass extends Activity implements OnItemClickListener {
    ListView list;
    String[] names = {"EMERGENCY HELP1", "EMERGENCY HELP2", "EMERGENCY HELP3"};
    String[] nos = {"8686024335", "8121510950", "7842972861"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincall);

        list = (ListView) findViewById(R.id.listView1);
        Resources r = this.getResources();
        int[] images = {R.drawable.contact, R.drawable.contact, R.drawable.contact};

        CustomAdapter adapter = new CustomAdapter(this, names, nos, images);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("You can perform");
        menu.add(0, 1, 1, "Update");
        //menu.add(0, 2, 2, "Update");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
        if (names[position].equals("EMERGENCY HELP1")) {
            Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nos[position]));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent1);

        }
        if (names[position].equals("EMERGENCY HELP2")) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nos[position]));
            startActivity(intent);

        }
        if (names[position].equals("EMERGENCY HELP3")) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + nos[position]));
            startActivity(intent);

        }
    }
}

class CustomAdapter extends ArrayAdapter {
    Context context;
    String[] names;
    String[] nos;
    int[] images;

    public CustomAdapter(Context context, String[] names, String[] nos, int[] images) {
        super(context, R.layout.single_row, names);
        this.context = context;
        this.names = names;
        this.nos = nos;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.single_row, null);
        ImageView image = (ImageView) v.findViewById(R.id.imageView1);
        TextView names = (TextView) v.findViewById(R.id.textView1);
        TextView nos = (TextView) v.findViewById(R.id.textView2);

        image.setImageResource(images[position]);
        names.setText(this.names[position]);
        nos.setText(this.nos[position]);
        //desc.setText(this.desc[position]);
        return v;
    }

}
