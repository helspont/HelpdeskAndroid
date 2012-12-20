package cz.fel.cvut.helpdeskclient;

import java.io.IOException;
import java.util.ArrayList;


import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TicketViewActivity extends ListActivity {
	private ArrayList<StoreValueListQuest> mydata/* = getValue()*/;
	String getTask_id, getTitle,getUsername,getMessage;
	TextView title;
	String task_id;
	Button send, set_wait, close;
	EditText message;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticketview);
		getTask_id = getIntent().getStringExtra("task_id");
		getTitle = getIntent().getStringExtra("title");
		getUsername = getIntent().getStringExtra("username");
		title = (TextView) findViewById(R.id.titleView);
		send = (Button)findViewById(R.id.commentButton);
		set_wait = (Button)findViewById(R.id.waitButton);
		close = (Button)findViewById(R.id.closeButton);
		message = (EditText)findViewById(R.id.commentText);
		title.setText(getTitle);
		
		mydata = getValues();
		mydata = getValues();
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, mydata);
		    setListAdapter(adapter);
		    
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getMessage = message.getText().toString();
				MyDBHelper dbAdapter1 = MyDBHelper
						.getDBAdapterInstance(getApplicationContext());
				dbAdapter1.openDataBase();
				
				ContentValues initialValues1 = new ContentValues();
				ContentValues initialValues2 = new ContentValues();
				initialValues1.put("id_user", getUsername);
				initialValues1.put("comment_text", getMessage);
				initialValues1.put("id_task", getTask_id);
				initialValues2.put("status", 1);
				long n1 = dbAdapter1.insertRecordsInDB(
						"comments", null, initialValues1);
				
				long n2 = dbAdapter1.updateRecordInDB("tasks", initialValues2, "title=?",new String[] {getTitle});
				/*help.displayer("new row inserted with id = "
						+ n, c);*/

				dbAdapter1.close();
				Intent myIntent = new Intent(TicketViewActivity.this, TicketViewActivity.class).putExtra("title", getTitle);
				myIntent.putExtra("task_id", getTask_id);
				myIntent.putExtra("username", getUsername);
				startActivity(myIntent);
				finishActivity(RESULT_OK);
				finish();
			}
		});
		
		set_wait.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getMessage = message.getText().toString();
				MyDBHelper dbAdapter1 = MyDBHelper
						.getDBAdapterInstance(getApplicationContext());
				dbAdapter1.openDataBase();
				ContentValues initialValues2 = new ContentValues();
				initialValues2.put("status", 2);
				
				long n2 = dbAdapter1.updateRecordInDB("tasks", initialValues2, "title=?",new String[] {getTitle});
				/*help.displayer("new row inserted with id = "
						+ n, c);*/

				dbAdapter1.close();
				Intent myIntent = new Intent(TicketViewActivity.this, MainActivity.class).putExtra("title", getTitle);
				myIntent.putExtra("task_id", getTask_id);
				myIntent.putExtra("username", getUsername);
				startActivity(myIntent);
				finishActivity(RESULT_OK);
				finish();
			
				
			}
		});
		
		close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getMessage = message.getText().toString();
				MyDBHelper dbAdapter1 = MyDBHelper
						.getDBAdapterInstance(getApplicationContext());
				dbAdapter1.openDataBase();
				ContentValues initialValues2 = new ContentValues();
				initialValues2.put("status", 3);
				
				long n2 = dbAdapter1.updateRecordInDB("tasks", initialValues2, "title=?",new String[] {getTitle});
				/*help.displayer("new row inserted with id = "
						+ n, c);*/

				dbAdapter1.close();
				Intent myIntent = new Intent(TicketViewActivity.this, MainActivity.class).putExtra("title", getTitle);
				myIntent.putExtra("task_id", getTask_id);
				myIntent.putExtra("username", getUsername);
				startActivity(myIntent);
				finishActivity(RESULT_OK);
				finish();
			}
		});
	}
	
	public ArrayList<StoreValueListQuest> getValues() {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM comments WHERE id_task ="+getTask_id+";";
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
					quest.username = list.get(1);
					System.out.println("List get 0: " + list.get(0));
					quest.question = list.get(3);
					System.out.println("List get 1: " + list.get(1));
					System.out.println("List get 2: " + list.get(2));
					System.out.println("List get 3: " + list.get(3));
					
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			questList.add(quest);
		}
		return questList;
	}
	
	
	
	private class MySimpleArrayAdapter extends ArrayAdapter<StoreValueListQuest> {
		  private final Context context;
		  private final ArrayList<StoreValueListQuest> values;

		  public MySimpleArrayAdapter(Context context, ArrayList<StoreValueListQuest> values) {
		    super(context, R.layout.rowlayout, values);
		    this.context = context;
		    this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		    TextView textView1 = (TextView) rowView.findViewById(R.id.userTextView);
		    TextView textView2 = (TextView) rowView.findViewById(R.id.userCommentTextView);
		    StoreValueListQuest message = values.get(position);

            if (message != null)
            {
                textView1.setText(message.getUsername());
                textView2.setText(message.getQuestion());
            }

		    return rowView;
		  }
		} 

}
