package com.D121201056.pretask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201056.pretask.R
import com.D121201056.pretask.fragments.ListFragmentDirections
import com.D121201056.pretask.model.Tugas

class TugasAdapter :RecyclerView.Adapter<TugasAdapter.TugasViewHolder>(){

    private var listTugas = emptyList<Tugas>()
    class TugasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val judul: TextView = itemView.findViewById(R.id.judul)
        val deadline:TextView = itemView.findViewById(R.id.deadline)
        val klik:CoordinatorLayout = itemView.findViewById(R.id.boxdetail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        //context

        return TugasViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptertugas,parent,false))
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val currentItem = listTugas[position]

        holder.judul.text = currentItem.judul
        holder.deadline.text = currentItem.deadline

        holder.klik.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToFilledFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
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