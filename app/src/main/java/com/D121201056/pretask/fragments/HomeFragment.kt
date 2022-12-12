package com.D121201056.pretask.fragments

import android.os.Bundle
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.D121201056.pretask.GlobalData
import com.D121201056.pretask.R
import com.D121201056.pretask.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding= FragmentHomeBinding.inflate(inflater,container,false)
        val view= binding.root

        menu(view)

        return view
    }
    private fun menu(view: View){
        binding.apply {
            pentingmendesak.setOnClickListener {
                GlobalData.kategori  ="Penting Mendesak"
                findNavController().navigate(R.id.action_homefragment_to_listFragment)
            }
            pentingtidakmendesak.setOnClickListener {
                GlobalData.kategori  ="Penting Tidak Mendesak"
                findNavController().navigate(R.id.action_homefragment_to_listFragment)
            }
            tidakpentingmendesak.setOnClickListener {
                GlobalData.kategori  ="Tidak Penting Mendesak"
                findNavController().navigate(R.id.action_homefragment_to_listFragment)
            }
            tidakpentingtidakmendesak.setOnClickListener {
                GlobalData.kategori  ="Tidak Penting Tidak Mendesak"
                findNavController().navigate(R.id.action_homefragment_to_listFragment)
            }
        }
    }
}