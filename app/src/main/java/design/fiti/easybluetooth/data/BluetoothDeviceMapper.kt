package design.fiti.easybluetooth.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import design.fiti.easybluetooth.domain.BtDevicesDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBtDevicesDomain():BtDevicesDomain {
    return BtDevicesDomain(
        deviceName = name,
        macAddress = address
    )
}