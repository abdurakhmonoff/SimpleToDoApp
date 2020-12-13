package com.abdurakhmonoff.simpleto_doapp

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.abdurakhmonoff.simpleto_doapp.models.Todo
import com.abdurakhmonoff.simpleto_doapp.utils.AdapterELV
import com.abdurakhmonoff.simpleto_doapp.utils.MySharedPrefs
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.group_item2.*
import kotlin.math.exp

class TodoList : AppCompatActivity() {
    private lateinit var titleList:ArrayList<String>
    private lateinit var map:HashMap<String,List<Todo>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        loadData()

        expandable_lv.setAdapter(AdapterELV(titleList,map))

        expandable_lv.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val intent = Intent(this,TodoInfo::class.java)
            val todoName = map[titleList[groupPosition]]!![childPosition].name
            val allTodos = MySharedPrefs(this).getTodo()
            val todo = allTodos.find { it.name == todoName }
            intent.putExtra("TODO_SELECTED",todo)
            startActivity(intent)
            expandable_lv.collapseGroup(groupPosition)
            finish()
            true
        }
    }

    private fun loadData(){
        titleList = arrayListOf("Open", "Development","Uploading","Reject","Closed")
        map = HashMap()
        val allTodos = MySharedPrefs(this@TodoList).getTodo()
        if(allTodos.isEmpty()){
            Toast.makeText(this, "You don't have any to-do!", Toast.LENGTH_SHORT).show()
            map["Open"] = listOf()
            map["Development"] = listOf()
            map["Uploading"] = listOf()
            map["Reject"] = listOf()
            map["Closed"] = listOf()
        }else{
            map["Open"] = allTodos.filter { it.category == "Open" }
            map["Development"] = allTodos.filter { it.category == "Development" }
            map["Uploading"] = allTodos.filter { it.category == "Uploading" }
            map["Reject"] = allTodos.filter{ it.category == "Reject" }
            map["Closed"] = allTodos.filter{ it.category == "Closed" }
        }
    }
}