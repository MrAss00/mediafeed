package com.example.mediafeed

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class ImageFeedActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var store: LocalStore

    private var isGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_feed)

        store = LocalStore(this)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler = findViewById(R.id.recycler)

        // ===================================================================
        // TODO 3a: Create an ImageAdapter and attach it to the RecyclerView
        // Hint:
        //   1. Create an ImageAdapter instance with:
        //      - store = store
        //      - onOpenComments = { post -> CommentActivity.start(...) }
        //      - onGridItemClick = { position -> setGridMode(false); recycler.scrollToPosition(position) }
        //   2. Set the adapter to recycler.adapter
        //   3. Call setGridMode(false) to set the initial mode to Feed
        // ===================================================================
        adapter = ImageAdapter(store, { post -> CommentActivity.start(this,MediaType.IMAGE, post.id, post.name) },{ position -> setGridMode(false); recycler.scrollToPosition(position) })
        recycler.adapter = adapter
        setGridMode(false)

    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    // =======================================================================
    // TODO 3b: Implement setGridMode() — LayoutManager switching
    // Hint:
    //   1. Update isGridMode = grid
    //   2. Set recycler.layoutManager:
    //      - grid=true  -> GridLayoutManager(this, 3)  (3-column grid)
    //      - grid=false -> LinearLayoutManager(this)    (vertical list)
    //   3. Sync adapter.isGridMode = grid
    //   4. Call adapter.notifyDataSetChanged() to refresh the list
    //   5. Call invalidateOptionsMenu() to update the menu icon
    // =======================================================================
    private fun setGridMode(grid: Boolean) {
        isGridMode = grid
        recycler.layoutManager = if(isGridMode){
            GridLayoutManager(this, 3)
        }
        else{
            LinearLayoutManager(this)
        }
        adapter.isGridMode = grid
        adapter.notifyDataSetChanged()
        invalidateOptionsMenu()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_image, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val item = menu.findItem(R.id.action_toggle_layout)
        item.title = if (isGridMode) "Back" else "Grid"
        item.setIcon(if (isGridMode) R.drawable.ic_arrow_back else R.drawable.ic_grid_view)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { finish(); true }
            R.id.action_toggle_layout -> { setGridMode(!isGridMode); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}