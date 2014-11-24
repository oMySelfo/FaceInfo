package facebook;

import java.util.List;

import com.kla.faceinfo_ui1.MainActivity;
import com.kla.faceinfo_ui1.R;
import com.kla.faceinfo_ui1.R.id;
import com.kla.faceinfo_ui1.R.layout;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.Permission.Type;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnFriendsListener;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnLogoutListener;
import com.sromku.simple.fb.utils.Utils;

import database.DBManager;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TestFacebookDatabaseFrangment extends Fragment{
	public TestFacebookDatabaseFrangment(){}
	
	Button btnLogin;
	Button btnReset;
	TextView txtDetail;
	MainActivity ma;
	SimpleFacebook mSimpleFacebook;
	String result;
	DBManager dbManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_testfacebook, container,false);
		
		ma = (MainActivity) this.getActivity();
		dbManager = ma.getDbManager();
		mSimpleFacebook = SimpleFacebook.getInstance();
		btnLogin = (Button) rootView.findViewById(R.id.login_facebook_button);
		btnReset = (Button) rootView.findViewById(R.id.reset_button);
		txtDetail = (TextView) rootView.findViewById(R.id.facebook_detail_text);
		
		

		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSimpleFacebook.login(new OnLoginListener() {
					@Override
					public void onFail(String reason) {
						
					}
					
					@Override
					public void onException(Throwable throwable) {
						
					}
					
					@Override
					public void onThinking() {
						
					}
					
					@Override
					public void onNotAcceptingPermissions(Type type) {
						
					}
					
					@Override
					public void onLogin() {
						result="";
						Properties properties = new Properties.Builder()
						.add(Profile.Properties.ID)
						.add(Profile.Properties.FIRST_NAME)
						.add(Profile.Properties.LAST_NAME)
						.add(Profile.Properties.BIRTHDAY)
						.add(Profile.Properties.AGE_RANGE)
						.add(Profile.Properties.EMAIL)
						.add(Profile.Properties.GENDER)
						.add(Profile.Properties.INSTALLED)
						.add(Profile.Properties.NAME)
						.build();
						
						mSimpleFacebook.getFriends(properties, new OnFriendsListener() {
							

							@Override
							public void onThinking() {
							}

							@Override
							public void onException(Throwable throwable) {
								txtDetail.setText(throwable.getMessage());
							}

							@Override
							public void onFail(String reason) {
								txtDetail.setText(reason);
							}

							@Override
							public void onComplete(List<Profile> response) {
								
								// make the result more readable. 
								
								result += Utils.join(response.iterator(), "<br>", new Utils.Process<Profile>() {
									@Override
									public String process(Profile profile) {
										System.out.println(profile.getName());
										dbManager.insertContacts(profile.getName());
										return  profile.getName();
									}
								});
								result += "<br>";
								txtDetail.setText(Html.fromHtml(result));
								


							}

						});
					}
				});
				
			}
		});
		
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSimpleFacebook.logout(new OnLogoutListener() {
					
					@Override
					public void onFail(String reason) {
						txtDetail.setText(reason);
						
					}
					
					@Override
					public void onException(Throwable throwable) {
						
					}
					
					@Override
					public void onThinking() {
						
					}
					
					@Override
					public void onLogout() {
						
						txtDetail.setText("Allready Logout.");
					}
				});
			}
		});
		
		
		
		
		return rootView;
	}

}
