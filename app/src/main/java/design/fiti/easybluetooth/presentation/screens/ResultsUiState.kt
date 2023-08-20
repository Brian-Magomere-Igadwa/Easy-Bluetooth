package design.fiti.easybluetooth.presentation.screens

import design.fiti.easybluetooth.domain.BtDevice

data class ResultsUiState(
    val scannedDevices: List<BtDevice> = emptyList(),
    val pairedDevices: List<BtDevice> = emptyList()
)
