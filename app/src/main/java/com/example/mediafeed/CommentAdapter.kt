package com.example.mediafeed

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(private var items: List<String>) :
    RecyclerView.Adapter<CommentAdapter.VH>() {

    fun setData(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    // =======================================================================
    // TODO 2a: Implement getItemCount()
    // Hint: Return the size of the items list
    // =======================================================================
    override fun getItemCount(): Int {
        return items.size
    }

    // =======================================================================
    // TODO 2b: Implement onCreateViewHolder()
    // Hint: Use LayoutInflater to inflate R.layout.item_comment, then return VH
    // =======================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return VH(view)
    }

    // =======================================================================
    // TODO 2c: Implement onBindViewHolder()
    // Hint: Call holder.bind(items[position])
    // =======================================================================
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv = itemView.findViewById<TextView>(R.id.tvComment)

        // ===================================================================
        // TODO 2d: Implement bind()
        // Hint: Set the given text to the tv (TextView)
        // ===================================================================
        fun bind(text: String) {
            tv.text = "IDK what this has to be set"
        }
    }
}