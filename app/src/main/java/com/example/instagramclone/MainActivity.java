package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtgetData;
    private Button bntGetAllData,btnTransition;
    private String allKickBoxers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(MainActivity.this);

        edtName = findViewById(R.id.edtName);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        txtgetData = findViewById(R.id.txtGetData);
        bntGetAllData = findViewById(R.id.btnAllData);
        btnTransition = findViewById(R.id.btnTransition);

        txtgetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("q6Z1E3WiTH", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtgetData.setText(object.get("name") + "" + " Punch Power: " + object.get("punchPower"));

                        }

                    }
                });
            }
        });

        bntGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
//                queryAll.whereGreaterThan("punchPower",2000);
                queryAll.whereGreaterThanOrEqualTo("punchPower",3000);
                queryAll.setLimit(1);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if ( e == null ){
                            if ( objects.size() > 0) {
                                for (ParseObject kickBoxer : objects){
                                allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n"; }
                                FancyToast.makeText(MainActivity.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            }
                            else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();}

////                               Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();


                        }
                    }
                });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpLogInActivity.class);
                startActivity(intent);

            }
        });

                    }


                    @Override
                    public void onClick(View v) {
                        try {
                            final ParseObject kickBoxer = new ParseObject("KickBoxer");

                            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
                            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
                            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickPower.getText().toString()));
                            kickBoxer.put("kickPower", Integer.parseInt(edtKickSpeed.getText().toString()));
                            kickBoxer.put("name", edtName.getText().toString());

                            kickBoxer.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        FancyToast.makeText(MainActivity.this, kickBoxer.get("name") + " is saved on server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                    } else {
                                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                    }

                                }
                            });
                        } catch (Exception e) {
                            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }

                    }
                }