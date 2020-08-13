package sg.edu.rp.c346.id19036308.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView tvCat, tvSubCat;
    Spinner spnCat, spnSubCat;
    Button btngo;
    ArrayList<String> alSubCat;
    ArrayAdapter<String> aaSubCat;

    String[][] sites={
            {
                "https://www.google.com/",
                    "http://www.rp.edu.sg/student-life",

            },
            {
                "http://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                    "http://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCat = findViewById(R.id.textViewCategory);
        tvSubCat = findViewById(R.id.textViewSubCategory);
        spnCat = findViewById(R.id.spinnerCategory);
        spnSubCat = findViewById(R.id.spinnerSubCategory);
        btngo = findViewById(R.id.buttonGo);


        //Initialise the ArrayList
        alSubCat = new ArrayList<>();

        //Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaSubCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,alSubCat);

        //Bind the ArrayAdapter to the Spinner
        spnSubCat.setAdapter(aaSubCat);

//        //Approach 2
//        //Get the string-array and store as an Array
//        String[] strCat = getResources().getStringArray(R.array.rpCat);
//
//        //Convert Array to List and add to the ArrayList
//        alCat.addAll(Arrays.asList(strCat));

        //Implement the button onClick() method
        // to load the correct number list when it is clicked
        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int pos = spnCat.getSelectedItemPosition();
                alSubCat.clear();

                //Apply either of the two approaches
                // to load the correct number List based on the selection of spn1
                if (pos == 0) {

                    //Approach 2
                    //Get the string-array and store as an Array
                    String[] strSubCat = getResources().getStringArray(R.array.rpCat);

                    //Convert Array to List and add to the ArrayList
                    alSubCat.addAll(Arrays.asList(strSubCat));
                }else if (pos == 1){
                    //Approach 2
                    //Get the string-array and store as an Array
                    String[] strSubCat = getResources().getStringArray(R.array.SOICat);

                    //Convert Array to List and add to the ArrayList
                    alSubCat.addAll(Arrays.asList(strSubCat));
                }
                //Update the Sub-Category spinner
                aaSubCat.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = spnSubCat.getSelectedItemPosition();

                String url = sites[spnCat.getSelectedItemPosition()][spnSubCat.getSelectedItemPosition()];

                //Create a new intent
                Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                //Insert the URL into the intent
                myIntent.putExtra("url", url);
                startActivity(myIntent);

            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Get the data selected by user
        int catPos = spnCat.getSelectedItemPosition();
        int subCatPos = spnSubCat.getSelectedItemPosition();

        //`Obtain the Default SharedPreference Object
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Create a SharedPreferences Editor by Calling edit()
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Write the keys and values into SharedPreferences via the Editor
        prefEdit.putInt("cat_position", catPos);
        prefEdit.putInt("subcat_position", subCatPos);

        //Call commit() to save changes made to sharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Obtain the Default SharedPreferences Object
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Retrieve the saved data from the sharedPreferences
        int catPos = prefs.getInt("cat_position", 0);
        int subCatPos = prefs.getInt("subcat_position", 0);

        spnCat.setSelection(catPos);

        alSubCat.clear();
        //Obtain the string array of the sub category
        if(catPos==0){
            String[] strSubCat = getResources().getStringArray(R.array.rpCat);
            alSubCat.addAll(Arrays.asList(strSubCat));

        }else if(catPos == 1){
            String[] strSubCat = getResources().getStringArray(R.array.SOICat);
            alSubCat.addAll(Arrays.asList(strSubCat));
        }
        spnSubCat.setSelection(subCatPos);

        //Update sub-category spinner
        aaSubCat.notifyDataSetChanged();
    }

}
