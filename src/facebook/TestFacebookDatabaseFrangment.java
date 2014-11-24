package facebook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
		
		result = "https://graph.facebook.com/1651958423/picture?type=large";
		downloadFile(result);

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
						.add(Profile.Properties.PICTURE)
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


								Utils.join(response.iterator(), "<br>", new Utils.Process<Profile>() {
									@Override
									public String process(Profile profile) {
										dbManager.insertContactsFacebook(profile);
										System.out.println(profile.getId());
										return  profile.getName();
									}
								});

								
									for(String con_name: dbManager.selectContacts()){
										result+=con_name;
										result += "<br>";
									}
								
									
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
	

	void downloadFile(String uRl) {
	    File direct = new File(Environment.getExternalStorageDirectory()
	            + "/Faceinfo/Img");

	    if (!direct.exists()) {
	        direct.mkdirs();
	    }

	    DownloadManager mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

	    Uri downloadUri = Uri.parse(uRl);
	    DownloadManager.Request request = new DownloadManager.Request(
	            downloadUri);

	    request.setAllowedNetworkTypes(
	            DownloadManager.Request.NETWORK_WIFI
	                    | DownloadManager.Request.NETWORK_MOBILE)
	            .setAllowedOverRoaming(false).setTitle("Demo")
	            .setDescription("Something useful. No, really.")
	            .setDestinationInExternalPublicDir("/Faceinfo/Img", "fileName.jpg");

	    mgr.enqueue(request);

	    
	}

}
