package com.example.yusub_x.finalwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class registration extends AppCompatActivity {
    EditText _givenName,_givenSurname,_givenFatherName,_givenAge;
    Spinner _givenGender,_givenEducation,_givenOperator;
    EditText _givenNumber;
    Button _button_register;
    database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        _givenName = findViewById(R.id.edtName);
        _givenSurname = findViewById(R.id.edtSurname);
        _button_register = findViewById(R.id.btnRegister);
        _givenFatherName = findViewById(R.id.edtFather);
        _givenAge = findViewById(R.id.edtAge);
        _givenGender = findViewById(R.id.spin_gender);
        _givenEducation = findViewById(R.id.spin_education);
        _givenOperator = findViewById(R.id.spin_operator);
        _givenNumber = findViewById(R.id.edtNumber);
        db = new database(this);
        _button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!_givenName.getText().toString().equals("") &&
                        !_givenSurname.getText().toString().equals("") &&
                        !_givenFatherName.getText().toString().equals("") &&
                        !_givenAge.getText().toString().equals("") &&
                        !_givenNumber.getText().toString().equals("")){
                    model model = new model();
                    model.setName(_givenName.getText().toString());
                    model.setSurname(_givenSurname.getText().toString());
                    model.setFather(_givenFatherName.getText().toString());
                    model.setAge(Integer.parseInt(_givenAge.getText().toString()));
                    model.setGender(_givenGender.getSelectedItem().toString());
                    model.setEducation(_givenEducation.getSelectedItem().toString());
                    model.setOperator(_givenOperator.getSelectedItem().toString());
                    model.setNumber(_givenNumber.getText().toString());
                 db.addCustomer(model);
                 setResult(RESULT_OK);
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
