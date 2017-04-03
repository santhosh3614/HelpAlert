package hometutorial.nit.com.helpalert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MessageActivity extends Activity {

	Button btnGPSShowLocation;
	Button btnShowAddress;
	TextView tvAddress;
	EditText et1, et2;
	Button send;

	AppLocationService appLocationService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		//tvAddress = (TextView) findViewById(R.id.tvAddress);
		et1 = (EditText) findViewById(R.id.toPhoneNumberET);
		et2 = (EditText) findViewById(R.id.smsMessageET);
		send = (Button) findViewById(R.id.sendSMSBtn);

		appLocationService = new AppLocationService(MessageActivity.this);

		btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);
		btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Location gpsLocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
				if (gpsLocation != null) {
					double latitude = gpsLocation.getLatitude();
					double longitude = gpsLocation.getLongitude();
					String result = "Latitude: " + gpsLocation.getLatitude() + " Longitude: "
							+ gpsLocation.getLongitude();
					tvAddress.setText(result);
				} else {
					showSettingsAlert();
				}
			}
		});

		btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
		btnShowAddress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);

				// you can hard-code the lat & long if you have issues with
				// getting it
				// remove the below if-condition and use the following couple of
				// lines
				// double latitude = 37.422005;
				// double longitude = -122.084095

				if (location != null) {
					double latitude = location.getLatitude();
					double longitude = location.getLongitude();
					LocationAddress locationAddress = new LocationAddress();
					locationAddress.getAddressFromLocation(latitude, longitude, getApplicationContext(),
							new GeocoderHandler());
				} else {
					showSettingsAlert();
				}

			}
		});
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String toPhoneNumber = et1.getText().toString();
					String message = et2.getText().toString();
					// int aba = Integer.parseInt(toPhoneNumber);

					// SmsManager smsManager = SmsManager.getDefault();
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(toPhoneNumber, null, message, null, null);
					Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Sending SMS failed.", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		});

	}

	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MessageActivity.this);
		alertDialog.setTitle("SETTINGS");
		alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				MessageActivity.this.startActivity(intent);
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

	public class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
			String locationAddress;
			String sms = null;
			switch (message.what) {
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				break;
			default:
				locationAddress = null;
			}
			//tvAddress.setText(locationAddress);

			et2.setText(locationAddress);
		}

	}
}