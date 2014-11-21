package widget;

import android.content.Context;
import android.text.Editable;
import android.widget.EditText;

public class CreateEdittext extends EditText {

	public CreateEdittext(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	private boolean show;
//	
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}


}
