package com.D121201056.pretask.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201056.pretask.GlobalData
import com.D121201056.pretask.R
import com.D121201056.pretask.adapter.TugasAdapter
import com.D121201056.pretask.databinding.FragmentHomeBinding
import com.D121201056.pretask.databinding.FragmentListBinding
import com.D121201056.pretask.viewmodel.TugasViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding?=null
    private val binding get()=_binding!!
    private lateinit var tugasViewModel: TugasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentListBinding.inflate(inflater,container,false)
        val view= binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]


        val adapter = TugasAdapter()
        binding.listtugas.adapter = adapter
        binding.listtugas.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.kembali.setOnClickListener { findNavController().navigate(R.id.action_listFragment_to_homefragment) }
        binding.namakategori.text = GlobalData.kategori

        when (GlobalData.kategori){
            "Penting Mendesak"->{
                tugasViewModel.readAllDataPentingMendesak.observe(viewLifecycleOwner){ tugas->
                    adapter.tambahData(tugas)
                    visibiliti(tugas.size)

                }
            }
            "Tidak Penting Mendesak"->{
                tugasViewModel.readAllDataTidakPentingMendesak.observe(viewLifecycleOwner){ tugas->
                    adapter.tambahData(tugas)
                    visibiliti(tugas.size)
                }

            }
            "Penting Tidak Mendesak"->{
                tugasViewModel.readAllDataPentingTidakMendesak.observe(viewLifecycleOwner){ tugas->
                    adapter.tambahData(tugas)
                    visibiliti(tugas.size)

                }

            }
            else->{
                tugasViewModel.readAllDataTidakPentingTidakMendesak.observe(viewLifecycleOwner){ tugas->
                    adapter.tambahData(tugas)
                    visibiliti(tugas.size)
                }
            }
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
