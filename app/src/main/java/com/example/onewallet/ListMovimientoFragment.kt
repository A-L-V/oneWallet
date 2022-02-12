package com.example.onewallet

import Modelo.AdapterMovimiento
import Modelo.Movimiento
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onewallet.databinding.FragmentListMovimientosBinding
import com.example.onewallet.ui.home.HomeFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListMovimientoFragment : Fragment(), AdapterMovimiento.ViewHolder.onClickItem {
    var listaMov:ArrayList<Movimiento> = ArrayList()


    lateinit var txtTitulo:TextView

    private var _binding: FragmentListMovimientosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity: ActivityMovimientos = activity as ActivityMovimientos
        listaMov = activity.getMyData();

        _binding = FragmentListMovimientosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.recicleMovimientos.layoutManager=LinearLayoutManager(activity)
        val adp= AdapterMovimiento( listaMov,this);
        binding.recicleMovimientos.adapter=adp



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public fun returnToHome(){
        val it: Intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(it);
    }

    override fun onClick(mov: Movimiento) {
        val it: Intent =Intent(requireContext(), CrudMovimiento::class.java)
        it.putExtra("dato", mov)
        startActivity(it);
    }
}