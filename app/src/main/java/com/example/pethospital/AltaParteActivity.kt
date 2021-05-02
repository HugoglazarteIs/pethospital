package com.example.pethospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.os.TokenWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pethospital.model.Animal
import com.example.pethospital.model.Doctor
import com.example.pethospital.model.Parte
import org.w3c.dom.Text

class AltaParteActivity : AppCompatActivity() {

    lateinit var id: TextView
    lateinit var buscar: Button
    lateinit var diagnostico: TextView
    lateinit var causas: TextView
    lateinit var medicamento: TextView
    lateinit var tratamiento: TextView
    lateinit var reposo: TextView
    lateinit var cargar: Button
    lateinit var limpiar: Button
    lateinit var volver: Button

    lateinit var nombreMascota: TextView
    lateinit var doctorMascota: TextView

    var listaDoc: ArrayList<Doctor> = ArrayList<Doctor>()
    var listaMascotas: ArrayList<Animal> = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_parte)

        inicializar()

        buscar.setOnClickListener {
            if(!listaMascotas.isEmpty()){
                if(id.text.toString().toInt() < listaMascotas.size){
                    mapping(listaMascotas.get(id.text.toString().toInt()))
                } else {
                    Toast.makeText(this, "No existe paciente con el ID.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "No hay pacientes por el momento", Toast.LENGTH_LONG).show()
            }
        }

        cargar.setOnClickListener {
            var p: Parte = guardarDatos()
            listaMascotas[id.text.toString().toInt()].parte = p
            clearElement()
            Toast.makeText(this, "El parte fue cargado.", Toast.LENGTH_LONG).show()
        }

        limpiar.setOnClickListener {
            clearElement()
        }

        volver.setOnClickListener {
            val intento: Intent = Intent(this, MainActivity::class.java)
            intento.putExtra("listaMascotas", listaMascotas)
            intento.putExtra("listaDoc", listaDoc)
            startActivity(intento)
        }

    }

    private fun inicializar(){
        id=findViewById(R.id.p_t_id)
        buscar=findViewById(R.id.p_b_buscar)
        diagnostico=findViewById(R.id.p_t_diagnostico)
        causas=findViewById(R.id.p_t_causas)
        medicamento=findViewById(R.id.p_t_medicamento)
        tratamiento=findViewById(R.id.p_t_tratamiento)
        reposo=findViewById(R.id.p_t_reposo)
        nombreMascota=findViewById(R.id.p_t_nombreMascota)
        doctorMascota=findViewById(R.id.p_t_doctorMascota)
        cargar=findViewById(R.id.p_b_cargar)
        limpiar=findViewById(R.id.p_b_limpiar)
        volver=findViewById(R.id.p_b_volver)
        inicializarListas()
    }

    private fun inicializarListas(){

        if(intent.getSerializableExtra("listaMascotas") != null){
            listaMascotas = intent.getSerializableExtra("listaMascotas") as ArrayList<Animal>
        }

        if(intent.getSerializableExtra("listaDoc") != null){
            listaDoc = intent.getSerializableExtra("listaDoc") as ArrayList<Doctor>
        }

    }

    private fun mapping(paciente: Animal){
        nombreMascota.setText("Nombre: " + paciente.nombre)
        doctorMascota.setText("Doctor: " + paciente.nombreDoc)
    }

    private fun guardarDatos(): Parte{
        return Parte(diagnostico.text.toString(),
            causas.text.toString(),
            medicamento.text.toString(),
            tratamiento.text.toString(),
            reposo.text.toString(),
            id.text.toString()
        )
    }

    private fun clearElement(){
        id.setText("")
        doctorMascota.setText("")
        nombreMascota.setText("")
        diagnostico.setText("")
        causas.setText("")
        medicamento.setText("")
        tratamiento.setText("")
        reposo.setText("")
    }

}