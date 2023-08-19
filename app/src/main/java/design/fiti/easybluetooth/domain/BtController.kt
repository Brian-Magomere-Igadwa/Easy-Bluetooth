package design.fiti.easybluetooth.domain

import kotlinx.coroutines.flow.StateFlow

interface BtController {
    val scannedDevices: StateFlow<List<BtDevices>>
    val pairedDevices: StateFlow<List<BtDevices>>

    fun startDiscovery()
    fun stopDiscovery()
    fun release()

}