package com.example.linkagelab.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityBarBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class ProgressBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()


    }

   fun initListener() {
       binding.toolbar.setOnClickListener {
           finish()
       }

       binding.showToastBtn.setOnClickListener {
           val myToast = Toast.makeText(this.applicationContext, "토스트 메세지 입니다", Toast.LENGTH_LONG)
           myToast.show()
       }

       binding.showSnackBarBtn.setOnClickListener {
           Snackbar.make(binding.mainContent, "SnackBar 메세지 입니다", Snackbar.LENGTH_LONG).show()
       }

       binding.oneDialogBtn.setOnClickListener {
           MaterialAlertDialogBuilder(this)
               .setTitle("알림창")
               .setMessage("버튼이 1개인 알림창 입니다.")
               .setPositiveButton("확인") {  dialog, which ->
                   dialog.dismiss()
               }
               .setCancelable(false)
               .show()
       }

       binding.twoDialogBtn.setOnClickListener {
           MaterialAlertDialogBuilder(this)
               .setTitle("알림창")
               .setMessage("버튼이 2개인 알림창 입니다.")
               .setPositiveButton("확인") {  dialog, which ->
                   dialog.dismiss()
               }
               .setNegativeButton("취소") { dialog, which ->
                   dialog.dismiss()
               }
               .setCancelable(false)
               .show()
       }



       binding.listDialogBtn.setOnClickListener {
           MaterialAlertDialogBuilder(this)
               .setTitle("리스트 알림창")
               .setItems(arrayOf("1번 목록", "2번 목록","3번 목록")) { dialog, which ->
                   val myToast = Toast.makeText(this.applicationContext, "${which + 1}번 목록 선택", Toast.LENGTH_SHORT)
                   myToast.show()
               }.show()
       }
    }
}