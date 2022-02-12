package Modelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onewallet.R


class AdapterMovimiento(private val mlis: List<Movimiento>, private val itemClick: ViewHolder.onClickItem):RecyclerView.Adapter<AdapterMovimiento.ViewHolder>()  {

    class ViewHolder(item:View):RecyclerView.ViewHolder(item){
        val descr: TextView =item.findViewById(R.id.txtMovDesc);
        val estado: TextView = item.findViewById(R.id.txtMovEstado);

        interface onClickItem{
            fun onClick(mov:Movimiento);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista= LayoutInflater.from(parent.context).inflate(R.layout.component_movimiento,parent,false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mov:Movimiento= mlis[position]
        holder.descr.setText(mov.titulo + "\n" + mov.tipo + "\n" + mov.nota)
        holder.estado.setText("" +mov.cantidad + "\n" + mov.total)
        holder.itemView.setOnClickListener{
            itemClick.onClick(mov)
        }

    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}