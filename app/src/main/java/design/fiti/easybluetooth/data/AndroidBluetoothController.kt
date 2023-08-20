package design.fiti.easybluetooth.data

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import design.fiti.easybluetooth.domain.BtController
import design.fiti.easybluetooth.domain.BtDevices
import design.fiti.easybluetooth.domain.BtDevicesDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("MissingPermission")
class AndroidBluetoothController(
    private val context: Context
) : BtController {
    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val _scannedDevices = MutableStateFlow<List<BtDevicesDomain>>(emptyList())
    override val scannedDevices: StateFlow<List<BtDevices>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices = MutableStateFlow<List<BtDevicesDomain>>(emptyList())
    override val pairedDevices: StateFlow<List<BtDevices>>
        get() = _pairedDevices.asStateFlow()

    private val foundDeviceReceiver = FoundDeviceReceiver { foundDevice ->
        _scannedDevices.update { existingDevices ->
            val newDevice = foundDevice.toBtDevicesDomain()
            if (newDevice in existingDevices) existingDevices else existingDevices + newDevice
        }
    }

    init {
        updatePairedDevices()
    }

    override fun startDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }
        //To tell android what reciever we're interested in and for what intent
        context.registerReceiver(foundDeviceReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))

        //no need to rescan for already paired devices so we get those first
        updatePairedDevices()
        bluetoothAdapter?.startDiscovery()

    }

    override fun stopDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }
        bluetoothAdapter?.cancelDiscovery()

    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
    }

    private fun updatePairedDevices() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return
        }
        bluetoothAdapter?.bondedDevices?.map { it.toBtDevicesDomain() }?.also { devices ->
            _pairedDevices.update {
                devices
            }
        }
    }

    private fun hasPermission(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}