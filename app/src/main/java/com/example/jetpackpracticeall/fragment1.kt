package com.example.jetpackpracticeall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class fragment1 : Fragment() {
    lateinit var tv1: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)
        tv1 = view.findViewById(R.id.tv1) as TextView
        val args = arguments
        if (args!=null) {
            val textData = args.getString("KEY_F1")
            tv1.text = textData
            CoroutineScope(IO).launch {
                delay(2000)
                withContext(Main) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment2())
                        .addToBackStack("fragment2")
                        .commit()
                }
            }
        }
        return view
    }
}