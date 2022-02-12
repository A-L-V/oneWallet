package Modelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onewallet.R

class AdapterCuenta(private val mlis: List<Cuenta>, private val itemClick: ViewHolder.onClickItem): RecyclerView.Adapter<AdapterCuenta.ViewHolder>()  {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        interface onClickItem {
            fun onClick(cuenta:Cuenta);
        }

        val titulo: TextView =item.findViewById(R.id.txtCuentaTitulo);
        val cantidad: TextView = item.findViewById(R.id.txtCuentaSaldo);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista= LayoutInflater.from(parent.context).inflate(R.layout.component_cuenta,parent,false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cuenta:Cuenta= mlis[position]
        holder.titulo.setText(cuenta.titulo +"\n" + cuenta.moneda)
        holder.cantidad.setText(""+cuenta.cantidad)
        holder.itemView.setOnClickListener{
            itemClick.onClick(cuenta)
        }
    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}