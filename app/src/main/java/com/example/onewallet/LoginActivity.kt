package com.example.onewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException

class LoginActivity : AppCompatActivity() {
    val ruta = "https://impartable-threader.000webhostapp.com/tControla.php?tag="
    var cola: RequestQueue?= null;
    var login:Boolean=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cola= Volley.newRequestQueue(this)

    }

    public fun login(v: View) {
        if(textUser.text.toString().isNotEmpty() && textPass.text.toString().isNotEmpty()){
            var user =textUser.text.toString()
            var pass = textPass.text.toString();
            var enlace = ruta +"login&cod="+user+"&pas="+pass
            val req= JsonObjectRequest(Request.Method.GET, enlace,null, Response.Listener{
                    response ->
                try {
                    val res = response.getString("dato").toString()
                    if(res!= ""){
                        Toast.makeText(this,"Logeado Correcto", Toast.LENGTH_SHORT).show()
                        val it: Intent = Intent(this, MainActivity::class.java)
                        startActivity(it);
                    }else {
                        Toast.makeText(this,"Logeado Incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }catch (ex: JSONException){
                    ex.printStackTrace()
                }
            }, Response.ErrorListener {
                    error ->
                Toast.makeText(this,"error 1 $error", Toast.LENGTH_SHORT).show()
            })
            cola?.add(req)
        }
    }

    public fun register(v: View) {

        if(!textUser.text.toString().isEmpty() || !textPass.text.toString().isEmpty()){
            var user =textUser.text.toString()
            var pass = textPass.text.toString();

                var enlace = ruta +"register&cod="+user+"&pas="+pass
            val req= JsonObjectRequest(Request.Method.GET, enlace,null, Response.Listener{
                    response ->
                try {
                    val res = response.getString("dato")
                    if(res.toString()!=""){
                        login=true

                    }else {
                        Toast.makeText(this,"Registro Correcto", Toast.LENGTH_SHORT).show()
                    }
                }catch (ex: JSONException){
                    ex.printStackTrace()
                }
            }, Response.ErrorListener {
                    error ->
                Toast.makeText(this,"error 1 $error", Toast.LENGTH_SHORT).show()
            })
            cola?.add(req)
        }
    }
}