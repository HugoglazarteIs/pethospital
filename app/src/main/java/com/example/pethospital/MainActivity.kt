package com.example.pethospital

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.pethospital.model.Animal
import com.example.pethospital.model.Doctor
import com.example.pethospital.model.Parte

class MainActivity : AppCompatActivity() {

    lateinit var verFicha: Button
    lateinit var altaMascota: Button
    lateinit var altaParte: Button

    var listaMascotas: ArrayList<Animal> = ArrayList<Animal>()
    var listaDoc: ArrayList<Doctor> = ArrayList<Doctor>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializar()

        verFicha.setOnClickListener{
            goToActivity(this, FichaActivity::class.java)
        }

        altaMascota.setOnClickListener {

            goToActivity(this, AltaMascotaActivity::class.java)
        }

        altaParte.setOnClickListener {
            goToActivity(this, AltaParteActivity::class.java)
        }


    }

    private fun inicializar(){
        verFicha=findViewById(R.id.i_b_ver)
        altaMascota=findViewById(R.id.i_b_mascota)
        altaParte=findViewById(R.id.i_b_ficha)
        inicializarListas()
    }

    private fun inicializarListas(){

        if(intent.getSerializableExtra("listaMascotas") != null){
            listaMascotas = intent.getSerializableExtra("listaMascotas") as ArrayList<Animal>
        }

        if(intent.getSerializableExtra("listaDoc") != null){
            listaDoc = intent.getSerializableExtra("listaDoc") as ArrayList<Doctor>
        } else {
            listaDoc.add(Doctor("Juan", 0))
            listaDoc.add(Doctor("Pedro", 0))
        }

    }

    fun <T>goToActivity(context: Context, nuevaVista: Class<T>){
        val intento: Intent = Intent(context, nuevaVista)
        intento.putExtra("listaMascotas", listaMascotas)
        intento.putExtra("listaDoc", listaDoc)
        startActivity(intento)
    }
}