package br.unitins.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void nextScreen(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        Button button = (Button) findViewById(R.id.buttonFirst);
        int buttonValue = Integer.parseInt(button.getText().toString());
        int increaseValue = buttonValue + 1;
        intent.putExtra("New", increaseValue);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            Button button = (Button) findViewById(R.id.buttonSecond);
            button.setText(data.getSerializableExtra("Increase").toString());
        }
    }
}