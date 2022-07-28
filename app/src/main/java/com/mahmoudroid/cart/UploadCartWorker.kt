package com.mahmoudroid.cart

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mahmoudroid.cart.database.CartDao
import com.mahmoudroid.cart.database.CartDataBase
import com.mahmoudroid.cart.viewmodel.CartViewModel

class UploadCartWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters), ViewModelStoreOwner {

    private lateinit var cartViewModel: CartViewModel

    override suspend fun doWork(): Result {
        cartViewModel.deleteAllItem()
        return Result.success()
    }

    override fun getViewModelStore(): ViewModelStore {
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        return viewModelStore
    }

}