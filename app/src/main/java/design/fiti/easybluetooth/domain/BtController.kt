package design.fiti.easybluetooth.domain

import kotlinx.coroutines.flow.StateFlow

interface BtController {
    val scannedDevices: StateFlow<List<BtDevice>>
    val pairedDevices: StateFlow<List<BtDevice>>

    fun startDiscovery()
    fun stopDiscovery()
    fun release()

}