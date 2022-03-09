package com.example.onewallet

import Modelo.Cuenta
import Modelo.DataHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_cuenta.*



class AddCuenta : Fragment(),View.OnClickListener  {
    var data:DataHelper?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View=inflater.inflate(R.layout.fragment_add_cuenta, container, false)
        data=DataHelper(requireContext())

        val btn: Button =v.findViewById(R.id.button4)
        btn.setOnClickListener(this)

        return v
    }



    override fun onClick(p0: View?) {
        val cuenta = Cuenta();
        cuenta.titulo=textAddCuentaTitulo.text.toString();
        cuenta.moneda=textAddCuentaMoneda.text.toString()
        cuenta.cantidad=textAddCuentaCantidad.text.toString().toDouble()
        cuenta.tipo=textAddCuentaTipo.text.toString()
        val res= data?.insertCuenta(cuenta)
        Toast.makeText(context,"datos Insertados", Toast.LENGTH_LONG).show()
    }
}