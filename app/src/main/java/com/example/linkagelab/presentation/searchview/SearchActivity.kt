package com.example.linkagelab.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivitySearchviewBinding
import com.example.linkagelab.presentation.searchview.RecentWordAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchviewBinding
    lateinit var adapter : NicknameAdapter

    lateinit var recentdapter : RecentWordAdapter

    val linkageLabKrew = mutableListOf("올리","하퍼","미아","이든","칸","아마라","레스더","웨인","리암","웬디","조셉","카일라","맥스","폴라","델리",
        "로웰","마리","월드","엘리자","헤이즐","제임스","제니","카이","드웨이","민디","찰리","콘라드","히로","제이콥","제니퍼","카오","키미","라일라")


    // 최근 검색어 저장할 내부저장소
    private lateinit var prefs  : SharedPreferences
    private lateinit var recentSearchedList : MutableMap<String, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = this.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

        initAdapter()
        initListener()
        initAccessibility()

    }

    fun initAdapter() {
        adapter = NicknameAdapter(
            { word ->
                //saveRecentWord(word)
            //Toast.makeText(this, "$word 선택", Toast.LENGTH_SHORT).show()
                },
        )
        adapter.setDate(linkageLabKrew)
        binding.krewList.adapter = adapter

        recentdapter = RecentWordAdapter(
            { word ->

                binding.searchEditbar.setText(word)
                searchWord(word)
                binding.searchBar.requestFocus()

            },
            { word, currentIdx ->
                removeRecentWord(word, currentIdx)
            })

        binding.recentList.adapter = recentdapter
        setRecentWord()

    }

    fun initListener() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.deleteBtn.setOnClickListener {

           binding.searchEditbar.text = null
            adapter.setDate(linkageLabKrew)
            adapter.searchKeyword = ""

            // 최근 검색어 나타남
            binding.recentText.visibility = View.VISIBLE
            binding.recentList.visibility = View.VISIBLE
            binding.deleteBtn.visibility = View.GONE


            binding.searchBar.post {
                binding.root.clearFocus()
                binding.searchBar.requestFocus()
            }

        }


        binding.searchEditbar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.deleteBtn.visibility = View.VISIBLE
                val keyword = newText.toString()
                searchWord(keyword)
            }
        })

        binding.searchEditbar.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->

            var handled = false

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveRecentWord(v.text.toString())

                binding.searchBar.requestFocus()

                // 키보드 내리기
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.searchEditbar.windowToken, 0)

                binding.recentText.visibility = View.VISIBLE
                binding.recentList.visibility = View.VISIBLE

                handled = true

            }

            handled
        })

    }

    fun initAccessibility() {

        binding.searchBar.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                // 기본 접근성 행동을 유지
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS)
            }

            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {
                    AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS -> {
                        if( adapter.searchKeyword != "") {
                            binding.searchBar.contentDescription = "링키지랩 크루 닉네임, 수정 중, ${adapter.searchKeyword}, 수정창, 크루 닉네임을 입력해주세요 예) 올리 "
                            binding.searchBar.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        } else {
                            binding.searchBar.contentDescription = "링키지랩 크루 닉네임, 수정, 크루 닉네임을 입력해주세요 예) 올리, 수정창 "
                            binding.searchBar.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        }

                        super.performAccessibilityAction(host, action, args)
                    }

                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })

    }

    fun searchWord(keyword : String) {

        // 최근 검색어 사라짐
        binding.recentText.visibility = View.GONE
        binding.recentList.visibility = View.GONE
        binding.krewList.visibility = View.VISIBLE

        if(keyword == "") {
            adapter.setDate(mutableListOf())
            adapter.searchKeyword = ""

        } else {

            val search_list = linkageLabKrew.filter { it.contains(keyword!!) }

            if(search_list != null) {
                // 몇개의 결과중 몇개의 결과가 있습니다.
                adapter.setDate(search_list.toMutableList())
                adapter.searchKeyword = keyword

                binding.searchBar.announceForAccessibility("${search_list.size}개의 결과 제안")

            }

        }
    }

    fun saveRecentWord(item : String) {
        recentSearchedList!!.put(item, recentSearchedList.size.toInt())

        val json = Gson().toJson(recentSearchedList)
        prefs.edit().putString("recentWordData", json).apply()

        setRecentWord()
    }

    fun removeRecentWord(item : String, currentIdx : Int) {

        val newPosition = if(currentIdx  > 0 ) currentIdx - 1 else -1

        recentSearchedList!!.remove(item)

        val json = Gson().toJson(recentSearchedList)
        prefs.edit().putString("recentWordData", json).apply()

        Log.d("newViewHolder","size : ${recentSearchedList.size}")

        if(recentSearchedList.size == 0) {
            // 최근검색어가 더이상 없는 경우
            // 초점 이동
            binding.root.clearFocus()
            binding.searchBar.requestFocus()

        } else {

            if(currentIdx == recentSearchedList.size && recentSearchedList.size > 0) {
                val newViewHolder = binding.recentList.findViewHolderForAdapterPosition(newPosition)
                //val item = newViewHolder?.itemView?.findViewById<ImageButton>(R.id.removeBtn)
                newViewHolder?.itemView?.requestFocus()
            }
        }

        setRecentWord()
    }


    fun setRecentWord() {

        val json = prefs.getString("recentWordData", null)
        val type = object : TypeToken<Map<String, Any>>() {}.type

        recentSearchedList = Gson().fromJson(json, type) ?: mutableMapOf()

        var newPreSearchedList = mutableListOf<String>()
        for (key in recentSearchedList.keys) {
            newPreSearchedList.add(key)
        }

        // 어댑터 데이터 다시 셋팅
        recentdapter.setDate(newPreSearchedList)
    }

}