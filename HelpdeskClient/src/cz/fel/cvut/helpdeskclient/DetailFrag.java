package cz.fel.cvut.helpdeskclient;



import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

@SuppressLint("ShowToast")
public class DetailFrag extends Fragment {
	private ViewFlipper flipper;
	//private ArrayList<StoreValueListQuest> mydata = getValue();
    protected String[] item;
    ListView list1,list2,list3,list4;
	ViewFlipper flip;
	Context c;
	TextView username,category;
	String getUsername, taskID;
	String getRank="";
	HelpPrinter help;
	String selected;
	int status = 1;
	int rank =0;
	private ArrayList<StoreValue> mydata2 = getUserRank();
	private ArrayList<StoreValueListQuest> mydata/* = getValue()*/;
    protected String[] items;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		c =getActivity().getApplicationContext();
		mydata2= getUserRank();
		mydata2= getUserRank();
		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		username = (TextView)getActivity().findViewById(R.id.username);
		getUsername = username.getText().toString().toLowerCase();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.detail_fragment, container, false);
		//RelativeLayout rel = (RelativeLayout) inflater.inflate(R.layout.main, container, false);
		flipper = (ViewFlipper) view.findViewById(R.id.flipper);
		category = (TextView)view.findViewById(R.id.categoryView);
		list1 = (ListView)view.findViewById(R.id.listView1);
		list2 = (ListView)view.findViewById(R.id.listView2);
		list3 = (ListView)view.findViewById(R.id.listView3);
		list4 = (ListView)view.findViewById(R.id.listView4);
		/*
		mydata2= getUserRank();
		mydata2= getUserRank();
		mydata2= getUserRank();
		getRank = mydata2.get(0).getRank();
		rank = Integer.parseInt(getRank);
		*/
		//
		//help = new HelpPrinter();
		Toast.makeText(c, getUsername, Toast.LENGTH_LONG).show();
		status = flipper.getDisplayedChild();
		return view;
	}

	public void setFlipper(int type){
		
		switch (type) {
		case 1:
			flipper.setDisplayedChild(0);
			category.setText("Category: New");
			
			break;
		case 2:
			flipper.setDisplayedChild(1);
			category.setText("Category: Open");
			
			break;
		case 3:
			flipper.setDisplayedChild(2);
			category.setText("Category: Waiting on user");
			
			break;
		case 4:
			flipper.setDisplayedChild(3);
			category.setText("Category: Closed");
			
			break;

		default:
			flipper.setDisplayedChild(0);
			break;
		}
	}
	
	public void selectList(int number){
		mydata2= getUserRank();
		mydata2= getUserRank();
		getRank = mydata2.get(0).getRank();
		rank = Integer.parseInt(getRank);
		status = flipper.getDisplayedChild();
		if(rank==0){
		mydata = getValue();
		System.out.println(""+status);
		items = new String [mydata.size()];
		}else{
			mydata = getValueAll();
			System.out.println(""+status);
			items = new String [mydata.size()];
		}
		for (int i=0; i < mydata.size(); i++){
			items [i] = mydata.get(i).getTitle();
		}
		
		for (int i=0; i < mydata.size(); i++){
			System.out.println(items [i]);
		}
		
		if(number==1){
			list1.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
			
			list1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
				    selected = items[arg2];
					mydata = getTaskID();
					mydata = getTaskID();
					String task_id = mydata.get(0).getType();
					Intent myIntent = new Intent(c, TicketViewActivity.class).putExtra("title", selected);
					myIntent.putExtra("task_id", task_id);
					myIntent.putExtra("username", getUsername);
					startActivity(myIntent);
					
				}
			});
		}else if(number==2){
			list2.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
			
			list2.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					selected = items[arg2];
					mydata = getTaskID();
					mydata = getTaskID();
					String task_id = mydata.get(0).getType();
					Intent myIntent = new Intent(c, TicketViewActivity.class).putExtra("title", selected);
					myIntent.putExtra("task_id", task_id);
					myIntent.putExtra("username", getUsername);
					startActivity(myIntent);
					
				}
			});
		}else if(number==3){
			list3.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
			
			list3.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					selected = items[arg2];
					mydata = getTaskID();
					mydata = getTaskID();
					String task_id = mydata.get(0).getType();
					Intent myIntent = new Intent(c, TicketViewActivity.class).putExtra("title", selected);
					myIntent.putExtra("task_id", task_id);
					myIntent.putExtra("username", getUsername);
					startActivity(myIntent);
					
				}
			});
		}else{
			list4.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
			
			list4.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					selected = items[arg2];
					mydata = getTaskID();
					mydata = getTaskID();
					String task_id = mydata.get(0).getType();
					Intent myIntent = new Intent(c, TicketViewActivity.class).putExtra("title", selected);
					myIntent.putExtra("task_id", task_id);
					myIntent.putExtra("username", getUsername);
					startActivity(myIntent);
					
				}
			});
		}
		
	}
	
	public ArrayList<StoreValueListQuest> getValue() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(getActivity());
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM tasks WHERE status ="+status+" AND user_id=\""+getUsername+"\";";
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
					quest.title = list.get(1);
					System.out.println("List get 0: " + list.get(0));
					quest.user_id = list.get(3);
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
	
	
	public ArrayList<StoreValueListQuest> getValueAll() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(getActivity());
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
					quest.type = list.get(0);
					quest.title = list.get(1);
					System.out.println("List get 0: " + list.get(0));
					quest.user_id = list.get(3);
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
	
	public ArrayList<StoreValueListQuest> getTaskID() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(getActivity());
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM tasks WHERE title =\""+selected+"\";";
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
					quest.type = list.get(0);
					quest.title = list.get(1);
					System.out.println("List get 0: " + list.get(0));
					quest.user_id = list.get(3);
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
	
	
	public ArrayList<StoreValue> getUserRank() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(getActivity());
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
	/*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String quest_selected = item[position];
		String s = quest_selected.replaceAll(" ", "");
		Intent myIntent = new Intent(PatQuestionnaires.this, Questionnaire_patient.class).putExtra("table", s);
		myIntent.putExtra("index", 0);
		startActivity(myIntent);
	}*/
}
