package com.example.onewallet.ui.home

import Modelo.AdapterCuenta
import Modelo.Cuenta
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onewallet.ActivityMovimientos
import com.example.onewallet.MyGlobal
import com.example.onewallet.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), AdapterCuenta.ViewHolder.onClickItem{

    private var _binding: FragmentHomeBinding? = null

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

        val adp= AdapterCuenta(MyGlobal.listaPlato,this);
        binding.recicleCuenta.adapter=adp

        if(MyGlobal.listaPlato.isEmpty()){
            MyGlobal.inicializar()
        }
        var saldo=0.0
        for(cuenta in MyGlobal.listaPlato ){
            saldo+=cuenta.cantidad;
        }
        saldo = Math.round(saldo * 100.0) / 100.0
        binding.txtSaldo.setText("S/. "+ saldo);
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(cuenta: Cuenta) {
        val it: Intent =Intent(requireContext(), ActivityMovimientos::class.java)
        it.putExtra("dato", cuenta.lista)
        startActivity(it);
    }
}