package com.example.linkagelab.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.linkagelab.databinding.ActivitySearchviewBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchviewBinding
    lateinit var adapter : NicknameAdapter

    val linkageLabKrew = mutableListOf("올리","하퍼","미아","이든","칸","아마라","레스더","웨인","리암","웬디","조셉","카일라","맥스","폴라","델리",
        "로웰","마리","월드","엘리자","헤이즐","제임스","제니","카이","드웨이","민디","찰리","콘라드","히로","제이콥","제니퍼","카오","키미","라일라")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initListener()

    }

    fun initAdapter() {
        adapter = NicknameAdapter()
        adapter.setDate(linkageLabKrew)

        binding.krewLiat.adapter = adapter

    }

    fun initListener() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.searchBar.setOnClickListener {
            adapter.setDate(mutableListOf())
            adapter.searchKeyword = ""
        }

        binding.searchBar.setOnCloseListener {
            // SearchView가 닫힐 때
            adapter.setDate(linkageLabKrew)
            adapter.searchKeyword = ""
            false
        }

        binding.searchBar.setOnQueryTextFocusChangeListener { view, b ->
            if(b) { // 포커스 됨
                if(binding.searchBar.query == "") {
                    adapter.setDate(mutableListOf())
                    adapter.searchKeyword = ""
                }

            } else {
                if(binding.searchBar.query == "") {
                    adapter.setDate(linkageLabKrew)
                    adapter.searchKeyword = ""
                }
            }
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 포커스 닫히도록
                binding.searchBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText == "") {
                    adapter.setDate(mutableListOf())
                    adapter.searchKeyword = ""
                } else {
                    val search_list = linkageLabKrew.filter { it.contains(newText!!) }

                    if(search_list != null) {
                        // 몇개의 결과중 몇개의 결과가 있습니다.
                        adapter.setDate(search_list.toMutableList())
                        adapter.searchKeyword = newText!!

                        binding.searchBar.announceForAccessibility("${search_list.size}개의 결과 제안")
                    }
                }

                return false
            }
        })
    }
}