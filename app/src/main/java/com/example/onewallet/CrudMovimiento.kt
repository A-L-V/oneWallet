package com.example.onewallet

import Modelo.Movimiento
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_crud_movimiento.*
import java.util.Arrays.asList

class CrudMovimiento : AppCompatActivity() {
    var mov:Movimiento = Movimiento()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_movimiento)
        mov = intent.getSerializableExtra("dato") as Movimiento
        txtMovTitulo.setText(mov.titulo)
        txtMovTipo.setText(mov.tipo)
        txtMovNota.setText(mov.nota)
        txtMovCantidad.setText("" + mov.cantidad)

        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item)
        for(cuenta in MyGlobal.listaPlato){
            adaptador.add(cuenta.titulo)
        }

        spinnerCuenta.adapter=adaptador
    }
}
