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
import com.example.jetpackpracticeall.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    lateinit var binding: Fragment1Binding
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_1, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(requireActivity()) {
            if (it.isNotEmpty() && activity != null) {
                binding.recyclerView.apply {
                    layoutManager =(LinearLayoutManager(requireContext()))
                    adapter = MainAdapter(it)
                }
            }
        }
        binding.btF2.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            val fragment = Fragment2()
            bundle.putString("KEY", "FRom Fragment 1")
            fragment.arguments  = bundle
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment).addToBackStack("Fragment2").commit()
        })
    }
}