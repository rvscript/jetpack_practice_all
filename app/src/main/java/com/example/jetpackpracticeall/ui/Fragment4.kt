package com.example.jetpackpracticeall.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackpracticeall.R
import com.example.jetpackpracticeall.databinding.Fragment4Binding
import com.example.jetpackpracticeall.ui.viewModels.MainViewModel

class Fragment4 : Fragment() {
    lateinit var binding: Fragment4Binding
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_4, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return binding.root
    }
}