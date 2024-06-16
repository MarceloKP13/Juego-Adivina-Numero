package com.example.juego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var numeroAleatorio: Int = 0
    private var intentosRealizados: Int = 0
    private lateinit var textViewIntentos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rango: SeekBar = findViewById(R.id.barraRango)
        val valorRango: TextView = findViewById(R.id.textoRango)
        textViewIntentos = findViewById(R.id.numeroIntentos)

        rango.progress = 10
        valorRango.text = rango.progress.toString()

        rango.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                valorRango.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                generarNumeroAleatorio(seekBar.progress)
            }
        })
    }

    private fun generarNumeroAleatorio(maximo: Int) {
        val aleatorio = Random()
        numeroAleatorio = aleatorio.nextInt(maximo + 1)
        intentosRealizados = 0
        actualizarTextViewIntentos()
    }
    fun adivinar(v: View) {
        val adivinable: EditText = findViewById(R.id.textoNumero)
        val numeroAdivinable = adivinable.text.toString().toIntOrNull()

        if (numeroAdivinable == null) {
            Toast.makeText(this, "Por favor, ingresa un número válido", Toast.LENGTH_SHORT).show()
            return
        }
        intentosRealizados++
        actualizarTextViewIntentos()

        when {
            numeroAdivinable > numeroAleatorio -> {
                Toast.makeText(this, "El número es DEMASIADO ALTO al valor oculto", Toast.LENGTH_SHORT).show()
            }
            numeroAdivinable < numeroAleatorio -> {
                Toast.makeText(this, "El número es DEMASIADO BAJO al valor oculto", Toast.LENGTH_SHORT).show()
            }
            numeroAdivinable == numeroAleatorio -> {
                Toast.makeText(this, "El número es CORRECTO!! FELICIDADES!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun actualizarTextViewIntentos() {
        textViewIntentos.text = "Intentos: $intentosRealizados"
    }
}