package com.clarkelamothe.intermedia.di

import com.clarkelamothe.intermedia.data.characters.CharactersDataSource
import com.clarkelamothe.intermedia.data.characters.CharactersService
import com.clarkelamothe.intermedia.data.events.EventsDataSource
import com.clarkelamothe.intermedia.data.events.EventsService
import com.clarkelamothe.intermedia.utils.ApiDetails.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @ApiMarvel
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideCharactersService(@ApiMarvel retrofit: Retrofit): CharactersService =
        retrofit.create(CharactersService::class.java)

    @Provides
    fun provideCharactersDataSource(characterService: CharactersService): CharactersDataSource {
        return CharactersDataSource(characterService)
    }

    @Provides
    fun provideEventsService(@ApiMarvel retrofit: Retrofit): EventsService =
        retrofit.create(EventsService::class.java)

    @Provides
    fun provideEventsDataSource(eventsService: EventsService): EventsDataSource {
        return EventsDataSource(eventsService)
    }
}