package hometutorial.nit.com.helpalert;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Sos extends Activity {

	// create an array list of buttons
	private ArrayList<Button> list = new ArrayList<Button>();
	private static TableLayout table;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sos);

		// adding buttons to list so that can use onclick function.
		addRow(list.size(), "US Ambulance Hotline:", "911");
		addRow(list.size(), "US Fire Hotline:", "311");
		addRow(list.size(), "US Police Hotline:", "991");
		addRow(list.size(), "US Hotline:", "911");
		//addRow(list.size(), "Railway Hotline", "999");
		 addRow(list.size(), "uk hotline", "111");

		// adding home button to layout
		Button home = new Button(this);
		home.setText("Home");
		int index = list.size() + 1;
		home.setId(index);
		home.setOnClickListener(btnclick);
		home.setTextColor(getResources().getColor(R.color.BlueViolet));
		home.setBackgroundResource(R.drawable.button_layout);
		home.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT));
		table.addView(home);

	}

	public void addRow(int index, String textview, String number) {
		table = (TableLayout) findViewById(R.id.table);
		TableRow row = new TableRow(this);
		row.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
		TextView test = new TextView(this);
		test.setText(textview);
		test.setTextSize(18);
		test.setTextColor(getResources().getColor(R.color.PaleGoldenrod));
		test.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

		Button sample = new Button(this);
		sample.setText(number);
		sample.setId(index);
		sample.setOnClickListener(btnclick);
		sample.setBackgroundResource(R.drawable.button_layout);
		sample.setLayoutParams(
				new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

		row.addView(test);
		row.addView(sample);

		table.addView(row);
		list.add(sample);
	}

	OnClickListener btnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// if the id clicked is in the list, then call number for that
			// button.
			if (v.getId() < list.size()) {
				try {
					StringBuilder number = new StringBuilder();
					number.append("tel:");
					number.append(list.get(v.getId()).getText().toString());
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse(number.toString()));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Log.e("First Response", "Call failed");
				}
			} else {

				finish();
			}
		}
	};

	/**
	 * This method should stop any services before killing process.
	 */
	public void onDestroy() {
		super.onDestroy();
		// STOP Services
	}

	/**
	 * an empty onPause method
	 */
	public void onPause() {
		super.onPause();

	}

	/**
	 * used upon resuming the application.
	 *
	 */
	public void onResume() {
		super.onResume();

	}

	/**
	 * Called before interrupt to save data.
	 */
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// nothing needs to be saved here since nothing is changed or being
		// used.
	}

	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore session score
		// nothing needs to be restored here since nothing is used previously.
	}
}
