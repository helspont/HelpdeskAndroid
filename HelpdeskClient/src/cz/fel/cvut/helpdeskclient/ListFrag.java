package cz.fel.cvut.helpdeskclient;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ListFrag extends ListFragment {
	Button new_ticket;
	String getUsername="";
	TextView username;
	ListView list1,list2,list3,list4;
	ViewFlipper flip;
	Context c;

	int status = 0;
	//private ArrayList<StoreValueListQuest> mydata = getValue();
    protected String[] items;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		c = getActivity().getApplicationContext();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] { "New", "Open",
				"Waiting on user", "Closed"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		DetailFrag frag = (DetailFrag) getFragmentManager().findFragmentById(
				R.id.frag_capt);
		/*list1 = (ListView)getActivity().findViewById(R.id.listView1);
		list2 = (ListView)getActivity().findViewById(R.id.listView2);
		list3 = (ListView)getActivity().findViewById(R.id.listView3);
		list4 = (ListView)getActivity().findViewById(R.id.listView4);
		flip = (ViewFlipper)getActivity().findViewById(R.id.flipper);
		
		status = flip.getDisplayedChild();*/
		/*mydata = getValue();
		items = new String [mydata.size()];
		for (int i=0; i < mydata.size(); i++){
			items [i] = mydata.get(i).getName();
		}
		
		for (int i=0; i < mydata.size(); i++){
			System.out.println(items [i]);
		}

		list1.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
		*/
		
		if (frag != null && frag.isInLayout()) {
			frag.setFlipper((getCapt(item)));
			//frag.selectList((getCapt(item)));
			for(int i = 0;i<2;i++){
				frag.selectList((getCapt(item)));
			}
		}
		
		
	}

	private int getCapt(String ship) {
		if (ship.toLowerCase().contains("new")) {
			return 1;
		}
		if (ship.toLowerCase().contains("open")) {
			return 2;
		}
		if (ship.toLowerCase().contains("waiting on user")) {
			return 3;
		}
		if (ship.toLowerCase().contains("closed")) {
			return 4;
		}
		return 0;
	}
	
	/*public void selectList(int number){
		status = flip.getDisplayedChild();
		mydata = getValue();
		System.out.println(""+status);
		items = new String [mydata.size()];
		for (int i=0; i < mydata.size(); i++){
			items [i] = mydata.get(i).getName();
		}
		
		for (int i=0; i < mydata.size(); i++){
			System.out.println(items [i]);
		}
		
		//if(number==1){
			list1.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
		//}else if(number==2){
			list2.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
		//}else if(number==3){
			list3.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
		//}else{
			list4.setAdapter(new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,items));
		//}
		
	}
	
	public ArrayList<StoreValueListQuest> getValue() {

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
	}*/
}
