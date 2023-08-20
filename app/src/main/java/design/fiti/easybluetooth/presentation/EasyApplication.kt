package design.fiti.easybluetooth.presentation

import android.app.Application
import design.fiti.easybluetooth.data.AppContainer
import design.fiti.easybluetooth.data.AppDataContainer

class EasyApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}