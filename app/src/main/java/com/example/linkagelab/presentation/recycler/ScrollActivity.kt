package com.example.linkagelab.presentation.recycler

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.ActivityScrollBinding

class ScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollBinding

    val country = arrayListOf("캐나다", "독일", "호주", "프랑스", "한국", "일본", "중국", "콩고", "브라질", "이집트")
    val music = arrayListOf("노래1","노래2","노래3","노래4","노래5","노래6","노래7","노래8","노래9","노래10")
    val linkageLabKrew = mutableListOf("올리","하퍼","미아","이든","칸","아마라","레스더","웨인","리암","웬디","조셉","카일라","맥스","폴라","델리",
        "로웰","마리","월드","엘리자","헤이즐","제임스","제니","카이","드웨이","민디","찰리","콘라드","히로","제이콥","제니퍼","카오","키미","라일라")

    val brunchContents = mutableListOf(BrunchContent("01화 [인턴일기] 눈 떠보니 인턴이 되었습니다", "일기를 시작하며 이 글을 읽는 모든 분들께. IT 자회사형 장애인 표준 사업장에서 일하는 인턴 하니입니다. 회사를 다니며 느끼는 점들을 솔직하고 담백하게 이야기하려고 해요. 누군가의 비밀일기를 읽는다는 마음으로, 제가 느끼는 설렘과 혼란, 각종 에피소드와 생각들을 함께 나누며"),
        BrunchContent("02화 [인턴일기] 두근 두근 인턴의 첫 임무", "화요일 아침, 출근과 함께 자리를 배정받았다. 간단히 책상 정리를 마친 후, 드디어 첫 팀 회의에 참석했다. 회의실 문을 열며 느꼈던 그 긴장감이 아직도 생생하다. 회사에 들어온 이후 가장 설레면서도 긴장되는 순간이었다. 회의에서 진행 중인 여러 사업과 업무 방식에 대해 배우게 되었는데,"),
        BrunchContent("03화 [인턴일기] 출근했습니다. 근데 이제 기타를 곁들인", "2주에 한 번, 수요일마다 기타를 메고 출근하는 크루원들이 있다. 우리 회사는 신청자를 받아 2주에 한 번씩 '아트랩스'라는 프로그램을 진행 중이다."),
        BrunchContent("05화 [인턴일기] 점심시간이야말로 인턴들의 전쟁터!!", "점심에 진심인 병아리 인턴들 우리 회사에는 나 말고도 아일라, 올리, 하퍼라는 인턴 동기들이 있다."),
        BrunchContent("[Android/Coroutine] Debounce로 검색 기능 문제 해결하기 / 검색 딜레이 주기","문제상황 '이디야' 를 검색하고 싶다고 가정할때, 이 -> 이디 -> 이디야 이런식으로 사용자가 키보드에 검색어를 입력하게 된다. 이때 결과적으론 가장 마지막에 '이디야' 에 대한 검색 결과가 화면에 표시되어야 하는데 '이디' 에 대한 검색 결과만 표시되는 경우가 있다."),
        BrunchContent("[Android/세미나실] 2024 Google I/O 참가 후기 및 정리","연구실 생활을 할 때 교수님을 따라 컨퍼런스 발표나 참관 등을 해본 경험은 있지만 스스로 적극적으로 나서서 참가해본 적은 없었다. Google I/O는 안드로이드 개발자로서 더 폭넓은 지식을 알아가고 싶고 성장하고 싶다는 생각에 처음으로 내돈내산 내발로 참여해보는 안드로이드 관련 대외 컨퍼런스, 세미나 행사였고 그래서 더 의미가 있었다. 컨퍼런스는 인천 송도에서 열렸는데 뭔가 \"멋있다~\" 라는 생각이 들었지만 막상 가보니"),
    BrunchContent("[Android/세미나실] 안드로이드를 더 잘하기 위한 자료 모음집","서류 지원서나 면접 질문에서 종종 물어보는 질문이 있다. '(안드로이드)개발을 잘하기 위해서 무슨 노력을 해봤나요?/ 어디까지 해봤나요?' 처음에는 '팀을 꾸려서 프로젝트를 해봤다, 오랜 시간 기능 개발/버그해결을 위해 고민해봤다.' 라고 대답했지만 이런 이야기는 너무 흔해빠졌다고 생각했다. 그리고 실제로 안드로이드 개발자로서 성장하기 위해 뭔가 깊게 생각하고 노력한 적은 없다는 생각이 들었다.그래서 뭘 할 수 있을까....에 대해 생각해보았다."),
    BrunchContent("[Git / Command] 내가 보려고 만든 Git 명령어 모음집","3일만 안 써도 까먹는 깃 명령어.... 진짜 빡대가리..정리해두고 그때그때 보면서 써야지!! 브랜치 변경\ngit checkout \n\n변경할 브랜치 브랜치 생성 및 전환\ngit checkout -b 생성할 브랜치 이름\n" +
            "\n브랜치 삭제\nLocal 브랜치 삭제\ngit branch -d 로컬브랜치이름\nRemote 브랜치 삭제\ngit push origin --delete  "),
    BrunchContent("[Git / Git Flow] Git Flow 수립 도전기!","\uD83D\uDCA1Git Flow 란?버전 관리와 개발자 간의 분업을 위해 일종의 규칙과 프로세스에 맞춰 git을 사용하는 것상황에 맞게 브랜치를 만들고 머지 기본적인 틀이 있지만 팀바팀!  기본적으로 git flow에서 사용되는 브랜치는 main / dev / feature / hotfix 로 이루어져 있다."),
    BrunchContent("[안드로이드/Coroutine] Coroutine 예제를 통해 본격 딥다이브 & Structured concurrency","중간 점검 차 계속 이론, 개념 공부만 하는 것이 아니라 직접 몇가지 실습을 통해 코루틴을 익혀보려고 한다. 유튜브에서 ‘새차원, 코틀린 코루틴’ 강의를 참고해 공부하면 공식문서 실습코드들을 따라 쳐보면서 공부할 수 있다.  그런데 이게 코드 예제들을 마주할 때마다 새롭고 띠용?,,, 하는게 많다. 내가 이해한건 A라 똑같이 적용해서 풀면 틀리고 B라는 개념이 또 등장한다.그래서 예제들을 많이 보면서 더 많이 생각하고 더 많이 배우게 되는 것 같다."))


    lateinit var verticalAdapter : VerticalListAdapter
    lateinit var verticalCustomAdapter : VerticalListAdapter

    lateinit var horizontalNumberAdapter : HorizontalListAdapter
    lateinit var horizontalMusicAdapter : HorizontalListCustomAdapter

    var currentMusicIdx = 0
    var displayedItemsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initListener()

    }

    fun initAdapter() {

        verticalAdapter = VerticalListAdapter()
        verticalCustomAdapter = VerticalListAdapter()
        horizontalNumberAdapter = HorizontalListAdapter()
        horizontalMusicAdapter  = HorizontalListCustomAdapter()

        verticalAdapter.setData(brunchContents)
        horizontalNumberAdapter.setData(music)
        horizontalMusicAdapter.setData(music)

        // 더보기 목록 - 첫 아이템 추가
        val newItems = brunchContents.drop(displayedItemsCount).take(5).toMutableList() // 다음 5개 아이템 가져오기
        verticalCustomAdapter.addItems(newItems)
        displayedItemsCount += newItems.size

        binding.verticalRecyclerView.adapter = verticalAdapter
        binding.verticalRecyclerViewCustom.adapter = verticalCustomAdapter
        binding.horizontalRecyclerViewBasic.adapter = horizontalNumberAdapter
        binding.horizontalRecyclerViewCustom.adapter = horizontalNumberAdapter
        binding.horizontalRecyclerViewCustom2.adapter = horizontalMusicAdapter

    }


    fun initListener() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.leftBtn.setOnClickListener {
            scrollRecyclerView(-1)
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }

        binding.rightBtn.setOnClickListener {
            scrollRecyclerView(1)
        }

        binding.moreBtn.setOnClickListener {
            loadMoreItems()
        }

        binding.verticalRecyclerViewCustom.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 스크롤이 끝에 도달했을 때 "더보기" 버튼을 보여줍니다.
                if (!recyclerView.canScrollVertically(1)) {
                    binding.moreBtn.visibility = View.VISIBLE
                } else {
                    binding.moreBtn.visibility = View.GONE
                }
            }
        })

       binding.horizontalRecyclerViewCustom2Layout.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
           override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
               super.onInitializeAccessibilityNodeInfo(host, info)

               val item = horizontalMusicAdapter.stringList?.get(currentMusicIdx)
               info.contentDescription = "${item}, 총 ${horizontalMusicAdapter.itemCount}개의 항목 중 ${currentMusicIdx + 1}번째 항목"
               binding.horizontalRecyclerViewCustom2Layout.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)



           }
        })

        // 재생 작업 추가
        ViewCompat.addAccessibilityAction(
            binding.horizontalRecyclerViewCustom2Layout,
            "플레이리스트  재생"
        ) { _, _ ->

            val item = horizontalMusicAdapter.stringList?.get(currentMusicIdx)

            binding.horizontalRecyclerViewCustom2.contentDescription = "${item}, 노래가 재생됩니다"
            binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

            Toast.makeText(this, "얄리얄리얄라리 얄라셩",Toast.LENGTH_SHORT).show()

            true
        }

        ViewCompat.addAccessibilityAction(
            binding.horizontalRecyclerViewCustom2Layout,
            "플레이리스트 상세보기"
        ) { _, _ ->

            Toast.makeText(this, "플레이리스트 상세 화면",Toast.LENGTH_SHORT).show()
            true
        }

        ViewCompat.addAccessibilityAction(
            binding.horizontalRecyclerViewCustom2Layout,
            "다음 항목"
        ) { _, _ ->

            if (currentMusicIdx < horizontalMusicAdapter.itemCount - 1) {
                binding.horizontalRecyclerViewCustom2.smoothScrollToPosition(currentMusicIdx)
                currentMusicIdx++

            } else {
                currentMusicIdx = horizontalMusicAdapter.itemCount - 1
            }

            val item = horizontalMusicAdapter.stringList?.get(currentMusicIdx)

            // 접근성 이벤트로 음성 출력
            binding.horizontalRecyclerViewCustom2.contentDescription = "${item}, 총 ${horizontalMusicAdapter.itemCount}개의 항목 중 ${currentMusicIdx + 1}번째 항목"
            binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

            true
        }

        // '이전 항목' 작업에서 smoothScrollToPosition을 호출하고 스크롤 완료 후에 음성 출력
        ViewCompat.addAccessibilityAction(
            binding.horizontalRecyclerViewCustom2Layout,
            "이전 항목"
        ) { _, _ ->

            if (currentMusicIdx > 0) {
                // 이전 항목으로 스크롤
                binding.horizontalRecyclerViewCustom2.smoothScrollToPosition(currentMusicIdx)
                currentMusicIdx--

            } else {
                currentMusicIdx = 0
            }

            val item = horizontalMusicAdapter.stringList?.get(currentMusicIdx)

            // 접근성 이벤트로 음성 출력
            binding.horizontalRecyclerViewCustom2.contentDescription = "${item}, 총 ${horizontalMusicAdapter.itemCount}개의 항목 중 ${currentMusicIdx + 1}번째 항목"
            binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

            true
        }

    }




    private fun scrollRecyclerView(direction: Int) {
        // 현재 스크롤 위치를 가져온다
        val layoutManager = binding.horizontalRecyclerViewCustom.layoutManager as LinearLayoutManager
        val currentPosition = layoutManager.findFirstVisibleItemPosition()

        // 이동할 위치 계산 (3개 항목 단위로 이동)
        var targetPosition = currentPosition + direction * 3

        // 스크롤 이동
        if (targetPosition in 0 until binding.horizontalRecyclerViewCustom.adapter!!.itemCount) {
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(targetPosition)

            // 현재 어떤 컴포넌트가 가장 앞에 있는지 안내
            binding.horizontalRecyclerViewCustom.contentDescription = "${targetPosition}번째 항목으로 스크롤 이동"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

        } else if(targetPosition < 0) {

            targetPosition = 1
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(0)
            // 현재 어떤 컴포넌트가 가장 앞에 있는지 안내
            binding.horizontalRecyclerViewCustom.contentDescription = "항목 리스트의 처음에 도달"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

        } else if(targetPosition > binding.horizontalRecyclerViewCustom.adapter!!.itemCount) {
            targetPosition = binding.horizontalRecyclerViewCustom.adapter!!.itemCount
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(binding.horizontalRecyclerViewCustom.adapter!!.itemCount)

            // 현재 어떤 컴포넌트가 가장 앞에 있는지 안내
            binding.horizontalRecyclerViewCustom.contentDescription = "항목 리스트의 마지막에 도달"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }
    }


    fun loadMoreItems() {
        val newItems = brunchContents.drop(displayedItemsCount).take(5).toMutableList() // 다음 5개 아이템 가져오기
        verticalCustomAdapter.addItems(newItems) // 어댑터에 추가

        binding.verticalRecyclerViewCustom.post {

            val viewHolder =
                binding.verticalRecyclerViewCustom.findViewHolderForAdapterPosition(displayedItemsCount)
            if (viewHolder != null) {
                val itemView = viewHolder.itemView

                itemView.requestFocus()
                itemView.announceForAccessibility("아래로 항목 추가됨")
                itemView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            }

            displayedItemsCount += newItems.size
            binding.moreBtn.visibility = if (displayedItemsCount < brunchContents.size - 5) View.VISIBLE else View.GONE // 더 이상 아이템이 없으면 버튼 숨기기

        }
    }
}