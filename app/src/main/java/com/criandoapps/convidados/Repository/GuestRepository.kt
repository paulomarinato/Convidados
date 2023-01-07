package com.criandoapps.convidados.Repository

import android.content.ContentValues
import android.content.Context
import com.criandoapps.convidados.Constants.DataBaseConstants
import com.criandoapps.convidados.Model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: java.lang.Exception) {
            false

        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selections = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())


            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selections, args)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun delete(id: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selections = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())


            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selections, args)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun getALL(): List<GuestModel> {
        val db = guestDataBase.readableDatabase

        val list = mutableListOf<GuestModel>()
        try {
            val selection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, selection,
                null, null, null, null, null,
            )
            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence ==1))
                }
            }
            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list
    }

    fun getPresent(): List<GuestModel> {
        val db = guestDataBase.readableDatabase

        val list = mutableListOf<GuestModel>()
        try {
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val cursor = db.rawQuery("SELECT id, name,  presence FROM Guest WHERE presence = 1", null)
            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence ==1))
                }
            }
            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val db = guestDataBase.readableDatabase

        val list = mutableListOf<GuestModel>()
        try {
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val cursor = db.rawQuery("SELECT id, name,  presence FROM Guest WHERE presence = 0", null)
            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence ==1))
                }
            }
            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list
    }
}