package com.example.ejemplonetworkingtest.di

import com.example.ejemplonetworkingtest.data.PersonajeRemoteDataSource
import com.example.ejemplonetworkingtest.model.PersonajeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class) // proporcionará esa dependencia en los componentes Activity o inferior.
abstract class PersonajeDataSourceModule {

    @Binds // indica a Hilt que implementación usar cuando necesite proporcionar una instancia
    @ViewModelScoped // define el alcance de vinculación a un componente. Se queda en memoria hasta que se destruye ese componente.
    abstract fun bindPersonajeDataSource(
        dataSourceImpl: PersonajeRemoteDataSource
    ): PersonajeDataSource
}