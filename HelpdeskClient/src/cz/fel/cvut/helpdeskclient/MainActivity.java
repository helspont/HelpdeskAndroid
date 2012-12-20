package cz.fel.cvut.helpdeskclient;

import java.io.IOException;
import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	Button new_ticket;
	String getUsername="";
	TextView username;
	ListView list;
	ViewFlipper flipper;
	int status;
	private ArrayList<StoreValueListQuest> mydata = getValue();
    protected String[] item;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		new_ticket = (Button) findViewById(R.id.newTicket);
		username = (TextView) findViewById(R.id.username);
		getUsername = getIntent().getStringExtra("username");
		username.setText(getUsername.toUpperCase());
		list = (ListView)findViewById(R.id.listView1);
		flipper =(ViewFlipper)findViewById(R.id.flipper);
		status = flipper.getDisplayedChild();
		/*
		mydata = getValue();
		item = new String [mydata.size()];
		for (int i=0; i < mydata.size(); i++){
			item [i] = mydata.get(i).getName();
		}
		
		for (int i=0; i < mydata.size(); i++){
			System.out.println(item [i]);
		}

		list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item));
	
		*/
		new_ticket.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				Log.d("Registred listener", "in onClick");
				Intent myIntent = new Intent(MainActivity.this, NewTicketActivity.class);
				Log.d("Registred listener", "after myIntent creation");
				myIntent.putExtra("username", getUsername);
				Log.d("Registred listener", "after putExtra");
				
				startActivityForResult(myIntent, RESULT_OK);
				
				finishActivity(RESULT_OK);
				finish();
				
			}
		});
	}
	
public ArrayList<StoreValueListQuest> getValue() {

	MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(this);
	try {
		dbAdapter.createDataBase();
	} catch (IOException e) {
		Log.i("*** select ", e.getMessage());
	}
	dbAdapter.openDataBase();

	String query = "SELECT * FROM tasks WHERE status ="+status+";";
	ArrayList<ArrayList<String>> stringList = dbAdapter
			.selectRecordsFromDBList(query, null);
	dbAdapter.close();
	ArrayList<StoreValueListQuest> questList = new ArrayList<StoreValueListQuest>();

	for (int i = 0; i < stringList.size(); i++) {
		ArrayList<String> list = stringList.get(i);
		StoreValueListQuest quest = new StoreValueListQuest();
		try {
			final StoreValueListQuest listItem = mydata.get(i); // --CloneChangeRequired
			if (listItem != null) {
				quest.name = list.get(1);
				System.out.println("List get 0: " + list.get(0));
				quest.type = list.get(2);
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
