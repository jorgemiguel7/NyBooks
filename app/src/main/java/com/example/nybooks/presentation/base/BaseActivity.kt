package com.example.nybooks.presentation.base

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    protected fun setupToolbar(toolbar: androidx.appcompat.widget.Toolbar, titleIdRes: Int){
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
    }
}