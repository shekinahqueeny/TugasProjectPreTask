package com.D121201056.pretask

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.D121201056.pretask.databinding.ActivityTambahBinding
import com.D121201056.pretask.model.Tugas
import com.D121201056.pretask.viewmodel.TugasViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TambahActivity : AppCompatActivity() {
    private lateinit var binding :ActivityTambahBinding
    private lateinit var tugasViewModel: TugasViewModel
    val deadline = Calendar.getInstance()
    var deadlineString = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tugasViewModel=ViewModelProvider(this)[TugasViewModel::class.java]

        var isChecking = true
        var mCheckedId = 0
        binding.row1.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1 && isChecking) {
                isChecking = false
                binding.row2.clearCheck()
                mCheckedId = checkedId
            }
            isChecking = true
        })

        binding.row2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1 && isChecking) {
                isChecking = false
                binding.row1.clearCheck()
                mCheckedId = checkedId
            }
            isChecking = true
        })
        val dateSetListener = object:DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, tahun: Int, bulan: Int, hari: Int) {
                deadline.set(Calendar.YEAR,tahun)
                deadline.set(Calendar.MONTH,bulan)
                deadline.set(Calendar.DAY_OF_MONTH,hari)
                updateDateInView(binding.deadline,deadline)
            }
        }
        binding.deadline.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@TambahActivity,
                dateSetListener,
                deadline.get(Calendar.YEAR),
                deadline.get(Calendar.MONTH),
                deadline.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
        binding.kembali.setOnClickListener {
            finish()
        }

        binding.addTugas.setOnClickListener {
            var radio = findViewById<RadioButton>(mCheckedId)
            var judul = findViewById<EditText>(R.id.judul).text.toString()
            var deskripsi = findViewById<EditText>(R.id.deskripsi).text.toString()

            //Log.d("CHECK ID=",radio.text.toString())

            if(judul.isEmpty()){
                binding.judul.error = "Judul harus di isi"
                return@setOnClickListener
            }
            if(deadlineString.isNullOrEmpty()){
                Toast.makeText(this,"Deadline belum dipilih",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(deskripsi.isEmpty()){
                binding.deskripsi.error = "Deskripsi harus di isi"
                return@setOnClickListener
            }

            if (mCheckedId == 0){
                Toast.makeText(this,"Kategori Belum dipilih",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch{
                val tugas= Tugas(0,judul,deskripsi,deadlineString,radio.text.toString(),"Belum Selesai")
                tugasViewModel.addTugas(tugas)
                finish()
            }
        }





    }
    private fun updateDateInView(tgl: TextView, str:Calendar) {
        val myFormat = "d MMMM yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        tgl.setText(sdf.format(str.getTime()))

        deadlineString = sdf.format(str.getTime())

    }
}