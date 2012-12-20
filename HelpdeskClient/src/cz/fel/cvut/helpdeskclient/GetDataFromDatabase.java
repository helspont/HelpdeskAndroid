package cz.fel.cvut.helpdeskclient;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class GetDataFromDatabase extends ArrayList<StoreValueListQuest> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Context con;
	// String table;
	int size = 0;
	private ArrayList<StoreValueListQuest> mydata;

	// private String table;

	public GetDataFromDatabase(Context cont, String table) {
		super();
		this.con = cont;
		mydata = getValueInit(table);
	}

	public ArrayList<StoreValueListQuest> getValueInit(String table) {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(con);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM " + table + ";";
		Log.e("QUERY", query);
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
					quest.index = list.get(0);
					quest.name = list.get(1);
					// System.out.println("List get 0: " + list.get(0));
					quest.type = list.get(2);
					// System.out.println("List get 1: " + list.get(1));
					// System.out.println("List get 2: " + list.get(2));
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			questList.add(quest);
			size = i;
		}
		return questList;
	}

	public ArrayList<StoreValueListQuest> getValue(String table, int index) {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(con);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM " + table + ";";
		Log.e("QUERY", query);
		ArrayList<ArrayList<String>> stringList = dbAdapter
				.selectRecordsFromDBList(query, null);
		dbAdapter.close();
		ArrayList<StoreValueListQuest> questList = new ArrayList<StoreValueListQuest>();

		ArrayList<String> list = stringList.get(index);
		StoreValueListQuest quest = new StoreValueListQuest();
		try {
			final StoreValueListQuest listItem = mydata.get(index); // --CloneChangeRequired
			if (listItem != null) {
				quest.index = list.get(0);
				quest.question = list.get(1);
				// System.out.println("List get 0: " + list.get(0));
				quest.atr1 = list.get(2);
				// System.out.println("List get 1: " + list.get(1));
				quest.atr2 = list.get(3);
				// System.out.println("List get 2: " + list.get(2));
				quest.atr3 = list.get(4);
				// System.out.println("List get 3: " + list.get(3));
				quest.header = list.get(5);
			}

		} catch (Exception e) {

			// Log.i("***" + LoginActivity.class.toString(),
			// e.getMessage());
		}
		questList.add(quest);
		size = index;
		return questList;
	}

	public ArrayList<StoreValueListQuest> getValueAll(String table) {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(con);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM " + table + ";";
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
					quest.device_id = list.get(1);
					quest.question = list.get(1);
					// System.out.println("List get 0: " + list.get(0));
					quest.atr1 = list.get(2);
					// System.out.println("List get 1: " + list.get(1));
					quest.atr2 = list.get(3);
					// System.out.println("List get 2: " + list.get(2));
					quest.atr3 = list.get(4);
					// System.out.println("List get 3: " + list.get(3));
					quest.header = list.get(5);
					quest.device_id = list.get(44);
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			questList.add(quest);
			size = i;
		}
		return questList;
	}

	public ArrayList<StoreValueListQuest> getValueAllWhere(String table,
			String where) {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(con);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM " + table + " WHERE type = \"" + where
				+ "\";";
		Log.e("QUERY", query);
		ArrayList<ArrayList<String>> stringList = dbAdapter
				.selectRecordsFromDBList(query, null);
		// Log.e(" STEP 1", "IN");
		dbAdapter.close();
		ArrayList<StoreValueListQuest> questList = new ArrayList<StoreValueListQuest>();
		// Log.e(" STEP 2", "IN");
		for (int i = 0; i < stringList.size(); i++) {
			// Log.e(" STEP FOR"+i, "IN");
			ArrayList<String> list = stringList.get(i);
			// Log.e(" STEP 3", "IN");
			StoreValueListQuest quest = new StoreValueListQuest();
			// Log.e(" STEP 4", "IN");
			try {
				final StoreValueListQuest listItem = mydata.get(i); // --CloneChangeRequired
				if (listItem != null) {
					quest.name = list.get(1);
					// Log.e(" STEP 5", "IN"+list.get(1));
					// System.out.println("List get 0: " + list.get(0));
					quest.type = list.get(2);
					// System.out.println("List get 1: " + list.get(1));
					// System.out.println("List get 2: " + list.get(2));
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			questList.add(quest);
			size = i;
		}
		return questList;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return (size + 1);
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<StoreValueListQuest> getNuberOfRows(String table) {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(con);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();
		// SELECT Count(*) FROM tblName
		String query = "SELECT Count(*) FROM " + table + ";";
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
					quest.count = list.get(0);
					// System.out.println("List get 1: " + list.get(0));
					/*
					 * quest.question = list.get(1);
					 * System.out.println("List get 0: " + list.get(0));
					 * quest.atr1 = list.get(2);
					 * System.out.println("List get 1: " + list.get(1));
					 * quest.atr2 = list.get(3);
					 * System.out.println("List get 2: " + list.get(2));
					 * quest.atr3 = list.get(4);
					 * System.out.println("List get 3: " + list.get(3));
					 */
				}

			} catch (Exception e) {

				// Log.i("***" + LoginActivity.class.toString(),
				// e.getMessage());
			}
			questList.add(quest);
			size = i;
		}
		return questList;
	}
	
	public ArrayList<StoreValueListQuest> getValueAllWhereDate(String table,
			String whereStatment, String value) {

		MyDBHelper dbAdapter = MyDBHelper.getDBAdapterInstance(con);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		String query = "SELECT * FROM " + table + " WHERE "+ whereStatment +"\""+ value +"\"" ;
		Log.e("QUERY", query);
		ArrayList<ArrayList<String>> stringList = dbAdapter
				.selectRecordsFromDBList(query, null);
		// Log.e(" STEP 1", "IN");
		dbAdapter.close();
		ArrayList<StoreValueListQuest> questList = new ArrayList<StoreValueListQuest>();
		// Log.e(" STEP 2", "IN");
		for (int i = 0; i < stringList.size(); i++) {
			// Log.e(" STEP FOR"+i, "IN");
			ArrayList<String> list = stringList.get(i);
			// Log.e(" STEP 3", "IN");
			StoreValueListQuest quest = new StoreValueListQuest();
			// Log.e(" STEP 4", "IN");
			try {
				final StoreValueListQuest listItem = mydata.get(i); // --CloneChangeRequired
				if (listItem != null) {
					
					quest.questionnaire_id = list.get(1);
					// Log.e(" STEP 5", "IN"+list.get(1));
					 System.out.println("List get 0: " + list.get(0));
					quest.patient_id = list.get(2);
					//ArrayList<String> tempValues = new ArrayList<String>();
					//for()
					quest.answer0 = list.get(3);
					quest.answer1 = list.get(4);
					quest.answer2 = list.get(5);
					quest.answer3 = list.get(6);
					quest.answer4 = list.get(7);
					quest.answer5 = list.get(8);
					quest.answer6 = list.get(9);
					quest.answer7 = list.get(10);
					quest.answer8 = list.get(11);
					quest.answer9 = list.get(12);
					quest.answer10 = list.get(13);
					quest.answer11 = list.get(14);
					quest.answer12 = list.get(15);
					quest.answer13 = list.get(16);
					quest.answer14 = list.get(17);
					quest.answer15 = list.get(18);
					quest.answer16 = list.get(19);
					quest.answer17 = list.get(20);
					quest.answer18 = list.get(21);
					quest.answer19 = list.get(22);
					quest.answer20 = list.get(23);
					quest.answer21 = list.get(24);
					quest.answer22 = list.get(25);
					quest.answer23 = list.get(26);
					quest.answer24 = list.get(27);
					quest.answer25 = list.get(28);
					quest.answer26 = list.get(29);
					quest.answer27 = list.get(30);
					quest.answer28 = list.get(31);
					quest.answer29 = list.get(32);
					quest.answer30 = list.get(33);
					quest.answer31 = list.get(34);
					quest.answer32 = list.get(35);
					quest.answer33 = list.get(36);
					quest.answer34 = list.get(37);
					quest.answer35 = list.get(38);
					quest.answer36 = list.get(39);
					quest.answer37 = list.get(40);
					quest.answer38 = list.get(41);
					quest.answer39 = list.get(42);
					quest.answer40 = list.get(43);
					quest.device_id = list.get(44);
					quest.date = list.get(45);
					 System.out.println("List get 1: " + list.get(1));
					 System.out.println("List get 2: " + list.get(2));
					 System.out.println("List get 1: " + list.get(3));
				}

			} catch (Exception e) {

				Log.e("*********ERORR", e.getMessage());
			}
			questList.add(quest);
			size = i;
		}
		return questList;
	}
}
