package com.abdurakhmonoff.simpleto_doapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_todo_btn.setOnClickListener {
            val intent = Intent(this,AddTodo::class.java)
            startActivity(intent)
        }

        todo_list_btn.setOnClickListener {
            val intent = Intent(this,TodoList::class.java)
            startActivity(intent)
        }
    }
}