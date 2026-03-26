package com.example.mediafeed

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
        TODO("Return the list size")
    }

    // =======================================================================
    // TODO 2b: Implement onCreateViewHolder()
    // Hint: Use LayoutInflater to inflate R.layout.item_comment, then return VH
    // =======================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("Inflate item_comment layout and return a VH instance")
    }

    // =======================================================================
    // TODO 2c: Implement onBindViewHolder()
    // Hint: Call holder.bind(items[position])
    // =======================================================================
    override fun onBindViewHolder(holder: VH, position: Int) {
        TODO("Call the holder's bind method with the data at the given position")
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv = itemView.findViewById<TextView>(R.id.tvComment)

        // ===================================================================
        // TODO 2d: Implement bind()
        // Hint: Set the given text to the tv (TextView)
        // ===================================================================
        fun bind(text: String) {
            TODO("Display the text on the TextView")
        }
    }
}