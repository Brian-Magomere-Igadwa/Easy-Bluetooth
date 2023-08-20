package design.fiti.easybluetooth.data

import design.fiti.easybluetooth.domain.BtController
import design.fiti.easybluetooth.domain.BtDevice
import kotlinx.coroutines.flow.StateFlow

interface BluetoothRepository : BtController {
    override val scannedDevices: StateFlow<List<BtDevice>>

    override val pairedDevices: StateFlow<List<BtDevice>>

    override fun startDiscovery()

    override fun stopDiscovery()

    override fun release()
}