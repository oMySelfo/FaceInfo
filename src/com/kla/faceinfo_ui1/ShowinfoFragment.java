package com.kla.faceinfo_ui1;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowinfoFragment extends Fragment {
	public ShowinfoFragment() {
	}

	JSONObject result;
	MainActivity ma;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_showinfo, container,
				false);
		ImageView im_accuracy1 = (ImageView) rootView
				.findViewById(R.id.im_accuracy1);
		ImageView im_accuracy2 = (ImageView) rootView
				.findViewById(R.id.im_accuracy2);
		ImageView im_accuracy3 = (ImageView) rootView
				.findViewById(R.id.im_accuracy3);
		TextView txt_accuracy1 = (TextView) rootView
				.findViewById(R.id.txt_accuracy1);
		TextView txt_accuracy2 = (TextView) rootView
				.findViewById(R.id.txt_accuracy2);
		TextView txt_accuracy3 = (TextView) rootView
				.findViewById(R.id.txt_accuracy3);

		im_accuracy1.setImageResource(R.drawable.nopicture);
		im_accuracy2.setImageResource(R.drawable.nopicture);
		im_accuracy3.setImageResource(R.drawable.nopicture);
		txt_accuracy1.setText("");
		txt_accuracy2.setText("");
		txt_accuracy3.setText("");
		ma = (MainActivity) this.getActivity();
		result = ma.getResult();

		

		
		processing(im_accuracy1, txt_accuracy1, 0);
		processing(im_accuracy2, txt_accuracy2, 1);
		processing(im_accuracy3, txt_accuracy3, 2);

		return rootView;
	}

	void processing(ImageView img, TextView text ,int index) {
		try {

				String name = result.getJSONArray("face").getJSONObject(0)
						.getJSONArray("candidate").getJSONObject(index)
						.getString("person_name");
//				String acc = result.getJSONArray("face").getJSONObject(0)
//						.getJSONArray("candidate").getJSONObject(index)
//						.getString("person_name");
				int acc = (int)Float.parseFloat(result.getJSONArray("face")
						.getJSONObject(0)
						.getJSONArray("candidate").getJSONObject(index)
						.getString("confidence"));

				
				if(name.equals("kla7016_Fah"))
				{
					img.setImageResource(R.drawable.fah);
					text.setText("Fah\n" + acc + "%");
				}
				else if(name.equals("kla7016_Jay"))
				{
					img.setImageResource(R.drawable.jay);
					text.setText("Jay\n" + acc + "%");
				}
				else if(name.equals("kla7016_Mhee"))
				{
					img.setImageResource(R.drawable.mhee);
					text.setText("Mhee\n" + acc + "%");
				}
				else if(name.equals("kla7016_P-Mike"))
				{
					img.setImageResource(R.drawable.mike);
					text.setText("P-Mike\n" + acc + "%");
				}
				else if(name.equals("kla7016_Plam"))
				{
					img.setImageResource(R.drawable.palm);
					text.setText("Palm\n"+ acc + "%");
				}
				else if(name.equals("kla7016_Kla"))
				{
					img.setImageResource(R.drawable.kla);
					text.setText("Kla\n" + acc + "%");
				}
				else if(name.equals("kla7016_Tae"))
				{
					img.setImageResource(R.drawable.tae);
					text.setText("Tae\n" + acc + "%");
				}
				else if(name.equals("kla7016_Coach"))
				{
					img.setImageResource(R.drawable.coach);
					text.setText("Coach\n" + acc + "%");
				}

			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
