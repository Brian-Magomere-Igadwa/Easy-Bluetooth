package design.fiti.easybluetooth.domain

typealias BtDevicesDomain = BtDevice

data class BtDevice(
    val deviceName: String,
    val macAddress: String
)
