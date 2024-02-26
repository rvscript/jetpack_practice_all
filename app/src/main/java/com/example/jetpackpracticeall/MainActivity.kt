package com.example.jetpackpracticeall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragment1: fragment1
    lateinit var fragment2: fragment2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragment1 = fragment1()
        fragment2 = fragment2()
        setupFragments()
    }

    private fun setupFragments() {
        val textData = "Hello Fragment1"
        val bundle = Bundle()
        bundle.putString("KEY_F1", textData)
        fragment1.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container, fragment1)

        fragmentTransaction.commit()
    }
}

data class someData(
    val str: String
)