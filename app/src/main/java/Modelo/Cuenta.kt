package Modelo

import java.io.Serializable

class Cuenta: Serializable {
    var id:Int=-1;
    var titulo: String=""
    var moneda: String=""
    var cantidad: Double=0.0
    var tipo: String= ""
    val lista:ArrayList<Movimiento> = ArrayList()

    constructor(titulo: String, moneda: String, cantidad: Double, tipo: String) {
        this.titulo = titulo
        this.moneda = moneda
        this.cantidad = cantidad
        this.tipo = tipo
    }

    constructor(titulo: String, moneda: String, cantidad: Double) {
        this.titulo = titulo
        this.moneda = moneda
        this.cantidad = cantidad
    }

    constructor()

    fun addMovimiento(mov:Movimiento){
        cantidad+=mov.cantidad
        mov.total=cantidad+mov.total
        lista.add(mov)
    }
}