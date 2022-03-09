package com.example.onewallet

import Modelo.Cuenta
import Modelo.DataHelper
import Modelo.Movimiento
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movimiento.*

class AddMovimiento : AppCompatActivity(),View.OnClickListener {
    var data: DataHelper?=null
    var cuenta = Cuenta();
    var mov = Movimiento()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movimiento)
        data= DataHelper(this)
        cuenta=intent.getSerializableExtra("dato") as Cuenta

        val tipo = getResources().getStringArray(R.array.tipoMovimiento)
        val adaptador =ArrayAdapter<String>(this,android.R.layout.simple_spinner_item)
        for(i in tipo){
            adaptador.add(i);
        }
        spinnerMovTipo.adapter=adaptador

        val btn: Button = findViewById(R.id.buttonAddMovimiento)
        btn.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        var mov = Movimiento();
        mov.titulo = txtMovTitulo.text.toString()
        mov.tipo = spinnerMovTipo.selectedItem.toString()
        mov.nota = txtMovNota.text.toString()
        mov.cantidad=txtMovCantidad.text.toString().toDouble()
        mov.cuenta=cuenta
        mov.updateTotal()
        val res= data?.insertarMovimiento(mov)
        Toast.makeText(this,"grabo "+res,Toast.LENGTH_LONG).show()
    }

    fun irAddMovimiento(view: View) {
        val it: Intent = Intent(this, movimiento::class.java)
        it.putExtra("dato", cuenta)
        startActivity(it);
    }

}