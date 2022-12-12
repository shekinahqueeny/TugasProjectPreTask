package com.D121201056.pretask.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201056.pretask.R
import com.D121201056.pretask.adapter.HistoryAdapter
import com.D121201056.pretask.adapter.TugasAdapter
import com.D121201056.pretask.databinding.FragmentHistoryBinding
import com.D121201056.pretask.databinding.FragmentHomeBinding
import com.D121201056.pretask.viewmodel.TugasViewModel


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding?=null
    private val binding get()=_binding!!
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentHistoryBinding.inflate(inflater,container,false)
        val view= binding.root


        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]


        val adapter = HistoryAdapter()
        binding.listtugas.adapter = adapter
        binding.listtugas.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        tugasViewModel.readAllData.observe(viewLifecycleOwner){ tugas->
            adapter.tambahData(tugas)
            visibiliti(tugas.size)
        }


        return view
    }

    private fun visibiliti(size:Int){
        if (size==0){
            binding.kosong.visibility = View.VISIBLE
            binding.listtugas.visibility = View.GONE
        }else{
            binding.kosong.visibility = View.GONE
            binding.listtugas.visibility = View.VISIBLE
        }
    }
}
