package design.fiti.easybluetooth.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import design.fiti.easybluetooth.domain.repository.BluetoothRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EasyBtViewModel @Inject constructor(
    private val btRepository: BluetoothRepository
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
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),_uiState.value)

    fun scanForDevices() {
        btRepository.BluetoothController.startDiscovery()
    }


}