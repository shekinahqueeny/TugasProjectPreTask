package com.D121201056.pretask.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.D121201056.pretask.R
import com.D121201056.pretask.databinding.FragmentFilledBinding
import com.D121201056.pretask.databinding.FragmentListBinding
import com.D121201056.pretask.model.Tugas
import com.D121201056.pretask.viewmodel.TugasViewModel

class FilledFragment : Fragment() {
    private var _binding: FragmentFilledBinding? = null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel
    private val args by navArgs<FilledFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilledBinding.inflate(inflater, container, false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]


        binding.judul.text = args.currentTugas.judul
        binding.deskripsi.text = args.currentTugas.deskripsi
        binding.tanggal.text = args.currentTugas.deadline

        binding.kembali.setOnClickListener{
            findNavController().navigate(R.id.action_filledFragment_to_listFragment)
        }

        if(args.currentTugas.kategori=="Penting Mendesak"){
            binding.kategori.text = args.currentTugas.kategori
            binding.kategori.setTextColor(resources.getColor(R.color.green))
        }
        else if(args.currentTugas.kategori=="Penting Tidak Mendesak"){
            binding.kategori.text = args.currentTugas.kategori
            binding.kategori.setTextColor(resources.getColor(R.color.blue))
        }
        else if(args.currentTugas.kategori=="Tidak Penting Mendesak"){
            binding.kategori.text = args.currentTugas.kategori
            binding.kategori.setTextColor(resources.getColor(R.color.yellow))
        }
        else {
            binding.kategori.text = args.currentTugas.kategori
            binding.kategori.setTextColor(resources.getColor(R.color.purple))
        }
        binding.TugasSelesai.setOnClickListener {
            val update = Tugas(args.currentTugas.id,
                args.currentTugas.judul,
                args.currentTugas.deskripsi,
                args.currentTugas.deadline,
                args.currentTugas.kategori,
                "Selesai")
            tugasViewModel.updatetugas(update)
            findNavController().navigate(R.id.action_filledFragment_to_listFragment)
        }





        return view
    }
}