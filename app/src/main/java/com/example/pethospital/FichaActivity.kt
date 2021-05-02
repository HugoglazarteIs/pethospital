package com.example.pethospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pethospital.model.Animal
import com.example.pethospital.model.Doctor
import com.example.pethospital.model.Parte
import org.w3c.dom.Text

class FichaActivity : AppCompatActivity() {

    lateinit var id: TextView
    lateinit var nombre: TextView
    lateinit var raza: TextView
    lateinit var causa: TextView
    lateinit var tipo: TextView
    lateinit var edad: TextView

    lateinit var doctor: TextView
    lateinit var diagnostico: TextView
    lateinit var causaDiagnostico: TextView
    lateinit var medicamente: TextView
    lateinit var tratamiento: TextView
    lateinit var reposo: TextView

    lateinit var buscar: Button
    lateinit var volver: Button


    var listaDoc: ArrayList<Doctor> = ArrayList<Doctor>()
    var listaMascotas: ArrayList<Animal> = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha)

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

        volver.setOnClickListener {
            val intento: Intent = Intent(this, MainActivity::class.java)
            intento.putExtra("listaMascotas", listaMascotas)
            intento.putExtra("listaDoc", listaDoc)
            startActivity(intento)
        }
    }

    private fun inicializar(){
        id=findViewById(R.id.f_t_id)
        nombre=findViewById(R.id.f_t_nombre)
        raza=findViewById(R.id.f_t_raza)
        causa=findViewById(R.id.f_t_causa)
        tipo=findViewById(R.id.f_t_tipo)
        edad=findViewById(R.id.f_t_edad)

        doctor=findViewById(R.id.f_t_doctor)
        diagnostico=findViewById(R.id.f_t_diagnostico)
        causaDiagnostico=findViewById(R.id.f_t_causas)
        medicamente=findViewById(R.id.f_t_medicamento)
        tratamiento=findViewById(R.id.f_t_tratamiento)
        reposo=findViewById(R.id.f_t_reposo)

        buscar=findViewById(R.id.f_b_buscar)
        volver=findViewById(R.id.f_b_volver)

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
        // Paciente
        nombre.setText(paciente.nombre)
        raza.setText(paciente.raza)
        causa.setText(paciente.causa)
        tipo.setText(paciente.tipo)
        edad.setText(paciente.edad)
        doctor.setText(paciente.nombreDoc)
        // Parte
        diagnostico.setText(paciente.parte.diagnostico)
        causaDiagnostico.setText(paciente.parte.causas)
        medicamente.setText(paciente.parte.medicamento)
        tratamiento.setText(paciente.parte.tratamiento)
        reposo.setText(paciente.parte.reposo)
    }
}