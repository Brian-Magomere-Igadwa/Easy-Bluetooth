package design.fiti.easybluetooth.data

import android.content.Context
import design.fiti.easybluetooth.domain.BtDevice
import kotlinx.coroutines.flow.StateFlow

class BluetoothRepositoryImpl(
    private val context: Context
) : BluetoothRepository {

    private val BluetoothController by lazy {
        AndroidBluetoothController(context)
    }

    override val scannedDevices: StateFlow<List<BtDevice>>
        get() = BluetoothController.scannedDevices
    override val pairedDevices: StateFlow<List<BtDevice>>
        get() = BluetoothController.pairedDevices

    override fun startDiscovery() {
        BluetoothController.startDiscovery()
    }

    override fun stopDiscovery() {
        BluetoothController.stopDiscovery()
    }

    override fun release() {
        BluetoothController.release()
    }
}