package com.example.homemade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper my_Data_Base;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void connecter(View view) {

        editTextUsername = findViewById(R.id.id_edit_user);

        editTextPassword = findViewById(R.id.id_edit_password);

        if(editTextUsername.getText().toString().matches("")|| editTextPassword.getText().toString().matches(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

            builder.setMessage("les champs doit être non vide").setPositiveButton("OK", null);

            AlertDialog alert = builder.create();

            alert.show();


        }

        else {

            Intent intent = new Intent(LoginActivity.this, AjouterProduitActivity.class);


            my_Data_Base = new DatabaseHelper(this);

           if (my_Data_Base.Connection(editTextUsername.getText().toString(),editTextPassword.getText().toString())==true) {

                //Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);

                startActivity(intent);

                finish();
            }

            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                builder.setMessage("user or mot passe erronée").setPositiveButton("OK", null);

                AlertDialog alert = builder.create();

                alert.show();
            }
        }
    }

}
