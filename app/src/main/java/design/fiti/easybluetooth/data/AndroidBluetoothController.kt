package design.fiti.easybluetooth.data

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

import design.fiti.easybluetooth.domain.BtController
import design.fiti.easybluetooth.domain.BtDevice
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
    override val scannedDevices: StateFlow<List<BtDevice>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices = MutableStateFlow<List<BtDevicesDomain>>(emptyList())
    override val pairedDevices: StateFlow<List<BtDevice>>
        get() = _pairedDevices.asStateFlow()

    private val foundDeviceReceiver = FoundDeviceReceiver { foundDevice ->
        _scannedDevices.update { existingDevices ->
            val newDevice = foundDevice.toBtDevicesDomain()
            existingDevices + newDevice
//            if (newDevice in existingDevices) existingDevices else existingDevices + newDevice
        }
    }

    init {
        updatePairedDevices()
    }

    override fun startDiscovery() {

        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }

        context.registerReceiver(foundDeviceReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))

        updatePairedDevices()
        bluetoothAdapter?.startDiscovery()

    }

    override fun stopDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH)) {
            return
        }
        bluetoothAdapter?.cancelDiscovery()
    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
    }

    private fun updatePairedDevices() {
        if (!hasPermission(Manifest.permission.BLUETOOTH)) {

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