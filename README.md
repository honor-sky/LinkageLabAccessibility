# LinkageLab - 기본 컴포넌트 개발 & 접근성 개선 과제

## Intro
- 안드로이드 모바일 개발에서 자주 사용되는 컴포넌트를 개발해 접근성이 지켜졌는지 확인합니다 (기본 컴포넌트)
- 컴포넌트가 기본적으로 접근성이 지켜지지 않는다면, 접근성을 개선 작업을 진행합니다 (커스텀 컴포넌트)
- 개선 작업은 안드로이드 접근성 함수 및 옵션을 사용하거나, 완전히 컴포넌트를 커스텀해 해결합니다


## Tech Stack
- Android, JetPack
- Kotlin, XML

## UI
- 유사한 컴포넌트끼리 하나의 페이지에 개발 / 화면 전체를 사용하는 컴포넌트의 경우 하나의 페이지에 개발
- 컴포넌트 개발 후 접근성 개선이 필요한 경우, 아래에 추가 개발
  - '기본 컴포넌트 입니다' / '커스텀 컴포넌트 입니다' 문구로 구분
  - 컴포넌트가 1개만 개발되어 있는 경우는 접근성이 잘 지켜져 추가 개선 작업을 하지 않음
  
![image](https://github.com/user-attachments/assets/32a6d167-4fc1-4d38-a78c-9670f241b7b2)


## Foldering
- 각 컴포넌트에 대응하는 코드 파일을 확인할 수 있습니다.
- layout은 화면 UI 코드, Activuty/Fragment는 동작 관련 코드입니다. 

|  Componant | Activuty/Fragment (.kt) |  Layout (.xml) | 
| :----------- | :------------: | :------------: |
|  메인 화면 |   MainActivity.kt   |   activity_main.xml  |
|  Basic, Image, Toggle, Radio, Checkbox, FAB, Chip, Switch, Slider |   ButtonActivity.kt   |   activity_button.xml  |
|  EditText  |   EditTextActivity.kt   |  activity_edittext.xml  |
|  Tab  |   PickerActivity.kt   |  activity_picker.xml  |
|  TimePicker  |   PickerActivity.kt, TimPickerFrag.kt,  |  activity_picker.xml, fragment_time_picker.xml |
|  DatePicker  |   PickerActivity.kt, DatePickerFrag.kt  |  activity_picker.xml, fragment_date_picker.xml  |
|  Calendar  |   PickerActivity.kt, CalendarFrag.kt, DayAdapter.kt  |  activity_picker.xml, fragment_calendar.xml, day_item.xml, month_item.xml  |
|  Bottom Sheet  |  SheetActivity.kt   |    activity_sheet.xml, text_bottom_sheet.xml  |
|  Custom Bottom Sheet  |  CustomSheetActivity.kt    |   activity_sheet_custom.xml, text_bottom_sheet_custom.xml  |
|  Drawer Navigation  |   DrawerActivity.kt, HomeFragment.kt, BookmarkFragment.kt, MypageFragment.kt    |    activity_drawer.xml, drawer_header.xml, drawer_main.xml, drawer_main_content.xml, fragment_home.xml, fragment_bookmark.xml, fragment_mypage.xml  |
|  Custom Drawer Navigation  |   DrawerActivity.kt, HomeFragment.kt, BookmarkFragment.kt, MypageFragment.kt    |    activity_drawer_custom.xml, drawer_header.xml, drawer_main.xml, drawer_main_content.xml, fragment_home.xml, fragment_bookmark.xml, fragment_mypage.xml  |
| Option Menu, Context Menu, Popup Menu  |   MenuActivity.kt   |   activity_menu  |
| ProgressBar, RatingBar, ToastBar, SnackBar, AlertDialog  |  BarActivity.kt    |   activity_bar  |
| RecyclerViw(sroll)  |  ScrollActivity.kt, HorizontalListAdapter.kt, VerticalListAdapter.kt   |   activity_scroll.xml, music_item.xml, vertical_item.xml  |
| Viewpager |  ViewpagerActivity.kt, HomeFragment.kt, BookmarkFragment.kt, MypageFragment.kt    |   activity_viewpager.xml,  fragment_home.xml, fragment_bookmark.xml, fragment_mypage.xml    |
| Suggestion 기능 (SearchView) |  SearchActivity.kt, NicknameAdapter.kt   |  activity_searchview  |


- layout 파일 경로 : app > src > layout
- Activuty/Fragment 파일 경로: app > src > main > java/com/example/linkagelab > presentation
