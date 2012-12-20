package cz.fel.cvut.helpdeskclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	/** Called when the activity is first created. */
	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); setContentView(R.layout.login);
	 * 
	 * final Button button = (Button) findViewById(R.id.button1);
	 * button.setOnClickListener(new View.OnClickListener() { public void
	 * onClick(View v) { Intent myIntent = new
	 * Intent(QuestionnariesActivity.this, MainActivity.class);
	 * startActivity(myIntent); } }); }
	 */
	EditText u_name = null, pwd = null;
	Button mylogin_submit,registration;
	String username = "", password = "", sha1pwd = "";
	private ArrayList<StoreValue> mydata = null;
	int i = 0;
	Intent Loginjump;
	static Context c;
	HelpPrinter help = new HelpPrinter();
	int j = 0;
	int size = 0;
	HashSHA1 sha = new HashSHA1();
	String device_id;
	GetDataFromDatabase getDevID;
	ArrayList<StoreValueListQuest> getDevIDdata = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		u_name = (EditText) findViewById(R.id.user);
		pwd = (EditText) findViewById(R.id.pwd);
		mylogin_submit = (Button) findViewById(R.id.submit);
		registration = (Button) findViewById(R.id.register);
		c = this;
		mydata = getValue();

		// mylogin_submit.set
		/*
		 * mylogin_submit.setOnClickListener(new View.OnClickListener() { public
		 * void onClick(View v) { Intent myIntent = new Intent(Form1.this,
		 * Form.class); startActivity(myIntent); }
		 */
		mylogin_submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				username = u_name.getText().toString();
				Log.d("UserName", username);
				password = pwd.getText().toString();
				try {
					sha1pwd = sha.SHA1(password);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("DATA", "have data from editext: \n user: " + username
						+ "\n password: " + password + "\n SHA1: " + sha1pwd);
				mydata = getValue();
				size = mydata.size();

				Log.d("MyData Size", Integer.toString(mydata.size()));
				for (i = 0; i < mydata.size(); i++)// pozor i=0
				{
					Log.i("GET", mydata.get(i).getPassword() + "\n"
							+ mydata.get(i).getUsername());

					System.out.println("Mydata of" + " i "
							+ (mydata.get(i).username));
					if (mydata.get(i).getPassword().equals(sha1pwd)
							&& mydata.get(i).getUsername().equals(username) == true
							&& (username.isEmpty() == false && password
									.isEmpty() == false))

					{
						if (username.equals("root")) {
							Log.d("Success", "ROOT");
							Loginjump = new Intent(Login.this,
									MainActivity.class);
							Loginjump.putExtra("table", "login");
							Loginjump.putExtra("username", username);
							startActivity(Loginjump);
							finishActivity(RESULT_OK);
							finish();
						} else {

							getDevID = new GetDataFromDatabase(
									getApplicationContext(), "device_id");
							getDevIDdata = getDevID.getNuberOfRows("device_id");
							int devcount = getDevIDdata.size();
							Log.i("devcount", "" + devcount);

							TelephonyManager tm = (TelephonyManager) getBaseContext()
									.getSystemService(Context.TELEPHONY_SERVICE);
							String tmDevice = "" + tm.getDeviceId();
							String androidId = ""
									+ android.provider.Settings.Secure
											.getString(
													getContentResolver(),
													android.provider.Settings.Secure.ANDROID_ID);
							UUID deviceUuid = new UUID(androidId.hashCode(),
									((long) tmDevice.hashCode() << 32));
							String device_id = deviceUuid.toString();

							// device_id=telemngr.getDeviceId();
							try {
								Log.i("device_id", device_id);
								Log.i("database devid", getDevIDdata.get(0)
										.getCount());
							} catch (Exception e) {
								Log.i("device_id", "NULL");
								// Log.i("batabase device_id", "NULL");
							}
							if (getDevIDdata.get(0).getCount().equals("")) {
								MyDBHelper dbAdapter = MyDBHelper
										.getDBAdapterInstance(c);
								dbAdapter.openDataBase();

								ContentValues initialValues = new ContentValues();
								initialValues.put("device_id", device_id);

								long n = dbAdapter.insertRecordsInDB(
										"device_id", null, initialValues);
								help.displayer("new row inserted with id = "
										+ n, c);

								dbAdapter.close();
							}
							Log.d("Success", "Boss");
							Loginjump = new Intent(Login.this,
									MainActivity.class);
							Loginjump.putExtra("username", username);
							startActivity(Loginjump);
							finishActivity(RESULT_OK);
							finish();
						}

					} else {
						++j;
						help.displayer("" + Integer.toString(j) + "\n"
								+ Integer.toString(size), c);
						Log.d("Failure", "1");

						if (j == size) {
							displayAlert();
							u_name.setText("");
							pwd.setText("");
							u_name.requestFocus();
							j = 0;
						}
					}

				}

				// return false;

			}
		});
		registration.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Loginjump = new Intent(Login.this,RegistrationActivity.class);
				startActivity(Loginjump);
				finishActivity(RESULT_OK);
				finish();
			}
		});

	}
	
	

	public static void displayAlert()

	{

		new AlertDialog.Builder(c)
				.setMessage(" Heslo nebo uživatelské jméno jsou chybné ")
				.setTitle("Password Alert")
				.setCancelable(true)
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// System.exit(0);
							
								dialog.dismiss();
							}
						}).show();
	}

	public ArrayList<StoreValue> getValue() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM login;";
		ArrayList<ArrayList<String>> stringList = dbAdapter
				.selectRecordsFromDBList(query, null);
		dbAdapter.close();
		ArrayList<StoreValue> usersList = new ArrayList<StoreValue>();

		for (int i = 0; i < stringList.size(); i++) {
			ArrayList<String> list = stringList.get(i);
			StoreValue user = new StoreValue();
			try {
				final StoreValue listItem = mydata.get(i); // --CloneChangeRequired
				if (listItem != null) {
					user.username = list.get(1);
					System.out.println("List get 0: " + list.get(0));
					user.password = list.get(2);
					System.out.println("List get 1: " + list.get(1));
					System.out.println("List get 2: " + list.get(2));
					System.out.println("List get 3: " + list.get(3));
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			usersList.add(user);
		}
		return usersList;
	}

}
