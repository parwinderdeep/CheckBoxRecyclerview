package com.app.checkboxtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class CheckboxAdapter(
    var context: MainActivity,
    var dataList: ArrayList<CheckboxData?>
) : RecyclerView.Adapter<CheckboxAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootLL: LinearLayout = itemView.findViewById(R.id.parentLL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_checkbox, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.rootLL.childCount == 0) {
            for (i in 0 until dataList[position]!!.checkbox_in_row) {
                val checkbox = CheckBox(context)
                checkbox.text = dataList[position]!!.text
                checkbox.tag = dataList[position]!!.id
                checkbox.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                checkbox.setOnClickListener {
                    val linearLayoutObj = holder.rootLL
                    for (i in 0 until linearLayoutObj.childCount) {
                        val checkBoxView = linearLayoutObj.getChildAt(i) as CheckBox
                        if (holder.itemView.id == linearLayoutObj.id) {
                            dataList[position]!!.isSelected = true
                            checkBoxView.isChecked = true
                        } else {
                            dataList[position]!!.isSelected = false
                            checkBoxView.isChecked = false
                        }
                    }
                }
                holder.rootLL.addView(checkbox)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
