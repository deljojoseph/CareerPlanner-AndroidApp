package com.sliit.rda;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity {
	private static String NAMESPACE = "http://wm.ilmi.com";
	private static String METHOD_NAME = "";
	private static String SOAP_ACTION = METHOD_NAME+NAMESPACE;
	private static String URL = "http://54.250.213.210:8080/axis2/services/servicemethods?wsdl";
	

	private Button butClose;
	private Button btLogin;
	private boolean finishedProcess = false;
	private ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();
	private SoapPrimitive response = null;
	private RadioGroup radioTypeGroup;
	private RadioButton radioTypeButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);

		butClose = (Button) findViewById(R.id.login_view_bt_cancel);
		butClose.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onBackPressed();
			}
		});

		btLogin = (Button) findViewById(R.id.login_view_bt_login);
		btLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				
				progressBar = new ProgressDialog(arg0.getContext());
				progressBar.setCancelable(true);
				progressBar.setMessage("Waiting for authentication from web service...");
				progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressBar.setProgress(0);
				progressBar.setMax(100);
				progressBar.show();
				progressBarStatus = 0;
				((TextView) findViewById(R.id.errortv)).setText("");
				
				new Thread(new Runnable() {
					public void run() {
						while (progressBarStatus < 100) {

							try {
								Thread.sleep(1000);
								
								 try {
							        	
									 EditText end=(EditText) findViewById(R.id.usret);
									 String usrid= end.getText().toString();
									 EditText pa=(EditText) findViewById(R.id.passet);
									 String pass= pa.getText().toString();
									 
									 System.out.println(usrid);
									 System.out.println(pass);
									
							        	METHOD_NAME = "chkUser";
							        	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
							            
							        	request.addProperty("uid",usrid);
							        	request.addProperty("pass",pass);
							        	
							            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
							                    SoapEnvelope.VER11);
							            envelope.dotNet = true;
							            envelope.setOutputSoapObject(request);
							            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
							            androidHttpTransport.call(SOAP_ACTION, envelope);
							            response = (SoapPrimitive)envelope.getResponse();
							            //Object result = envelope.getResponse();
							            progressBarStatus = progressBarStatus + 100;
							            
							   
							          
							         
							           
							            
							            
							         
							            
							            
							        } catch (Exception e) {
							            e.printStackTrace();
							        }		
								finishedProcess = true;
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							progressBarHandler.post(new Runnable() {
								public void run() {
									progressBar.setProgress(progressBarStatus);
								}
							});
						}
						if (progressBarStatus >= 100) {
							
							try {
								Thread.sleep(2000);
								
								// UI running Thread !!
								runOnUiThread(new Runnable() {
								     public void run() {
								    		if(finishedProcess == true){
								    			try {
								    				  if(response.toString().equals("true"))
													     {
								    					  radioTypeGroup = (RadioGroup) findViewById(R.id.radioType);
								    					  int selectedId = radioTypeGroup.getCheckedRadioButtonId();
								    					  
								    						// find the radiobutton by returned id
								    					        radioTypeButton = (RadioButton) findViewById(selectedId);
								    			 
								    						//Toast.makeText(Login.this,
								    							//radioTypeButton.getText(), Toast.LENGTH_SHORT).show();
								    					  
													    	 System.out.println("login success "+ response.toString());
													    	 
													    	 if(radioTypeButton.getText().equals("Student"))
													    	 {
													    	 Intent intent = new Intent(Login.this,
																		AndroidclientActivity.class);
																Login.this.startActivity(intent);
													    	 }
													    	 if(radioTypeButton.getText().equals("Mentor"))
													    	 {
													    		 Intent intent = new Intent(Login.this,
																			AndroidclientActivity2.class);
																	Login.this.startActivity(intent);
													    	 }
													    	 else
													    	 {
													    		 //((TextView) findViewById(R.id.errortv)).setText("Please select login type before proceed..");
													    	 }
																
																
													    	 
													     }
													     else
													     {
													    	 
													    	System.out.println("login error"+ response.toString());
													    	 ((TextView) findViewById(R.id.errortv)).setText("Invalid Username or Password may be your account has inactive contact Administrator");
													     }
								    			} catch (Exception e) {
								    				e.printStackTrace();
								    			}
												finishedProcess = false;
											}
								    }
								});	
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							progressBar.dismiss();
						}
					}
				}).start();
				
				
				
			
			}
		});

	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(this, R.style.CustomTheme));
		builder.setTitle(R.string.app_name);
		builder.setMessage("Are you sure want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Login.this.finish();
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
