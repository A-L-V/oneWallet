package com.example.onewallet

import Modelo.AdapterMovimiento
import Modelo.Cuenta
import Modelo.DataHelper
import Modelo.Movimiento
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_movimiento.*

class movimiento : AppCompatActivity(), AdapterMovimiento.ViewHolder.onClickItem {
    lateinit var recicleMovimientos: RecyclerView
    var cuenta = Cuenta();

    var data: DataHelper?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movimiento)

        cuenta=intent.getSerializableExtra("dato") as Cuenta

        recicleMovimientos= findViewById(R.id.recicleMovimientos)
        recicleMovimientos.layoutManager= LinearLayoutManager(this)

        data= DataHelper(this)


        val dp=AdapterMovimiento(data!!.listaMovimiento(cuenta.id),this,this);
        recicleMovimientos.adapter = dp
    }

    override fun onClick(mov: Movimiento) {
        val it: Intent =Intent(this, CambiaMovimiento::class.java)
        it.putExtra("dato", mov)
        it.putExtra("dato2", cuenta)
        startActivity(it);
    }

    override fun onClickDelete(id: Int) {
        val it: Intent =Intent(this,  movimiento::class.java)
        it.putExtra("dato", cuenta)
        for(mov in cuenta.lista){
            if(mov.id == id){
                cuenta.cantidad -= mov.cantidad;
            }
        }
        data!!.updateCuenta(cuenta);
        data?.deleteMovimiento(id);
        startActivity(it);
    }


    public fun irAddMovimiento(view: View) {
        val it: Intent = Intent(this, AddMovimiento::class.java)
        it.putExtra("dato", cuenta)
        startActivity(it);
    }

    fun returnToHome(view: View) {
        val it: Intent = Intent(this, MainActivity::class.java)
        startActivity(it);
    }


}