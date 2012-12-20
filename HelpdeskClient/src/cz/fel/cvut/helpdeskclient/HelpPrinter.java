package cz.fel.cvut.helpdeskclient;

import android.content.Context;
import android.widget.Toast;

public class HelpPrinter {
/**	Context name ;
	Object obj;
	public HelpPrinter(Object o, Context context) {
		super();
		name = context;
		obj = o;
		
		displayer(obj, name);
		
	}*/
	 void displayer(Object obj, Context con){
		String str1 ="";
		str1 = obj.toString();
		Context contxt = con;
		Toast.makeText(
				contxt,
				""+str1, Toast.LENGTH_SHORT).show();
	}
	
}
