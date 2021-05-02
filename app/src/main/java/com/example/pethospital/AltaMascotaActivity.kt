package com.example.pethospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.pethospital.model.Animal
import com.example.pethospital.model.Doctor
import com.example.pethospital.model.Parte

class AltaMascotaActivity : AppCompatActivity() {

    lateinit var nombre: EditText
    lateinit var tipo: RadioGroup
    lateinit var tipoSel: RadioButton
    lateinit var raza: EditText
    lateinit var edad: EditText
    lateinit var causa: EditText
    lateinit var guardar: Button
    lateinit var volver: Button
    lateinit var limpiar: Button
    lateinit var doctorSel: Spinner

    var listaDoc: ArrayList<Doctor> = ArrayList<Doctor>()
    var listaMascotas: ArrayList<Animal> = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_mascota)

        inicializar()

        guardar.setOnClickListener {

            if(listaMascotas.size < 5){
                val a:Animal = guardarDatos()
                val d: Doctor = listaDoc[capturoIdDoc(a.nombreDoc)]

                if(a.tipo.equals("Perro")){
                    if(d.nombre.equals("Pedro")){
                        if((d.pacientes < 3)){
                            Toast.makeText(this, "El paciente se derivo a Pedro", Toast.LENGTH_LONG).show()
                            listaMascotas.add(a)
                            listaDoc[capturoIdDoc(a.nombreDoc)].pacientes++
                        } else {
                            Toast.makeText(this, "Pedro alcanzo su limite de pacientes, El paciente se derivo a Juan", Toast.LENGTH_LONG).show()
                            a.nombreDoc = "Juan"
                            listaMascotas.add(a)
                            listaDoc[capturoIdDoc(a.nombreDoc)].pacientes++
                        }

                    } else {
                        Toast.makeText(this, "El paciente se derivo a Juan", Toast.LENGTH_LONG).show()
                        listaMascotas.add(a)
                        listaDoc[capturoIdDoc(a.nombreDoc)].pacientes++
                    }
                } else {
                    Toast.makeText(this, "El paciente se derivo a Juan", Toast.LENGTH_LONG).show()
                    listaMascotas.add(a)
                    listaDoc[capturoIdDoc(a.nombreDoc)].pacientes++
                }

                clearElement()

            } else {
                Toast.makeText(this, "Se alcanzo el limite diario de lugares disponibles", Toast.LENGTH_LONG).show()
            }


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

        guardar=findViewById(R.id.m_b_cargar)
        volver=findViewById(R.id.m_b_volver)
        limpiar=findViewById(R.id.m_b_limpiar)

        nombre=findViewById(R.id.m_nombre)
        tipo=findViewById(R.id.m_rg_tipo)
        raza=findViewById(R.id.m_raza)
        edad=findViewById(R.id.m_edad)
        causa=findViewById(R.id.m_causa)

        listaDoc = intent.getSerializableExtra("listaDoc") as ArrayList<Doctor>

        doctorSel = findViewById(R.id.m_sp_doctor)
        inicializarSpinner()

    }

    private fun inicializarSpinner(){

        var adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getDocNames())
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        doctorSel.adapter=adaptador
    }

    private fun getDocNames(): ArrayList<String> {
        var listDocNames: ArrayList<String> = ArrayList<String>()
        listaDoc.forEach{ item -> listDocNames.add(item.nombre) }
        return listDocNames
    }

    private fun guardarDatos(): Animal{
        return Animal(nombre.text.toString(),
            capturoSeleccionRadioButton().text.toString(),
            raza.text.toString(),
            edad.text.toString(),
            causa.text.toString(),
            doctorSel.selectedItem.toString(),
            Parte("","","","","","")
        )
    }

    private fun capturoIdDoc(nombreDoc: String): Int {
        var d: Doctor  = listaDoc.find{ i -> i.nombre.equals(nombreDoc)}!!
        return listaDoc.indexOf(d).toInt()
    }

    private fun capturoSeleccionRadioButton(): RadioButton {
        tipoSel=findViewById(tipo.checkedRadioButtonId)
        return tipoSel
    }

    private fun clearElement(){
        nombre.setText("")
        edad.setText("")
        raza.setText("")
        causa.setText("")
    }
}