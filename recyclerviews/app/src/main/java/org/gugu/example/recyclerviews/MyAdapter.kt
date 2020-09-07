package org.gugu.example.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataset: List<Vacas>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val itemView : CardView) : RecyclerView.ViewHolder(itemView) {
        internal var caravana : TextView = itemView.findViewById<TextView>(R.id.caravana)
        internal var peso : TextView = itemView.findViewById<TextView>(R.id.peso)

    }

    // Invoked by layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyAdapter.MyViewHolder {
        // Create a new view
        val cardView = LayoutInflater.from(parent.context)
                                    .inflate(R.layout.text_holder, parent, false) as CardView
        return MyViewHolder(cardView)
    }

    // Change content of views
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.caravana.text = myDataset[position].caravana
        holder.peso.text = myDataset[position].peso.toString()
    }

    override fun getItemCount() = myDataset.size
}