package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var radioButtonEven: RadioButton
    private lateinit var radioButtonOdd: RadioButton
    private lateinit var radioButtonPerfectSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
        editText = findViewById(R.id.textView)
        radioButtonEven = findViewById(R.id.radioButton)
        radioButtonOdd = findViewById(R.id.radioButton2)
        radioButtonPerfectSquare = findViewById(R.id.radioButton3)
        buttonShow = findViewById(R.id.button4)
        listView = findViewById(R.id.listView)

        // Thiết lập listener cho nút hiển thị kết quả
        buttonShow.setOnClickListener { showResults() }

        // Thiết lập padding cho view chính
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showResults() {
        val input = editText.text.toString()
        if (input.isEmpty()) return

        val n = input.toInt()
        val results = mutableListOf<String>()

        when {
            radioButtonEven.isChecked -> {
                for (i in 1..n) {
                    if (i % 2 == 0) {
                        results.add(i.toString())
                    }
                }
            }
            radioButtonOdd.isChecked -> {
                for (i in 1..n) {
                    if (i % 2 != 0) {
                        results.add(i.toString())
                    }
                }
            }
            radioButtonPerfectSquare.isChecked -> {
                for (i in 1..n) {
                    val sqrt = Math.sqrt(i.toDouble()).toInt()
                    if (sqrt * sqrt == i) {
                        results.add(i.toString())
                    }
                }
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        listView.adapter = adapter
    }
}
