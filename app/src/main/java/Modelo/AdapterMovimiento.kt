package Modelo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onewallet.R


class AdapterMovimiento(private val mlis: List<Movimiento>,private val contexto:Context, private val itemClick: ViewHolder.onClickItem):RecyclerView.Adapter<AdapterMovimiento.ViewHolder>()  {

    val ruta = "https://impartable-threader.000webhostapp.com/ICONS/"

    class ViewHolder(item:View):RecyclerView.ViewHolder(item){
        val descr: TextView =item.findViewById(R.id.txtMovDesc);
        val estado: TextView = item.findViewById(R.id.txtMovEstado);
        val eliminar = item.findViewById<Button>(R.id.buttonDeleteMovimiento)
        val img = item.findViewById<ImageView>(R.id.imgMov)
        interface onClickItem{
            fun onClick(mov:Movimiento);
            fun onClickDelete(id:Int);
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

        Glide.with(contexto)
            .load(ruta+mov.tipo+".png")
            .error(R.drawable.ic_launcher_background)
            .into(holder.img);

        holder.itemView.setOnClickListener{
            itemClick.onClick(mov)
        }
        holder.eliminar.setOnClickListener{
            itemClick.onClickDelete(mov.id)
        }
    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}