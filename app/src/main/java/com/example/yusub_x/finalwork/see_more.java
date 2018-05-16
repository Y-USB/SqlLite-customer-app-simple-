package com.example.yusub_x.finalwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class see_more extends AppCompatActivity {
    TextView rname, rsurname, rfathername, rage, rgender, reducation, rphone;
    Button deleteCustomer, updateCustomer;
    database db = new database(this);
    CustomListViewAdapter adapter;
    TextView txtClickToUpdate;
    ScrollView scrollInfo, scrollUpdate;
    EditText _givenName,_givenSurname,_givenFatherName,_givenAge;
    Spinner _givenGender,_givenEducation,_givenOperator;
    EditText _givenNumber;
    model modelGelen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        rname = findViewById(R.id.txtname);
        rsurname = findViewById(R.id.txtsurname);
        rfathername = findViewById(R.id.txtfathername);
        rage = findViewById(R.id.txtage);
        rgender = findViewById(R.id.txtgender);
        reducation = findViewById(R.id.txteducation);
        rphone = findViewById(R.id.txtnumber);
        deleteCustomer = findViewById(R.id.btnDelete);
        txtClickToUpdate = findViewById(R.id.txtUpdateCustomer);
        scrollInfo = findViewById(R.id.scrollViewInformation);
        scrollUpdate = findViewById(R.id.scrollViewUpdate);
        updateCustomer = findViewById(R.id.btnUpdate);

        _givenName = findViewById(R.id.edtUpdateName);
        _givenSurname = findViewById(R.id.edtUpdateSurname);
        _givenFatherName = findViewById(R.id.edtUpdateFather);
        _givenAge = findViewById(R.id.edtUpdateAge);
        _givenGender = findViewById(R.id.spin_genderUpdate);
        _givenEducation = findViewById(R.id.spin_educationUpdate);
        _givenOperator = findViewById(R.id.spin_operatorUpdate);
        _givenNumber = findViewById(R.id.edtUpdateNumber);

        txtClickToUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollInfo.setVisibility(View.GONE);
                deleteCustomer.setVisibility(View.GONE);
                txtClickToUpdate.setVisibility(View.GONE);
                scrollUpdate.setVisibility(View.VISIBLE);
                updateCustomer.setVisibility(View.VISIBLE);
            }
        });


        Intent intent = getIntent();
        int id = intent.getIntExtra("user", -1);

        modelGelen = db.getCustomer(id);

        rname.setText(modelGelen.getName());
        rsurname.setText(modelGelen.getSurname());
        rfathername.setText(modelGelen.getFather());
        rage.setText(String.valueOf(modelGelen.getAge()));
        rgender.setText(modelGelen.getGender());
        reducation.setText(modelGelen.getEducation());
        rphone.setText("(" + modelGelen.getOperator() + ") " + modelGelen.getNumber());
        deleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteCustomer(modelGelen);
                setResult(RESULT_OK);
                finish();
            }
        });

        updateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!_givenName.getText().toString().equals("") &&
                        !_givenSurname.getText().toString().equals("") &&
                        !_givenFatherName.getText().toString().equals("") &&
                        !_givenAge.getText().toString().equals("") &&
                        !_givenNumber.getText().toString().equals("")){
                    modelGelen.setName(_givenName.getText().toString());
                    modelGelen.setSurname(_givenSurname.getText().toString());
                    modelGelen.setFather(_givenFatherName.getText().toString());
                    modelGelen.setAge(Integer.parseInt(_givenAge.getText().toString()));
                    modelGelen.setGender(_givenGender.getSelectedItem().toString());
                    modelGelen.setEducation(_givenEducation.getSelectedItem().toString());
                    modelGelen.setOperator(_givenOperator.getSelectedItem().toString());
                    modelGelen.setNumber(_givenNumber.getText().toString());
                    db.updateCustomer(modelGelen);
                    setResult(RESULT_CANCELED);
                    finish();
                }
                if(_givenName.getText().toString().equals("")){
                    _givenName.setError("adı daxil edin!");
                }if(_givenSurname.getText().toString().equals("")){
                    _givenSurname.setError("soyadı daxil edin!");
                }if(_givenFatherName.getText().toString().equals("")){
                    _givenFatherName.setError("ata adını daxil edin!");
                }if(_givenAge.getText().toString().equals("")){
                    _givenAge.setError("yaşı daxil edin!");
                }if(_givenNumber.getText().toString().equals("")){
                    _givenNumber.setError("nömrəni daxil edin!");
                }
            }
        });
    }
}