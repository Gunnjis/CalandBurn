package com.example.admin.calandburn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Admin on 31/3/2559.
 */
public class EditProfile extends AppCompatActivity {

    //Explicit
    private String[] currentUserStrings;
    private EditText nameEditText, ageEditText, weightEditText, heightEditText;
    private TextView sexTextView, exericiseTextView;
    private RadioGroup sexRadioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Spinner exerciseSpinner;
    private String exerciseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Bind Widget
        bindWidget();

        //Read All Data User
        readAllDataUser();

        // Show View
        showView();

        //Create Spinner
        createSpinner();

        //Create Sex Radio
        createSexRadio();

    } //Main Method

    private void createSexRadio() {

        if (currentUserStrings[3].equals("male")) {
            maleRadioButton.setChecked(true);
        } else {
            femaleRadioButton.setChecked(true);
        }



    } //createSexRadio

    private void createSpinner() {

        String[] showStrings = getResources().getStringArray(R.array.my_act);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, showStrings);
        exerciseSpinner.setAdapter(stringArrayAdapter);

        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                if (i == 0) {
                    exerciseString = currentUserStrings[7];
                } else {
                    exerciseString = Integer.toString(i);
                } // if else
            } //onItem

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                exerciseString = currentUserStrings[7];
            }
        });

    }

    private void showView() {

        nameEditText.setText(currentUserStrings[2]);
        sexTextView.setText("เพศ: " + currentUserStrings[3]);
        ageEditText.setText(currentUserStrings[4]);
        heightEditText.setText(currentUserStrings[5]);
        weightEditText.setText(currentUserStrings[6]);
        exericiseTextView.setText("กิจกรรม: " + findCurrentExercise(currentUserStrings[7]));
    }

    private String findCurrentExercise(String currentUserString) {

        String[] myResultExerStrings = getResources().getStringArray(R.array.my_act);
        String strResult = myResultExerStrings[Integer.parseInt(currentUserString)];

        return strResult;
    }

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText7);
        ageEditText = (EditText) findViewById(R.id.editText8);
        heightEditText = (EditText) findViewById(R.id.editText9);
        weightEditText = (EditText) findViewById(R.id.editText10);
        sexTextView = (TextView) findViewById(R.id.txtsex);
        exericiseTextView = (TextView) findViewById(R.id.txtexercise);
        sexRadioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        maleRadioButton = (RadioButton) findViewById(R.id.radioButton3);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton4);
        exerciseSpinner = (Spinner) findViewById(R.id.spinner);


    } // Bind Widget

    private void readAllDataUser() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        cursor.moveToFirst();
        currentUserStrings = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            currentUserStrings[i] = cursor.getString(i);
        } // for
        cursor.close();

    }

    public void clickSaveEdit(View view) {

    }

} // main Class
