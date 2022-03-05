package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bValidarRed = findViewById<Button>(R.id.bValidarRed)
        val bSolicitudHTTP = findViewById<Button>(R.id.bSolicitudHTTP)

        bValidarRed.setOnClickListener{
            //Código para validar red
            if(Network.isNetworkAvailable( this)){
                Toast.makeText(this,"Si hay red!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No hay una conexión a internet",Toast.LENGTH_LONG).show()
            }
        }

        bSolicitudHTTP.setOnClickListener{
            //Código para validar red
            if(Network.isNetworkAvailable( this)){
                Log.d("bSolicitudOnClick", descargarDatos( "http://www.google.com"))
            }else{
                Toast.makeText(this,"No hay una conexión a internet",Toast.LENGTH_LONG).show()
            }
        }
    }

        @Throws(IOException::class)
        private fun descargarDatos(url:String):String{

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var inputStream: InputStream? = null

        try {
            val url = URL(url)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            return  inputStream.bufferedReader().use {
                it.readText()
            }
        }finally {
            if (inputStream != null) {
                inputStream.close()
            }
          }
        }
}