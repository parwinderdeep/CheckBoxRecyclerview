package com.app.checkboxtest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val numCheckboxET = findViewById(R.id.checkbox_numberET) as EditText
        val saveBT = findViewById(R.id.saveBT) as Button

        saveBT.setOnClickListener {
            val editor = getSharedPreferences("test_pref", MODE_PRIVATE).edit()
            editor.putString("number_checkbox", numCheckboxET.text.toString().trim())
            editor.apply()
            finish()
        }
    }
}