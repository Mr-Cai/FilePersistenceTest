package com.example.filepersistencetest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputText = load()
        if (!TextUtils.isEmpty(inputText)) {
            inputET!!.setText(inputText)
            inputET!!.setSelection(inputText.length)
            Toast.makeText(this, "恢复成功", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        save("${inputET.text}")
    }

    private fun save(inputText: String) {
        BufferedWriter(OutputStreamWriter(openFileOutput("data", Context.MODE_PRIVATE))).apply {
            write(inputText)
            close()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            BufferedReader(InputStreamReader(openFileInput("data"))).forEachLine {
                content.append(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "$content"
    }

}
