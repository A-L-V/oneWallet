package com.example.onewallet

import Modelo.Cuenta
import Modelo.Movimiento
import android.app.Application


class MyGlobal: Application() {
    //mantener en memoria los atributos
    companion object{
        var listaCuenta:ArrayList<Cuenta> = ArrayList();
    }

}