package com.D121201056.pretask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201056.pretask.R
import com.D121201056.pretask.fragments.HistoryFragmentDirections
import com.D121201056.pretask.fragments.ListFragmentDirections
import com.D121201056.pretask.model.Tugas
import com.D121201056.pretask.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var listTugas = emptyList<Tugas>()
    private var context: Context ?= null
    private lateinit var tugasViewModel: TugasViewModel
    class HistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val judul: TextView = itemView.findViewById(R.id.judul)
        val deadline: TextView = itemView.findViewById(R.id.deadline)
        val klik: CoordinatorLayout = itemView.findViewById(R.id.boxdetail)
        val delete:ImageView = itemView.findViewById(R.id.hapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {

        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        return HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterhistory,parent,false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = listTugas[position]

        holder.judul.text = currentItem.judul
        holder.deadline.text = currentItem.deadline

        holder.klik.setOnClickListener{
            val action = HistoryFragmentDirections.actionHistoryFragmentToFilledFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        holder.delete.setOnClickListener {
            tugasViewModel.deleteTugas(listTugas[position])
            
        }
    }

    override fun getItemCount(): Int {
        return listTugas.size
    }
    fun tambahData(tugas:List<Tugas>){
        this.listTugas=tugas
        notifyDataSetChanged()
    }
}