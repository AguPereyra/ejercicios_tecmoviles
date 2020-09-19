package org.example.material

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class CattleCardRecyclerViewAdapter(private val cattleList: List<Cattle>) : RecyclerView.Adapter<CattleCardRecyclerViewAdapter.CattleCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CattleCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.cattle_card, parent, false)
        return CattleCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CattleCardViewHolder, position: Int) {
        if (position < cattleList.size) {
            val bovine = cattleList[position]
            holder.cattleCaravan.text = bovine.caravan
            holder.cattleWeight.text = bovine.weight.toString()
        }
    }

    override fun getItemCount() = cattleList.size

    class CattleCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cattleCaravan = itemView.findViewById<TextView>(R.id.cattle_title)
        val cattleWeight = itemView.findViewById<TextView>(R.id.cattle_description)
    }
}