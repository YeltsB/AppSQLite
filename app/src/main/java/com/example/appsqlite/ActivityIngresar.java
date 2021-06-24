package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appsqlite.transacciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {
    EditText nombres,apellidos,edad,correo,direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        Button btn = (Button)findViewById(R.id.button);
        nombres = (EditText)findViewById(R.id.txtNombres);
        apellidos = (EditText)findViewById(R.id.txtApellidos);
        edad = (EditText)findViewById(R.id.txtEdad);
        correo = (EditText)findViewById(R.id.txtCorreo);
        direccion = (EditText)findViewById(R.id.txtDireccion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();

            }
        });
    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion( this, Transacciones.NameDateBase, null, 1 );
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas,Transacciones.id, valores);
        //Toast
        Toast.makeText(getApplicationContext(), "Registro ingresado :" + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        ClearScreen();
    }

    private void ClearScreen(){
        nombres.setText("");
        apellidos.setText("");
        correo.setText("");
        direccion.setText("");
        edad.setText("");
    }
}