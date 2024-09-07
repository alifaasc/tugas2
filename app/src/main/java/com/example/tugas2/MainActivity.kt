package com.example.tugas2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var rg_operator: RadioGroup
    private lateinit var btn_hitung: Button
    private lateinit var et_nilai1: EditText
    private lateinit var et_nilai2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rg_operator = findViewById(R.id.rg_operator)
        btn_hitung = findViewById(R.id.btn_Hitung)
        et_nilai1 = findViewById(R.id.et_angka1)
        et_nilai2 = findViewById(R.id.et_angka2)



        btn_hitung.setOnClickListener {
            val nilai1String = et_nilai1.text.toString()
            val nilai2String = et_nilai2.text.toString()

            if(nilai1String.isEmpty()){
                et_nilai1.error = "Angka 1 Wajib diisi"
                return@setOnClickListener
            }

            if(nilai2String.isEmpty()){
                et_nilai2.error = "Angka 2 Wajib diisi"
                return@setOnClickListener
            }

            val nilai1 = nilai1String.toInt()
            val nilai2 = nilai2String.toInt()

            val hasil = do_hitung_hasil(nilai1, nilai2)

            if (hasil != null) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("RESULT", hasil)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun do_hitung_hasil(nilai1: Int,nilai2: Int): String? {
        val selectedOperatorId = rg_operator.checkedRadioButtonId
        if (selectedOperatorId == -1) {
            Toast.makeText(this, "Pilih Operator", Toast.LENGTH_SHORT).show()
            return null
        }

        val hitunghasil = when (selectedOperatorId) {
            R.id.rb_plus -> nilai1 + nilai2
            R.id.rb_minus -> nilai1 + nilai2
            R.id.rb_multiply -> nilai1 + nilai2
            R.id.rb_divide -> {
                if (nilai2 == 0) {
                    Toast.makeText(this, "Tidak dapat membagi dengan 0", Toast.LENGTH_SHORT).show()
                    return null
                } else {
                    nilai1 / nilai2
                }
            }
            else -> {
                Toast.makeText(this, "Operator tidak valid", Toast.LENGTH_SHORT).show()
                return null
            }
        }
        return hitunghasil.toString()
    }
}