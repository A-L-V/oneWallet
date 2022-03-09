package com.example.onewallet

import Modelo.Cuenta
import Modelo.DataHelper
import Modelo.Movimiento
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cambia_cuenta.*

class CambiaCuenta : AppCompatActivity()  {

    var data: DataHelper?=null
    var cuenta = Cuenta();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambia_cuenta)
        cuenta = intent.getSerializableExtra("dato") as Cuenta
        textCrudCuentaTitulo.setText(cuenta.titulo)
        textCrudCuentaMoneda.setText(cuenta.moneda);
        textCrudCuentaCantidad.setText(""+cuenta.cantidad);
        textCrudCuentaTipo.setText(cuenta.tipo);
        data= DataHelper(this)

    }

    public fun cambia(v: View){
        cuenta.titulo=textCrudCuentaTitulo.text.toString();
        cuenta.moneda=textCrudCuentaMoneda.text.toString()
        cuenta.cantidad=textCrudCuentaCantidad.text.toString().toDouble()
        cuenta.tipo=textCrudCuentaTipo.text.toString()
        val res= data?.updateCuenta(cuenta)
        Toast.makeText(this,"datos actualizados",Toast.LENGTH_LONG).show()
    }
    public fun retorna(v:View){
        val it: Intent = Intent(this, MainActivity::class.java)
        startActivity(it)
    }
}