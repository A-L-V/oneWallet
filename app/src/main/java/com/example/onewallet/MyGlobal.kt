package com.example.onewallet

import Modelo.Cuenta
import Modelo.Movimiento
import android.app.Application


class MyGlobal: Application() {
    //mantener en memoria los atributos

    companion object{
        fun inicializar() {
            listaPlato.add(Cuenta("cuenta1,", "PEN", 15.2))
            listaPlato.add(Cuenta("cuenta2,", "PEN", 15.2))
            listaPlato.add(Cuenta("cuenta3,", "PEN", 15.2))
            listaPlato.add(Cuenta("cuenta4,", "PEN", 15.2))

            listaPlato.get(0).addMovimiento(Movimiento("Mov 1", "Ingreso", "sueldo", 100.0, listaPlato.get(0)))
            listaPlato.get(0).addMovimiento(Movimiento("Mov 2", "Ingreso", "sueldo", 100.0, listaPlato.get(0)))
            listaPlato.get(0).addMovimiento(Movimiento("Mov 3", "Ingreso", "sueldo", 100.0, listaPlato.get(0)))
            listaPlato.get(0).addMovimiento(Movimiento("Mov 4", "Ingreso", "sueldo", 100.0, listaPlato.get(0)))
        }

        var listaPlato:ArrayList<Cuenta> = ArrayList();
    }

}