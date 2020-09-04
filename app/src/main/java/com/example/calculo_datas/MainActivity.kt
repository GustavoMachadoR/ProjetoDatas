package com.example.calculo_datas

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculoData(componente: View){
        val nomeEvento = et_nome.text
        val dataInicio = et_data1.text.toString()
        val dataFim = et_data2.text.toString()

        val data1 = parseData(dataInicio)
        val data2 = parseData(dataFim)

        val dataFinal = ChronoUnit.DAYS.between(data1, data2)

        tv_resultado.setText("Faltam $dataFinal dias para o evento $nomeEvento!")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseData(date: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH)
        return LocalDate.parse(date, formatter)
    }
}