package group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kla.faceinfo_ui1.MainActivity;
import com.kla.faceinfo_ui1.R;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class GrouplistFragment extends Fragment{
	public GrouplistFragment() {}
	
	
	String[][] dataGroup = new String[][] { { "Friends", R.drawable.friends + "" },
			{ "Family", R.drawable.family + "" },{"Work", R.drawable.work + "" }};
	
	
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
		
		
		
		return rootView;
	}
	

}
