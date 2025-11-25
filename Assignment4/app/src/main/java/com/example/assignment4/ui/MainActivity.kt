package com.example.assignment4.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment4.MoviesFragment

import com.example.assignment4.R


class MainActivity: AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, MoviesFragment())
                .commit()
        }
    }
}