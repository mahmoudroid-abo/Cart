package com.mahmoudroid.cart.database

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import com.mahmoudroid.cart.model.CartItem

@Database(entities = [CartItem::class], version = 1)
abstract class CartDataBase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        private var roomInstance: CartDataBase? = null

        @SuppressLint("RestrictedApi")
        fun getDatabase(context: Context): CartDataBase? {
            if (roomInstance == null) {
                synchronized(CartDataBase::class.java) {
                    if (roomInstance == null) {
                        roomInstance = Room.databaseBuilder<CartDataBase>(
                            context.applicationContext,
                            CartDataBase::class.java,
                            "cart-database"
                        ).build()
                    }
                }
            }
            return roomInstance
        }
    }
}