package com.example.linkagelab.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityToastBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class ToastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    fun initListener() {

        binding.showToastBtn.setOnClickListener {
            val myToast = Toast.makeText(this.applicationContext, "토스트 메세지 입니다", Toast.LENGTH_LONG)
            myToast.show()
        }

        binding.showSnackBarBtn.setOnClickListener {
            Snackbar.make(binding.mainContent, "SnackBar 메세지 입니다", Snackbar.LENGTH_LONG).show()
        }

        binding.showDialogBtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("기본 알림창")
                .setMessage("알림창 내용입니다~~")
                .setPositiveButton("확인") {  dialog, which ->
                    dialog.dismiss()
                }
                .setNegativeButton("취소") { dialog, which ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

    }

}