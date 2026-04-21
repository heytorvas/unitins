package br.unitins.secondapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText age;
    private EditText cpf;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.etName);
        cpf = (EditText) findViewById(R.id.etCpf);
        age = (EditText) findViewById(R.id.etAge);
        email = (EditText) findViewById(R.id.etEmail);
        phone = (EditText) findViewById(R.id.etPhone);
    }
}