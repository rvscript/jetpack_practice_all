package com.example.jetpackpracticeall.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackpracticeall.ui.viewModels.MainViewModel
import com.example.jetpackpracticeall.R
import com.example.jetpackpracticeall.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    private lateinit var binding: Fragment2Binding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_2, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val str = arguments?.getString("KEY", "")?: "NULL ARGS"
        binding.tvTitleFrag2.text = str
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        viewModel.data.observe(requireActivity()) {
            if (it.isNotEmpty()) {
                binding.recyclerViewF2.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewF2.adapter = F2Adapter(it)
            }
        }
    }
}