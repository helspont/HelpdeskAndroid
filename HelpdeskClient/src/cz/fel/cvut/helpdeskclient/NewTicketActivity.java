package cz.fel.cvut.helpdeskclient;


import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTicketActivity extends Activity {
	Button createTicket;
	EditText message;
	EditText title;
	TextView username;
	String getUsername="";
	String getMessage="";
	String getTitle="";
	String getID="";
	String getRank="";
	HelpPrinter help;
	private ArrayList<StoreValue> mydata2 /*= getValue()*/;
	private ArrayList<StoreValueListQuest> mydata1 = getLastID();

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newticket);
		//Log.d("NewTicket", "in onCreate");
		help = new HelpPrinter();
		getUsername = getIntent().getStringExtra("username");
		createTicket = (Button) findViewById(R.id.crateTicket);
		message = (EditText) findViewById(R.id.messageText);
		title = (EditText) findViewById(R.id.titleText);
		username = (TextView) findViewById(R.id.usernameNewTicket);
		username.setText(getUsername.toUpperCase());
		System.err.println(getUsername);
		
		
		
		createTicket.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getMessage = message.getText().toString();
				getTitle = title.getText().toString();
				mydata2=getValue();
				mydata2 =getValue();
				getRank = mydata2.get(0).getRank();
				System.err.println(getRank);
				int rank = Integer.parseInt(getRank);
				mydata1 =getLastID();
				if(mydata1.size()>0){
				getID = mydata1.get(0).getLast_ticket_id();
				}else{
					getID ="0";
				}
				int id_last = Integer.parseInt(getID);
				MyDBHelper dbAdapter = MyDBHelper
						.getDBAdapterInstance(getApplicationContext());
				dbAdapter.openDataBase();
				ContentValues initialValues = new ContentValues();
				initialValues.put("user_id", getUsername);
				initialValues.put("description", getMessage);
				initialValues.put("title", getTitle);
				initialValues.put("rank",  rank);

				long n = dbAdapter.insertRecordsInDB(
						"tasks", null, initialValues);
				/*help.displayer("new row inserted with id = "
						+ n, c);*/

				dbAdapter.close();
				
				MyDBHelper dbAdapter1 = MyDBHelper
						.getDBAdapterInstance(getApplicationContext());
				dbAdapter1.openDataBase();
				
				ContentValues initialValues1 = new ContentValues();
				initialValues1.put("id_user", getUsername);
				initialValues1.put("comment_text", getMessage);
				initialValues1.put("id_task", (id_last+1));

				long n1 = dbAdapter1.insertRecordsInDB(
						"comments", null, initialValues1);
				/*help.displayer("new row inserted with id = "
						+ n, c);*/

				dbAdapter1.close();
				
				Intent myIntent = new Intent(NewTicketActivity.this,MainActivity.class);
				myIntent.putExtra("username", getUsername);
				startActivity(myIntent);
				finishActivity(RESULT_OK);
				finish();
				
			}
		});
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && hasWindowFocus()) {
			Intent myIntent = new Intent(NewTicketActivity.this, MainActivity.class);
			myIntent.putExtra("username", getUsername);
        	startActivity(myIntent);
        	finishActivity(RESULT_OK);
			finish();
			return true;
		} else if ((keyCode == KeyEvent.KEYCODE_BACK)
				&& hasWindowFocus() == false) {
			Intent myIntent = new Intent(NewTicketActivity.this, MainActivity.class);
			myIntent.putExtra("username", getUsername);
        	startActivity(myIntent);
        	finishActivity(RESULT_OK);
			finish();
			return true;
		}else if ((keyCode == KeyEvent.KEYCODE_HOME)){
			finishActivity(RESULT_OK);
			finish();
		}

		return super.onKeyDown(keyCode, event);
	}
	
	public ArrayList<StoreValue> getValue() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM login WHERE user=\'"+getUsername+"\';";
		System.out.println(query);
		ArrayList<ArrayList<String>> stringList = dbAdapter
				.selectRecordsFromDBList(query, null);
		dbAdapter.close();
		ArrayList<StoreValue> usersList = new ArrayList<StoreValue>();

		for (int i = 0; i < stringList.size(); i++) {
			ArrayList<String> list = stringList.get(i);
			StoreValue user = new StoreValue();
			try {
				final StoreValue listItem = mydata2.get(i); // --CloneChangeRequired
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
	
	public ArrayList<StoreValueListQuest> getLastID() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM tasks ORDER BY _id DESC;";
		ArrayList<ArrayList<String>> stringList = dbAdapter
				.selectRecordsFromDBList(query, null);
		dbAdapter.close();
		ArrayList<StoreValueListQuest> questList = new ArrayList<StoreValueListQuest>();

		for (int i = 0; i < stringList.size(); i++) {
			ArrayList<String> list = stringList.get(i);
			StoreValueListQuest quest = new StoreValueListQuest();
			try {
				final StoreValueListQuest listItem = mydata1.get(i); // --CloneChangeRequired
				if (listItem != null) {
					quest.last_ticket_id = list.get(0);
					System.out.println("List get 0: " + list.get(0));
					quest.rank = list.get(3);
					System.out.println("List get 1: " + list.get(1));
					System.out.println("List get 2: " + list.get(2));
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			questList.add(quest);
		}
		return questList;
	}
	

}
