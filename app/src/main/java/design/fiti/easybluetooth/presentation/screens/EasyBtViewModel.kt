package design.fiti.easybluetooth.presentation.screens

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import design.fiti.easybluetooth.data.BluetoothRepository
import design.fiti.easybluetooth.presentation.EasyApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update

class EasyBtViewModel(
    val btRepository: BluetoothRepository
) : ViewModel() {


    private var _uiState = MutableStateFlow(
        ResultsUiState()
    )
//    val uiState = _uiState.asStateFlow()

    val uiState = combine(
        btRepository.pairedDevices,
        btRepository.scannedDevices,
        _uiState
    ) { pairedDevices, scannedDevices, uiState ->
        uiState.copy(scannedDevices = scannedDevices, pairedDevices = pairedDevices)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), _uiState.value)

    private fun startDiscovery() {
        btRepository.startDiscovery()
    }

    private fun stopDiscovery() {
        btRepository.stopDiscovery()
    }

    private fun release() {
        btRepository.release()
    }

    fun scanForDevices() {
        startDiscovery()
    }

    fun stopScan() {
        stopDiscovery()
        release()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EasyApplication)
                val btRepository = application.container.bluetoothRepository
                EasyBtViewModel(btRepository = btRepository)
            }
        }
    }

}