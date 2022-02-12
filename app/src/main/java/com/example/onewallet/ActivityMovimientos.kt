package com.example.onewallet

import Modelo.Movimiento
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.onewallet.databinding.ActivityMovimientosBinding

class ActivityMovimientos : AppCompatActivity() {

    var listaMov:ArrayList<Movimiento> = ArrayList()

    var mov: Movimiento = Movimiento();

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMovimientosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listaMov= intent.getSerializableExtra("dato") as ArrayList<Movimiento>

        binding = ActivityMovimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_activity_movimientos)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        listaMov= intent.getSerializableExtra("dato") as ArrayList<Movimiento>

        val navController = findNavController(R.id.nav_host_fragment_content_activity_movimientos)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun getMyData(): ArrayList<Movimiento> {
        return listaMov;
    }

}