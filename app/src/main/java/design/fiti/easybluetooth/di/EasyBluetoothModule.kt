package design.fiti.easybluetooth.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import design.fiti.easybluetooth.data.repository.BluetoothRepositoryImpl
import design.fiti.easybluetooth.domain.repository.BluetoothRepository

@Module
@InstallIn(ViewModelComponent::class)
object EasyBluetoothModule {

    @Provides
    fun providesBluetoothRepository(
        btRepository: BluetoothRepositoryImpl,
    ): BluetoothRepository {
        return btRepository
    }

}