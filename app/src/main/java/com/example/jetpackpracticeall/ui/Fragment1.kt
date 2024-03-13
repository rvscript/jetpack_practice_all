package com.example.jetpackpracticeall.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackpracticeall.ui.viewModels.MainViewModel
import com.example.jetpackpracticeall.R
import com.example.jetpackpracticeall.databinding.Fragment1Binding
import com.example.jetpackpracticeall.utils.LooperThread
import kotlinx.coroutines.delay
import javax.security.auth.login.LoginException

class Fragment1 : Fragment(){
    lateinit var binding: Fragment1Binding
    lateinit var viewModel: MainViewModel
    lateinit var handler: Handler
    private var count = 0
    lateinit var looperThread: LooperThread
    companion object {
        const val TAG = "threadLoops"
    }
    var isStopLoop = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_1, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        handler = Handler(Looper.getMainLooper())
        looperThread = LooperThread()
        looperThread.start()
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

        binding.btStartThread.setOnClickListener {
            isStopLoop = true
            Thread {
                run {
                    while (isStopLoop) {
                        try {
                            Thread.sleep(1000)
                            count++
                        } catch (e: InterruptedException) {
                            Log.i(TAG, "${e.message}")
                        }
                        Log.i(TAG, "threadId=${Thread.currentThread().id}, count = $count")
                        handler.post(Runnable {
                            run {
                                binding.tvF1.text = "count = $count"
                            }
                        })
                    }
                }
            }.start()
        }

        binding.btStartThread2.setOnClickListener {
            executeOnCustomLooper()
        }

        binding.btStopThread.setOnClickListener {
            isStopLoop = false
        }
    }

    private fun executeOnCustomLooper() {
        isStopLoop = true
        Thread {
            run {
                while (isStopLoop) {
                    try {
                        Log.i(TAG, "ThreadId of thread Sending Message: ${Thread.currentThread().id}")
                        Thread.sleep(1000)
                        count++
                        val message = Message()
                        message.obj = "$count"
                        looperThread.myHandler.sendMessage(message)
                    } catch (e: InterruptedException) {
                        Log.i(TAG, "executeOnCustomLooper: Thread Interrupted")
                    }
                }
            }
        }.start()
    }

    private fun getMessageWithCount(count: String): Message {
        val message = Message()
        message.obj = "$count"
        return message
    }
}