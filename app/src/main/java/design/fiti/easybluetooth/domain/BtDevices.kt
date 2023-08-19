package design.fiti.easybluetooth.domain

typealias BtDevicesDomain = BtDevices

data class BtDevices(
    val deviceName: String,
    val macAddress: String
)
