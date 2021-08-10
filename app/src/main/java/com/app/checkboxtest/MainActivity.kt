package com.app.checkboxtest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"
    lateinit var checkboxRV: RecyclerView
    lateinit var rootLL: LinearLayout
    var list: ArrayList<CheckboxData?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intViews()
    }

    private fun intViews() {
        rootLL = findViewById(R.id.rootLL)
        checkboxRV = findViewById(R.id.checkboxRV)
        checkboxRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        if (list != null)
            list.clear()
        setCheckboxAdapter()
    }

    private fun setCheckboxAdapter() {
        val prefs = getSharedPreferences("test_pref", MODE_PRIVATE)
        val number_checkbox = prefs.getString("number_checkbox", "10")
        for (i in 0 until number_checkbox!!.toInt()) {
            val data = CheckboxData(i, i + 1, i.toString(), false)
            list.add(data)
        }
        checkboxRV.adapter = CheckboxAdapter(this, list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.submit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.submit) {
            printCheckboxState()
        } else if (item.itemId == R.id.settings) {
            gotoSettingsActivity()
        }
        return true
    }

    private fun gotoSettingsActivity() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun printCheckboxState() {
        for (i in 0 until list.size) {
            Log.e(
                TAG,
                "Row " + i + " : c" + list[i]!!.checkbox_in_row + "(" + list[i]!!.isSelected + ")"
            )
        }
    }
}