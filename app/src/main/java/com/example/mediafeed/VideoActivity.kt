package com.example.mediafeed

import android.os.Bundle
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class VideoActivity : AppCompatActivity() {

    private lateinit var store: LocalStore
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: VideoPagerAdapter

    private lateinit var player: ExoPlayer
    private var currentPos = 0
    private var currentHolder: VideoPagerAdapter.VideoVH? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        store = LocalStore(this)

        // ===================================================================
        // TODO 4a: Initialize ExoPlayer
        // Hint:
        //   - Use ExoPlayer.Builder(this).build()
        //   - Set repeatMode = Player.REPEAT_MODE_ONE (loop current video)
        //   - Set playWhenReady = true (auto-play when ready)
        // ===================================================================

        player = ExoPlayer.Builder(this).build()
        player.repeatMode = Player.REPEAT_MODE_ONE
        player.playWhenReady = true


        viewPager = findViewById(R.id.viewPager)
        // ===================================================================
        // TODO 4b: Set ViewPager2 to vertical orientation
        // Hint: viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        // ===================================================================
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        adapter = VideoPagerAdapter(
            store = store,
            onOpenComments = { post ->
                CommentActivity.start(this, MediaType.VIDEO, post.id, post.name)
            }
        )
        viewPager.adapter = adapter

        // ===================================================================
        // TODO 4c: Register a page change callback for auto-play
        // Hint:
        //   1. Call viewPager.registerOnPageChangeCallback(...)
        //   2. Create an anonymous ViewPager2.OnPageChangeCallback object
        //   3. Override onPageSelected(position):
        //      - Update currentPos = position
        //      - Call viewPager.post { playAt(position) }
        //   4. Also play the first page: viewPager.post { playAt(0) }
        // ===================================================================
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int){
                currentPos = position
                viewPager.post{playAt(position)}
            }
        })

        viewPager.post { playAt(0) }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        viewPager.post { playAt(currentPos) }
    }

    // =======================================================================
    // TODO 4d: Implement playAt() — play video at the given page
    // Hint:
    //   1. Get the RecyclerView: val rv = viewPager.getChildAt(0) as? RecyclerView ?: return
    //   2. Get the ViewHolder: rv.findViewHolderForAdapterPosition(position) as? VideoPagerAdapter.VideoVH
    //   3. Detach the old holder: currentHolder?.playerView?.player = null
    //   4. Attach to new holder: holder.playerView.player = player
    //   5. Update currentHolder = holder
    //   6. Build URI: RawResourceDataSource.buildRawResourceUri(post.rawRes)
    //   7. Set and play: player.setMediaItem(...) -> player.prepare() -> player.play()
    // =======================================================================

    @OptIn(UnstableApi::class)
    private fun playAt(position: Int) {
        val rv = viewPager.getChildAt(0) as? RecyclerView ?: return
        val holder = rv.findViewHolderForAdapterPosition(position) as? VideoPagerAdapter.VideoVH
        currentHolder?.playerView?.player = null
        holder?.playerView?.player = player
        currentHolder = holder
        val post = adapter.items[position]

        val uri = RawResourceDataSource.buildRawResourceUri(post.rawRes)
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}