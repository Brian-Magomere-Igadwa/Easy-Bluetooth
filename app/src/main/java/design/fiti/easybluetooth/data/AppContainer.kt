package design.fiti.easybluetooth.data

import android.content.Context

interface AppContainer {
    val bluetoothRepository: BluetoothRepository
}

class AppDataContainer(
    private val context: Context
) : AppContainer {
    override val bluetoothRepository: BluetoothRepository by lazy {
        BluetoothRepositoryImpl(context = context)
    }
}