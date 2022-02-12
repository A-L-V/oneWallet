package Modelo
import java.io.Serializable
class Movimiento :Serializable{
    var titulo: String=""
    var tipo: String=""
    var nota: String=""
    var cantidad: Double=0.0
    var total: Double= 0.0
    var cuenta: Cuenta? = null

    constructor(
        titulo: String,
        tipo: String,
        nota: String,
        cantidad: Double,
        cuenta: Cuenta?
    ) {
        this.titulo = titulo
        this.tipo = tipo
        this.nota = nota
        this.cantidad = cantidad
        this.cuenta = cuenta
    }


    constructor()


}