package com.marinato.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.marinato.convidados.dataBase.Constants
import com.marinato.convidados.dataBase.GuestDataBase
import com.marinato.convidados.model.GuestModel

class GuestRepostory private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repostory: GuestRepostory

        fun getInstance(context: Context): GuestRepostory {
            if (!Companion::repostory.isInitialized) {
                repostory = GuestRepostory(context)
            }
            return repostory
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(Constants.GUEST.COLUMNS.NAME, guest.name)
            values.put(Constants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(Constants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(Constants.GUEST.COLUMNS.NAME, guest.name)
            values.put(Constants.GUEST.COLUMNS.PRESENCE, presence)


            val selections = Constants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(guest.id.toString())

            db.update(Constants.GUEST.TABLE_NAME, values, selections, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete (id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selections = Constants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(id.toString())

            db.delete(Constants.GUEST.TABLE_NAME, selections, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null
        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                Constants.GUEST.COLUMNS.ID,
                Constants.GUEST.COLUMNS.NAME,
                Constants.GUEST.COLUMNS.PRESENCE,
            )

            val selections = Constants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                Constants.GUEST.TABLE_NAME, projection,
                selections, args,
                null, null, null
            )

            if (cursor != null &&  cursor.count >= 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.NAME))

                    val presence =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.PRESENCE))

                    guest = GuestModel( id, name, presence == 1 )

                }
            }

            cursor.close()
        }catch (e: Exception){
            return guest
        }
        return guest
    }

    fun getAll() :List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                Constants.GUEST.COLUMNS.ID,
                Constants.GUEST.COLUMNS.NAME,
                Constants.GUEST.COLUMNS.PRESENCE,
            )

            val cursor = db.query(
                Constants.GUEST.TABLE_NAME, projection,
                null, null,
                null, null, null
            )

            if (cursor != null &&  cursor.count >= 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.ID))

                    val name =
                        cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.NAME))

                    val presence =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel( id, name, presence == 1 )
                }
            }
            cursor.close()
        }catch (e: Exception){
            return list
        }
        return list
    }

    fun getPresent() :List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, " +
                    "presence FROM Guest WHERE presence = 1", null)

            if (cursor != null &&  cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.ID))

                    val name =
                        cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.NAME))

                    val presence =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel( id, name, presence == 1 )
                }
            }
            cursor.close()
        }catch (e: Exception){
            return list
        }
        return list
    }

    fun getAbsent() :List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, " +
                    "presence FROM Guest WHERE presence = 0", null)

            if (cursor != null &&  cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.ID))

                    val name =
                        cursor.getString(cursor.getColumnIndex(Constants.GUEST.COLUMNS.NAME))

                    val presence =
                        cursor.getInt(cursor.getColumnIndex(Constants.GUEST.COLUMNS.PRESENCE))

                    val guest = GuestModel( id, name, presence == 1 )
                }
            }
            cursor.close()
        }catch (e: Exception){
            return list
        }
        return list
    }
}
