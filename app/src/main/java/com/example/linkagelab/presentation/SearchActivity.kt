package com.example.linkagelab.presentation

import android.os.Bundle
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

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    // 검색 결과와 리스트 비교해서 일치하는게 있는지 확인

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    // 입력된 값이 있는 경우
                    val search_list = linkageLabKrew.filter { it.contains(newText) }

                    if(search_list != null) {

                        // 몇개의 결과중 몇개의 결과가 있습니다.
                        adapter.setDate(search_list.toMutableList())
                        adapter.searchKeyword = newText

                        binding.searchBar.announceForAccessibility("${linkageLabKrew.size}개의 항목 중 ${search_list.size}개의 결과가 검색되었습니다.")
                    } else {

                        // 검색 결과가 없습니다
                        binding.searchBar.announceForAccessibility("검색 결과가 없습니다.")
                    }

                } ?: run {
                    // 입력된 값 없는 경우
                    adapter.setDate(linkageLabKrew)
                    adapter.searchKeyword = ""
                }
                return false
            }
        })


    }
}