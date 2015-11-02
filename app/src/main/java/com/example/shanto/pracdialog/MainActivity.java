package com.example.shanto.pracdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    CharSequence[] items={"Google","Apple","Mictosoft","lenovo"};
    boolean[] itemsChecked=new boolean[items.length];
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onDialog2(View view){
        final ProgressDialog dialog=ProgressDialog.show(this,"Doing something","Please wait...",true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    dialog.dismiss();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void onDialog(View view){
        showDialog(0);
    }
    public void onDialog3(View view){
        showDialog(1);
       progressDialog.setProgress(0);
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=1;i<=100;i++){
                    try{
                    Thread.sleep(100);
                    progressDialog.incrementProgressBy((int)(100/100));}
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();

            }
        }).start();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.abc_ab_share_pack_mtrl_alpha);
                builder.setTitle("This is a dialog with simple text..");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Ok Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setMultiChoiceItems(items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(getBaseContext(), items[which] + (isChecked ? " Checked!" : " unchecked!"), Toast.LENGTH_SHORT).show();
                    }
                });
                return builder.create();

            case(1):
                progressDialog=new ProgressDialog(this);
                progressDialog.setIcon(R.drawable.notification_template_icon_bg);
                progressDialog.setTitle("Downloading files...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Ok clicked", Toast.LENGTH_LONG).show();
                    }
                });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_LONG).show();
                    }
                });
                return progressDialog;
        }
        return null;

    }
}
