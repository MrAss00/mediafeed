package com.example.mediafeed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommentActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_TYPE = "type"
        private const val EXTRA_ID = "id"
        private const val EXTRA_TITLE = "title"

        fun start(context: Context, type: MediaType, id: String, title: String) {
            val i = Intent(context, CommentActivity::class.java)
            i.putExtra(EXTRA_TYPE, type.name)
            i.putExtra(EXTRA_ID, id)
            i.putExtra(EXTRA_TITLE, title)
            context.startActivity(i)
        }
    }

    private lateinit var store: LocalStore
    private lateinit var adapter: CommentAdapter

    private lateinit var type: MediaType
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        store = LocalStore(this)

        type = MediaType.valueOf(intent.getStringExtra(EXTRA_TYPE)!!)
        id = intent.getStringExtra(EXTRA_ID)!!
        title = intent.getStringExtra(EXTRA_TITLE) ?: "Comments"

        val rv = findViewById<RecyclerView>(R.id.rvComments)
        rv.layoutManager = LinearLayoutManager(this)

        adapter = CommentAdapter(store.getComments(type, id))
        rv.adapter = adapter

        val et = findViewById<EditText>(R.id.etComment)
        findViewById<Button>(R.id.btnSend).setOnClickListener {
            val text = et.text.toString().trim()
            if (text.isNotEmpty()) {
                store.addComment(type, id, text)
                et.setText("")
                adapter.setData(store.getComments(type, id))
                rv.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}