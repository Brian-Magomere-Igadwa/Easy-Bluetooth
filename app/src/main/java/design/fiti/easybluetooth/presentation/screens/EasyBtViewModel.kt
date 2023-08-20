package design.fiti.easybluetooth.presentation.screens

import androidx.lifecycle.ViewModel
import design.fiti.easybluetooth.domain.repository.BluetoothRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class EasyBtViewModel @Inject constructor(
    val btRepository: BluetoothRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultsUiState())
    val uiState = combine(
        btRepository.BluetoothController.pairedDevices,
        btRepository.BluetoothController.scannedDevices,
        _uiState
    ) { pairedDevices, scannedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices,
        )
    }
}