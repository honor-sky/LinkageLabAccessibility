package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    fun initListener() {

        binding.contextMenu.setOnClickListener {

           // val context_menu = ContextMenu()

        }

        binding.popupMenu.setOnClickListener {

        }
    }


 /*   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_exit) {
            finish()
        } else {
            Toast.makeText(this, "Hello Menu, ${item.title}", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }*/


}