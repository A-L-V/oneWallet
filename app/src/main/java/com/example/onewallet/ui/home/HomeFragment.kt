package com.example.onewallet.ui.home

import Modelo.AdapterCuenta
import Modelo.Cuenta
import Modelo.DataHelper
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onewallet.*
import com.example.onewallet.databinding.FragmentHomeBinding
import org.json.JSONException


class HomeFragment : Fragment(), AdapterCuenta.ViewHolder.onClickItem ,View.OnClickListener {
    var data: DataHelper?=null

    private var _binding: FragmentHomeBinding? = null

    var cola: RequestQueue?=null
    var ruta = "http:// mi ip /..."
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.recicleCuenta.layoutManager= LinearLayoutManager(activity)
        binding.recicleCuenta.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        data= DataHelper(requireContext())
        data!!.listCuenta();

        val adp = AdapterCuenta(MyGlobal.listaCuenta,this);
        binding.recicleCuenta.adapter=adp

        generateSaldo()
        cola= Volley.newRequestQueue( requireContext());
        return root
    }

    fun generateSaldo(){
        var saldo=0.0

        if(!(MyGlobal.listaCuenta.isEmpty())){
            for(cuenta in MyGlobal.listaCuenta ){
                saldo+=cuenta.cantidad;
            }
        }

        saldo = Math.round(saldo * 100.0) / 100.0
        binding.txtSaldo.setText("S/. "+ saldo);
    };


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onClickCuenta(cuenta: Cuenta) {
        val it: Intent =Intent(requireContext(), movimiento::class.java)
        it.putExtra("dato", cuenta)
        startActivity(it);
    }

    override fun onClickModfificar(cuenta: Cuenta) {
        val it: Intent =Intent(requireContext(), CambiaCuenta::class.java)
        it.putExtra("dato", cuenta)
        startActivity(it);
    }

    override fun onClickDelete(id: Int) {
        val it: Intent =Intent(requireContext(),  MainActivity::class.java)
        data?.deleteCuenta(id);
        startActivity(it);

    }

    override fun onClick(p0: View?) {
        val it: Intent =Intent(requireContext(),  AddMovimiento::class.java)
        startActivity(it);
    }



}