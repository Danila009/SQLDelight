package com.example.sqldelight.di

import android.app.Application
import com.example.sqldelight.data.PersonSource
import com.example.sqldelight.data.PersonSourceImpl
import com.plcoding.sqldelightcrashcourse.PersonDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideSqlDriver(
        application: Application
    ):SqlDriver = AndroidSqliteDriver(
        schema = PersonDatabase.Schema,
        context = application,
        name = "person.db"
    )

    @Provides
    @Singleton
    fun providerPersonDataSource(
        sgDriver: SqlDriver
    ):PersonSource = PersonSourceImpl(PersonDatabase(sgDriver))
}