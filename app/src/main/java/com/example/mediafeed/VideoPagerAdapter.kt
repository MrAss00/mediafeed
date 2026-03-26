package com.example.mediafeed

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView

class VideoPagerAdapter(
    private val store: LocalStore,
    private val onOpenComments: (VideoPost) -> Unit
) : RecyclerView.Adapter<VideoPagerAdapter.VideoVH>() {

    val items: List<VideoPost> = SampleData.videos

    // =======================================================================
    // TODO 5a: Implement getItemCount()
    // Hint: Return the size of the items list
    // =======================================================================
    override fun getItemCount(): Int {
        return items.size

    }

    // =======================================================================
    // TODO 5b: Implement onCreateViewHolder()
    // Hint: Inflate R.layout.item_video_page and return a VideoVH
    // =======================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_video_page, parent, false)
        return VideoVH(v)
    }

    // =======================================================================
    // TODO 5c: Implement onBindViewHolder()
    // Hint: Call holder.bind(items[position])
    // =======================================================================
    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        holder.bind(items[position])
    }

    inner class VideoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerView: PlayerView = itemView.findViewById(R.id.playerView)
        private val btnLike: ImageButton = itemView.findViewById(R.id.btnLike)
        private val tvLike: TextView = itemView.findViewById(R.id.tvLikeCount)
        private val btnComment: ImageButton = itemView.findViewById(R.id.btnComment)
        private val tvComment: TextView = itemView.findViewById(R.id.tvCommentCount)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvVideoTitle)

        // ===================================================================
        // TODO 5d: Implement VideoVH's bind() method
        // Hint:
        //   1. Set video title: tvTitle.text = post.name
        //   2. Read like state from store and update UI:
        //      - tvLike shows "1" or "0"
        //      - btnLike color: Color.RED (liked) or Color.WHITE (not liked)
        //   3. Show comment count: store.commentsCount(MediaType.VIDEO, post.id)
        //   4. WARNING: Do NOT call notifyItemChanged on btnLike click!
        //      In ViewPager2, calling notifyItemChanged will cause the video
        //      to reload. Instead, update the view directly:
        //      tvLike.text = ...; btnLike.setColorFilter(...)
        //   5. On btnComment click: call onOpenComments(post)
        // ===================================================================
        fun bind(post: VideoPost) {
            tvTitle.text = post.name

            val liked = store.isLiked(MediaType.VIDEO, post.id)
            tvLike.text = if(liked) "1" else "0"
            btnLike.setColorFilter(if(liked) Color.RED else Color.WHITE)

            tvComment.text = store.commentsCount(MediaType.VIDEO, post.id).toString()

            btnLike.setOnClickListener {
                val LikedNow = store.toggleLike(MediaType.VIDEO,post.id)
                tvLike.text = if(LikedNow) "1" else "0"
                btnLike.setColorFilter(if(LikedNow) Color.RED else Color.WHITE)

            }

            btnComment.setOnClickListener {
                onOpenComments(post)
            }

            TODO("Set title, like state, comment count. Note the special click handling for ViewPager2")
        }
    }
}