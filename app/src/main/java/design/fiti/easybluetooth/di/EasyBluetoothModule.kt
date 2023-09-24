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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EasyBluetoothModule {

    @Provides
    @Singleton
    fun providesBluetoothRepository(
        btRepository: BluetoothRepositoryImpl,
    ): BluetoothRepository {
        return btRepository
    }

}