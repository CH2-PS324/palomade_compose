package com.example.palomadeapps

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.screen.home.HomeViewModel
import com.example.palomadeapps.ui.screen.login.LoginViewModel
import com.example.palomadeapps.ui.screen.register.RegisterViewModel
import com.example.palomadeapps.views.main.MainViewModel

class ViewModelFactory(private val repository: PaloRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(InventoryViewModel::class.java) -> {
//                InventoryViewModel(repository) as T
//            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
//                AddViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
//                AccountViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(InvDetailViewModel::class.java) -> {
//                InvDetailViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(TransactionsViewModel::class.java) -> {
//                TransactionsViewModel(repository) as T
//            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
//    companion object{
//        fun getInstance(context: Context): ViewModelFactory = synchronized(this) {
//            ViewModelFactory(Injection.provideRepository(context))
//        }
//    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}