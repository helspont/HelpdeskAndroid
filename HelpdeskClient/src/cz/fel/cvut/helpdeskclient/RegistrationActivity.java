package cz.fel.cvut.helpdeskclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {

	Button back, submit;
	EditText requsername, user, reqpwd, pwd;
	String sh1pwd, sh1reqpwd;
	HashSHA1 sha = new HashSHA1();
	String sPwd, sReqPwd, sReqUsername, sUser;
	private ArrayList<StoreValue> mydata;
	CheckBox helpdesk;
	Boolean checked;
	String isHelpdek="0";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		back = (Button) findViewById(R.id.back);
		submit = (Button) findViewById(R.id.buttonSubmit);
		requsername = (EditText) findViewById(R.id.requsername);
		reqpwd = (EditText) findViewById(R.id.reqpwd);
		user = (EditText) findViewById(R.id.user);
		pwd = (EditText) findViewById(R.id.pwd);
		helpdesk = (CheckBox)findViewById(R.id.checkBox1);
		mydata = getValue();
		/*back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(RegistrationActivity.this,
						Login.class);
				startActivity(myIntent);
				finishActivity(RESULT_OK);
				finish();

			}
		});*/
		
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sUser = user.getText().toString();
				sPwd = pwd.getText().toString();
				sReqUsername = requsername.getText().toString();
				sReqPwd = reqpwd.getText().toString();
				checked=helpdesk.isChecked();
				Log.e("checked",""+checked);
				try {
					sh1pwd = sha.SHA1(sPwd);
					sh1reqpwd = sha.SHA1(sReqPwd);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mydata = getValue();
				mydata = getValue();
				Log.d("MyData Size", Integer.toString(mydata.size()));
				for (int i = 0; i < mydata.size(); i++)// pozor i=0
				{
					if (mydata.get(i).getPassword().equals(sh1pwd)
							&& mydata.get(i).getUsername().equals(sUser) == true
							&& (sUser.isEmpty() == false && sPwd.isEmpty() == false)
							&& (mydata.get(i).getRank().equals("1")))

					{
						if(checked){
							isHelpdek ="1";
						}else{
							isHelpdek="0";
						}
						MyDBHelper dbAdapter = MyDBHelper
								.getDBAdapterInstance(getApplicationContext());
						dbAdapter.openDataBase();

						ContentValues initialValues = new ContentValues();
						initialValues.put("user", sReqUsername);
						initialValues.put("password", sh1reqpwd);
						initialValues.put("rank", isHelpdek);

						long n = dbAdapter.insertRecordsInDB(
								"login", null, initialValues);
						dbAdapter.close();
						
						Intent myIntent = new Intent(RegistrationActivity.this,Login.class);
						startActivity(myIntent);
						finishActivity(RESULT_OK);
						finish();
					}
				}
			}
		});
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
					user.rank = list.get(3);
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
