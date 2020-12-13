package com.abdurakhmonoff.simpleto_doapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.abdurakhmonoff.simpleto_doapp.models.Todo
import com.abdurakhmonoff.simpleto_doapp.utils.MySharedPrefs
import kotlinx.android.synthetic.main.activity_todo_info.*

class TodoInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_info)

        val selectedTodo = intent.getSerializableExtra("TODO_SELECTED") as Todo

        var selectedCt = 0
        if (selectedTodo.category!="Open"){
            when(selectedTodo.category){
                "Development" -> selectedCt = 1
                "Uploading" -> selectedCt = 2
                "Reject" -> selectedCt = 3
                "Closed" -> selectedCt = 4
            }
        }
        val defaultSelected = category_rg.getChildAt(selectedCt) as RadioButton
        defaultSelected.isChecked = true

        actionBar?.title = selectedTodo.name
        supportActionBar?.title = selectedTodo.name
        todo_desc.text = selectedTodo.description
        todo_degree_img.setImageResource(selectedTodo.degree.img)
        todo_degree_txt.text = selectedTodo.degree.name
        todo_create_date.text = selectedTodo.crateDate
        todo_deadline_date.text = selectedTodo.deadLine

        ok_button.setOnClickListener {
            val selectedCategory = category_rg.checkedRadioButtonId
            val selectedName = findViewById<RadioButton>(selectedCategory).text
            val a = MySharedPrefs(this).getTodo()
            MySharedPrefs(this).removeTodo(selectedTodo.name)
            selectedTodo.category = "$selectedName"
            MySharedPrefs(this).addTodo(selectedTodo)
            val intent = Intent(this,TodoList::class.java)
            startActivity(intent)
            finish()
        }
    }
}