package com.example.eldohubdeliveries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EldoRVAdapter(var list : List<Eldoitems>,
                    val eldoItemClickInterface: EldoItemClickInterface
                    ):RecyclerView.Adapter<EldoRVAdapter.EldoViewHolder>(){

    inner class EldoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV= itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)
    }

    interface EldoItemClickInterface{
        fun onItemClick(eldoitems: Eldoitems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EldoViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.eldo_rv_item,parent, false)
        return EldoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EldoViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = list.get(position).itemPrice.toString()
        val itemTotal : Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "Rs. "+ itemTotal.toString()
        holder.deleteTV.setOnClickListener{
            eldoItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}