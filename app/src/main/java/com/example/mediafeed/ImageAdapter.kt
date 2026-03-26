package com.example.mediafeed

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Grid
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(
    private val store: LocalStore,
    private val onOpenComments: (ImagePost) -> Unit,
    private val onGridItemClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isGridMode: Boolean = false
    private val items = SampleData.images

    // =======================================================================
    // TODO 1a: Implement getItemCount()
    // Hint: Return the size of the items list
    // =======================================================================
    override fun getItemCount(): Int {
        return items.size;
    }

    // =======================================================================
    // TODO 1b: Implement getItemViewType()
    // Hint: Return 1 if in Grid mode, 0 otherwise.
    //       RecyclerView uses this value to decide which ViewHolder to use.
    // =======================================================================
    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) 1 else 0;
    }

    // =======================================================================
    // TODO 1c: Implement onCreateViewHolder()
    // Hint:
    //   - Use LayoutInflater.from(parent.context) to get the inflater
    //   - If viewType == 1, inflate R.layout.item_image_grid -> return GridVH(v)
    //   - Otherwise, inflate R.layout.item_image_feed -> return FeedVH(v)
    //   - When calling inflate(), pass parent as the 2nd arg and false as the 3rd
    // =======================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if(viewType==1){
            val v = inflater.inflate(R.layout.item_image_grid,parent,false);
            return GridVH(v);
        }
        val v = inflater.inflate(R.layout.item_image_feed,parent, false);
        return FeedVH(v);
    }

    // =======================================================================
    // TODO 1d: Implement onBindViewHolder()
    // Hint:
    //   - Get the current data from items[position]
    //   - Check the holder type: if GridVH, call holder.bind(post, position)
    //   - If FeedVH, call holder.bind(post)
    // =======================================================================
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if(holder is GridVH){
            holder.bind(item, position)
        }
        else if(holder is FeedVH){
            holder.bind(item)
        }
        TODO("Get the data at the given position and call the holder's bind method")
    }

    inner class GridVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv = itemView.findViewById<ImageView>(R.id.ivThumb)
        fun bind(post: ImagePost, position: Int) {
            iv.setImageResource(post.drawableRes)
            itemView.setOnClickListener { onGridItemClick(position) }
        }
    }

    inner class FeedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv = itemView.findViewById<ImageView>(R.id.ivPhoto)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val btnLike = itemView.findViewById<ImageButton>(R.id.btnLike)
        private val tvLike = itemView.findViewById<TextView>(R.id.tvLikeCount)
        private val btnComment = itemView.findViewById<ImageButton>(R.id.btnComment)
        private val tvComment = itemView.findViewById<TextView>(R.id.tvCommentCount)

        // ===================================================================
        // TODO 1e: Implement FeedVH's bind() method
        // Hint:
        //   1. Set the image: iv.setImageResource(post.drawableRes)
        //   2. Set the name: tvName.text = post.name
        //   3. Read like state from store: store.isLiked(MediaType.IMAGE, post.id)
        //      - Update tvLike text ("1" or "0") based on the state
        //      - Update btnLike color (Color.RED or Color.DKGRAY) based on the state
        //   4. Show comment count: store.commentsCount(MediaType.IMAGE, post.id)
        //   5. On btnLike click: call store.toggleLike() then notifyItemChanged
        //   6. On btnComment click: call onOpenComments(post)
        // ===================================================================
        fun bind(post: ImagePost) {
            iv.setImageResource(post.drawableRes)
            tvName.text = post.name
            if(store.isLiked(MediaType.IMAGE, post.id)) {
                tvLike.text ="1"
                btnLike.setColorFilter(Color.RED)
            }
            else{
                tvLike.text= "0"
                btnLike.setColorFilter(Color.DKGRAY)
            }
            tvComment.text = store.commentsCount(MediaType.IMAGE, post.id).toString()
            btnLike.setOnClickListener {
                val position = bindingAdapterPosition
                // when click, like +1
                store.toggleLike(MediaType.IMAGE, post.id)
                notifyItemChanged(position)
            }

            btnComment.setOnClickListener {
                onOpenComments(post)
            }
        }
    }
}