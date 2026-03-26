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
        TODO("Return the video list size")
    }

    // =======================================================================
    // TODO 5b: Implement onCreateViewHolder()
    // Hint: Inflate R.layout.item_video_page and return a VideoVH
    // =======================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        TODO("Inflate item_video_page layout and return a VideoVH")
    }

    // =======================================================================
    // TODO 5c: Implement onBindViewHolder()
    // Hint: Call holder.bind(items[position])
    // =======================================================================
    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        TODO("Pass the data at the given position to holder.bind")
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
            TODO("Set title, like state, comment count. Note the special click handling for ViewPager2")
        }
    }
}