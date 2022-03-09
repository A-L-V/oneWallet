package com.example.onewallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_graficos.*


class graficos : Fragment() ,View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View=inflater.inflate(R.layout.fragment_graficos, container, false)
        val btn: Button =v.findViewById(R.id.button2)
        btn.setOnClickListener(this)
        return v

    }

    override fun onClick(p0: View?) {
        //graficar
        var datos: ArrayList<Entry> = ArrayList<Entry>();
        var i:Int = 0;
        val vec:ArrayList<String> = ArrayList<String>();
        for(cuenta in MyGlobal.listaCuenta){
            datos.add(Entry(cuenta.cantidad.toFloat(), i))
            vec.add(cuenta.titulo);
            i++;
        }
        val ds =  PieDataSet(datos,"Sueldo");
        val graf = PieData(vec,ds)
        graficoTorta.animateX(1500);
        ds.setColors(ColorTemplate.COLORFUL_COLORS)
        graficoTorta?.setData(graf);
    }
}