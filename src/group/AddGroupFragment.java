package group;

import com.kla.faceinfo_ui1.MainActivity;
import com.kla.faceinfo_ui1.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class AddGroupFragment extends Fragment {
	public AddGroupFragment() {
	}

	String arr_images[] = { R.drawable.family_cl + "", R.drawable.friend_cl + "",
			R.drawable.work_cl + "" };
	
	private MainActivity ma = (MainActivity) this.getActivity();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.actitvity_addgroup,
				container, false);
		
		
		Spinner spinnerPicGroup = (Spinner) rootView
				.findViewById(R.id.spinner_picgroup);
		spinnerPicGroup.setAdapter(new MyAdapter(ma,
				R.layout.activity_rowaddgroup, arr_images));
		
		return rootView;
	}

	public class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {

			LayoutInflater inflater = ma.getLayoutInflater();
			View row = inflater.inflate(R.layout.activity_rowaddgroup, parent,
					false);

			ImageView icon = (ImageView) row.findViewById(R.id.image);
			icon.setImageResource(Integer.parseInt(arr_images[position]));

			return row;
		}
	}

}
