package ru.axas.contacts.main

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import ru.axas.contacts.common.BuildConfig
import ru.axas.contacts.di.module.setModels
import ru.axas.contacts.common.models.logger.LogCustom
import ru.axas.contacts.common.models.res.TextApp
import ru.axas.contacts.di.initKoin

class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        TextApp.initDebugAndVersion(BuildConfig.DEBUG, BuildConfig.VERSION_NAME)
        LogCustom.init(BuildConfig.DEBUG, BuildConfig.VERSION_NAME)
        initKoin(
            enableNetworkLogs = BuildConfig.DEBUG,
            context = this@App
        ) {
            modules(setModels)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }
}