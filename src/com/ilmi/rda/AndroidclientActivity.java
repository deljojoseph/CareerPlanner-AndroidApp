package com.ilmi.rda;

import java.util.ArrayList;
import java.util.List;

import com.ilmi.helpers.ButtonClass;
import com.ilmi.helpers.ImageAdapter;
import com.ilmi.rda.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AndroidclientActivity extends Activity {
	/** Called when the activity is first created. */

	private List<ButtonClass> gridButton = new ArrayList<ButtonClass>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		updateList();
		GridView gridview = (GridView) findViewById(R.id.gridview);

		gridview.setAdapter(new ImageAdapter(this, gridButton));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				gridButton.get(position).clickAction();
				/**
				 * Toast.makeText(AndroidclientActivity.this, "" + position,
				 * Toast.LENGTH_SHORT).show();
				 **/
			}
		});

		this.getResources();

	}

	private void updateList() {

		gridButton.add(new ButtonClass(AndroidclientActivity.this,
				StuMyAccount.class, R.string.MyyAccount,
				R.drawable.confirm_water_supply));
		gridButton.add(new ButtonClass(AndroidclientActivity.this,
				stuMyMentor.class, R.string.MyyMentor,
				R.drawable.explorer2));
		gridButton.add(new ButtonClass(AndroidclientActivity.this,
				Login.class, R.string.Calender,
				R.drawable.options));
		gridButton.add(new ButtonClass(AndroidclientActivity.this,
				Login.class, R.string.addMen,
				R.drawable.options));
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection

		switch (item.getItemId()) {
		case R.id.menuitem_aboutus: {
			dialogAboutBuilder();
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public void dialogAboutBuilder() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("About.");
		builder.setMessage(getString(R.string.aboutus));
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(this, R.style.CustomTheme));
		builder.setTitle(R.string.app_name);
		builder.setMessage("Are you sure want to Logout?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								AndroidclientActivity.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
}