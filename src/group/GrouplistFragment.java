package group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kla.faceinfo_ui1.MainActivity;
import com.kla.faceinfo_ui1.R;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GrouplistFragment extends Fragment{
	public GrouplistFragment() {}
	
	
	public int a;
	String[][] dataGroup = new String[][] { { "Friends", R.drawable.friend_cl + "" },
			{ "Family", R.drawable.family_cl + "" },{"Work", R.drawable.work_cl + "" }};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.activity_listgroup, container,false);
		final MainActivity ma = (MainActivity) this.getActivity();
		
		
		
		TextView amount_group = (TextView) rootView.findViewById(R.id.amount_group);
		amount_group.setText(dataGroup.length + " Groups");
		String[] from = { "picGroup", "nameGroup","amountGroup" };
		int[] to = { R.id.picGroup, R.id.txtNameGroup,R.id.amount_groupDetail };
		
		List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < dataGroup.length; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("nameGroup", dataGroup[i][0]);
			hm.put("picGroup", dataGroup[i][1] + "");
			hm.put("amountGroup","7 People" );
			aList.add(hm);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(ma,aList,
				R.layout.acitivity_rowgroup, from, to);
		ListView listViewGroup = (ListView) rootView.findViewById(R.id.listgroup);
		listViewGroup.setAdapter(adapter);
		
		
		listViewGroup.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                LinearLayout linearLayoutParent = (LinearLayout) container;
                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);

                TextView txt = (TextView) linearLayoutChild.getChildAt(0);
                ma.namegroup2 = txt.getText().toString();
                System.out.println(ma.namegroup2);
                
                a=1;
                //peoofGroup Fragment
                ma.displayView(12);
            }
        });
		
		ImageButton bt_addGroup = (ImageButton) rootView.findViewById(R.id.btn_addgrop);
		bt_addGroup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ma.displayView(11);
			}
		});
		return rootView;
	}
	

}
