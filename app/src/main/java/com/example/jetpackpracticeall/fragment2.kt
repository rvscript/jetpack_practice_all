package com.example.jetpackpracticeall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation

class fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment2, container, false)
        val tv2 = view.findViewById<TextView>(R.id.tv_fg_2)
        tv2.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment2_to_fragment1)
        }
        return view
    }

}