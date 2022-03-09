package Modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf
import com.example.onewallet.MyGlobal

class DataHelper(context:Context): SQLiteOpenHelper(context, DATABASE_NAME,null , VERSION) {
    companion object {
        private val DATABASE_NAME = "fasdf"
        private val VERSION = 1
        private val tablaNameMov = "Movimiento"
        private val tablaNameCue = "Cuenta";
    }

    val cuenta =
        "create table Cuenta(id integer primary key autoincrement, titulo text, moneda text, cantidad real, tipo text)"
    val movimiento =
        "create table Movimiento (id integer primary key autoincrement,titulo text,tipo text,nota text,cantidad real,total real, cuentaid int, FOREIGN KEY(cuentaid) REFERENCES Cuenta(id))"

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db?.execSQL(cuenta)
            db?.execSQL(movimiento)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        Log.w("cambia", "actualizando antigua " + p1 + " con nueva " + p2)
        db?.execSQL("drop table if exists Movimiento")
        db?.execSQL("drop table if exists Cuenta")
        onCreate(db)
        db?.execSQL(cuenta)
        db?.execSQL(movimiento)
    }
//los metodos de usuario

    fun insertCuenta(cuenta: Cuenta): Long {
        val db = this.writableDatabase //escribir en la bdd
        val ct = contentValuesOf()
        ct.put("titulo", cuenta.titulo)
        ct.put("moneda", cuenta.moneda)
        ct.put("cantidad", cuenta.cantidad)
        ct.put("tipo", cuenta.tipo)
        val resp = db.insert(tablaNameCue, null, ct)
        return resp;
    }

    fun updateCuenta(cuenta: Cuenta): Int {
        val db = this.writableDatabase //escribir en la bdd
        val ct = contentValuesOf()
        //  val condicion:Array<String> =arrayOf<String>(p.nropre.toString())
        ct.put("titulo", cuenta.titulo)
        ct.put("moneda", cuenta.moneda)
        ct.put("cantidad", cuenta.cantidad)
        ct.put("tipo", cuenta.tipo)
        //  val resp=db.update(TABLA_NAME,ct,"id=?",condicion)
        val resp = db.update(tablaNameCue, ct, "id=" + cuenta.id, null)
        return resp;
    }

    fun deleteCuenta(id: Int): Int {
        val db = this.writableDatabase //escribir en la bdd
        val resp = db.delete("Cuenta", "id=" + id, null)
        return resp
    }

    fun listCuenta(): List<Cuenta> {
        val lista: ArrayList<Cuenta> = ArrayList()
        val sql = "Select * from Cuenta"
        var db = this.readableDatabase
        var cur = db.rawQuery(sql, null)
        while (cur.moveToNext()) {
            var cuenta = Cuenta()
            cuenta.id = cur.getInt(0)
            cuenta.titulo = cur.getString(1)
            cuenta.moneda = cur.getString(2)
            cuenta.cantidad = cur.getDouble(3)
            cuenta.tipo = cur.getString(4)
            val sql2 = "Select * from Movimiento where cuentaid=" + cuenta.id;
            var cur2 = db.rawQuery(sql2, null)
            while (cur2.moveToNext()) {
                var mov = Movimiento()
                mov.id = cur2.getInt(0)
                mov.titulo = cur2.getString(1)
                mov.tipo = cur2.getString(2)
                mov.nota = cur2.getString(3)
                mov.cantidad = cur2.getDouble(4)
                mov.total = cur2.getDouble(5)
                mov.cuenta = Cuenta()
                mov.cuenta!!.id = cur2.getInt(6)
                cuenta.lista.add(mov)
            }
            lista.add(cuenta)
        }
        MyGlobal.listaCuenta = lista;
        return lista;
    }


    fun insertarMovimiento(mov: Movimiento):Long{
        val db=this.writableDatabase
        val ct= contentValuesOf()
        ct.put("titulo",mov.titulo)
        ct.put("tipo", mov.tipo)
        ct.put("nota", mov.nota)
        ct.put("cantidad", mov.cantidad)
        ct.put("cuentaid", mov.cuenta!!.id)
        ct.put("total", mov.total)
        for(cuenta in MyGlobal.listaCuenta){
            if(cuenta.id!= mov.cuenta!!.id){
                cuenta.addMovimiento(mov)
            }
        }
        val resp=db.insert(tablaNameMov,null,ct)

        mov.cuenta!!.cantidad=mov.total
        updateCuenta(mov.cuenta!!)
        return resp;
    }

    fun listaMovimiento(id: Int): List<Movimiento> {
        val lista: ArrayList<Movimiento> = ArrayList()
        var db = this.readableDatabase
        val sql = "Select * from Movimiento where cuentaid=" + id;
        var cur = db.rawQuery(sql, null)
        while (cur.moveToNext()) {
            var mov = Movimiento()
            mov.id = cur.getInt(0)
            mov.titulo = cur.getString(1)
            mov.tipo = cur.getString(2)
            mov.nota = cur.getString(3)
            mov.cantidad = cur.getDouble(4)
            mov.total = cur.getDouble(5)
            mov.cuenta = Cuenta()
            mov.cuenta!!.id = cur.getInt(6)

            lista.add(mov)
        }
    return lista;
    }
    fun updateMovimiento(mov: Movimiento): String {
        val db = this.writableDatabase //escribir en la bdd
        val ct = contentValuesOf()
        ct.put("titulo", mov.titulo)
        ct.put("tipo", mov.tipo)
        ct.put("nota", mov.nota)
        ct.put("cantidad", ""+mov.cantidad)
        val resp: String = ""+mov.cantidad + " aver modificado"+db.update(tablaNameMov, ct, "id=" + mov.id, null)
        return resp;
    }

    fun deleteMovimiento(id: Int): Int {
        val db = this.writableDatabase //escribir en la bdd
        val resp = db.delete("Movimiento", "id=" + id, null)
        return resp
    }
}