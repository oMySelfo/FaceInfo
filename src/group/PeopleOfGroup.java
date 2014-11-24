package group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kla.faceinfo_ui1.MainActivity;
import com.kla.faceinfo_ui1.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class PeopleOfGroup extends Fragment{
	public PeopleOfGroup(){}
	MainActivity ma; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_peopleofgroup, container,false);
		
		ma =  (MainActivity) this.getActivity();
		ma.setTitle(ma.namegroup2);
		
		
		
		TextView tv = (TextView) rootView.findViewById(R.id.amount_people);
		tv.setText(ma.data.length + " FRIENDS");
		
		String[] from = { "pic", "name" };
		int[] to = { R.id.pic, R.id.txtName };

		List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < ma.data.length; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("name", ma.data[i][0]);
			hm.put("pic", ma.data[i][1] + "");
			aList.add(hm);
		}

		SimpleAdapter adapter = new SimpleAdapter(ma, aList,
				R.layout.activity_row, from, to);
		ListView listView = (ListView) rootView.findViewById(R.id.listview_peoplegroup);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                LinearLayout linearLayoutParent = (LinearLayout) container;
                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);

                TextView txt = (TextView) linearLayoutChild.getChildAt(0);
                String namePeoPle = txt.getText().toString();
                System.out.println(namePeoPle);
            }
        });
		
		registerForContextMenu(listView);
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> view, View container,
					int position, long id) {
				view.showContextMenu();
				LinearLayout linearLayoutParent = (LinearLayout) container;
                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);

                TextView txt = (TextView) linearLayoutChild.getChildAt(0);
                String tv = txt.getText().toString();
                System.out.println(tv);
				return true;
			}
			
		});
		
		return rootView;
	}
	
	@Override 
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
            super.onCreateContextMenu(menu, v, menuInfo);
            //menu.setHeaderTitle("Select The Action");  
            menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title 
            menu.add(0, v.getId(), 0, "Edit");
    } 
	
	@Override  
    public boolean onContextItemSelected(MenuItem item){  
            if(item.getTitle()=="Delete"){
            	System.out.println("Delete");
            	alertDiaLog();
            } else{
               return false;
            }  
          return true;  
                            
      }
	private void alertDiaLog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(ma);
    	builder.setTitle("Delete").setIcon(getResources().getDrawable(R.drawable.newlogo))
    	.setMessage("Are you sure you want to delete ?")
    	.setPositiveButton("Not Now", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//delete profile
				ma.displayView(12);
			}
		});
    	
    	// Alert Dialog
    	AlertDialog alert = builder.create();
    	alert.show();
	}
}
