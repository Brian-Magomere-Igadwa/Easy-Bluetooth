package design.fiti.easybluetooth.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import design.fiti.easybluetooth.data.AndroidBluetoothController
import design.fiti.easybluetooth.domain.BtController
import design.fiti.easybluetooth.domain.repository.BluetoothRepository
import design.fiti.easybluetooth.domain.BtDevice
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BluetoothRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BluetoothRepository {

    override val BluetoothController: BtController by lazy {
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