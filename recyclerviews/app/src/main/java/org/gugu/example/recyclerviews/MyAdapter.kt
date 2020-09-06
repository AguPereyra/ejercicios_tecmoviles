package org.gugu.example.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataset: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val textView : TextView) : RecyclerView.ViewHolder(textView) {
    }

    // Invoked by layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyAdapter.MyViewHolder {
        // Create a new view
        val textView = LayoutInflater.from(parent.context)
                                    .inflate(R.layout.text_holder, parent, false) as TextView
        return MyViewHolder(textView)
    }

    // Change content of views
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}