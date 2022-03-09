package com.example.onewallet

import Modelo.Cuenta
import Modelo.DataHelper
import Modelo.Movimiento
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movimiento.*
import kotlinx.android.synthetic.main.activity_cambia_movimiento.*

class CambiaMovimiento : AppCompatActivity() {

    var data: DataHelper?=null
    var mov = Movimiento();
    var cuenta = Cuenta();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambia_movimiento)
        data= DataHelper(this)
        mov = intent.getSerializableExtra("dato") as Movimiento
        cuenta = intent.getSerializableExtra("dato2") as Cuenta

        txtMovCrudTitulo.setText(mov.titulo)

        val tipo: Array<out String> = getResources().getStringArray(R.array.tipoMovimiento)
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item)
        var indice:Int = -1;
        for(i in tipo){
            adaptador.add(i);
        }
        spinnerMovCurdTipo.adapter=adaptador
        val total = tipo.size-1;
        for(palabra in 0..total){
            if(mov.tipo==spinnerMovCurdTipo.getItemAtPosition(palabra)){
                spinnerMovCurdTipo.setSelection(palabra)
            }
        }

        txtMovCrudNota.setText(""+indice);
        txtMovCrudNota.setText(mov.nota);
        txtMovCrudCantidad.setText(""+mov.cantidad);
    }

     fun onClick(p0: View?) {
        mov.titulo = txtMovCrudTitulo.text.toString()
        mov.tipo = spinnerMovCurdTipo.selectedItem.toString()
        mov.nota = txtMovCrudNota.text.toString()
        mov.cantidad=txtMovCrudCantidad.text.toString().toDouble()
        mov.cuenta=cuenta
        mov.updateTotal()
        val res= data?.updateMovimiento(mov)

        Toast.makeText(this,"Datos actualizados "+res, Toast.LENGTH_LONG).show()
    }

    public fun retorna(v:View){
        val it: Intent = Intent(this, movimiento::class.java)
        it.putExtra("dato", cuenta)
        startActivity(it)
    }
}