package com.example.calculo_datas

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculoData(componente: View){
        val nomeEvento = et_nome.text.toString()
        val dataInicio = et_data1.text.toString()
        val dataFim = et_data2.text.toString()

        if (nomeEvento.isNullOrEmpty() || dataInicio.isNullOrEmpty() || dataFim.isNullOrEmpty()){
            tv_resultado.setText("Os dados não podem ser nulos!")
            tv_resultado.setTextColor(Color.RED)
            return
        }

        if(dataInicio.length != 8 || dataFim.length != 8){
            tv_resultado.setText("A data deve possuir 8 caracteres!")
            tv_resultado.setTextColor(Color.RED)
            return
        }

        val data1 = parseData(dataInicio)
        val data2 = parseData(dataFim)

        if(data1 == null || data2 == null){
            tv_resultado.setText("Digite uma data válida!")
            tv_resultado.setTextColor(Color.RED)
            return
        }

        val dataFinal = ChronoUnit.DAYS.between(data1, data2)

        if(dataFinal < 0){
            tv_resultado.setText("Digite um período válido!")
            tv_resultado.setTextColor(Color.RED)
            return
        }

        tv_resultado.setTextColor(Color.BLUE)
        tv_resultado.setText("Faltam $dataFinal dias para o evento $nomeEvento!")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseData(date: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH)
        val formatedDate: LocalDate
        try {
            formatedDate = LocalDate.parse(date, formatter)
        } catch (e: DateTimeParseException){
            return null
        }
        return formatedDate
    }
}