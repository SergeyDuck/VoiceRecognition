/*
 *  * Copyright (c)  2021  Shabinder Singh
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  *  You should have received a copy of the GNU General Public License
 *  *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.voicerecognition.di

import android.content.Context
import com.example.voicerecognition.di.module.setModels
import com.example.voicerecognition.store.di.providersModuleDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration


fun initKoin(
    enableNetworkLogs: Boolean = true,
    context: Context,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        if (enableNetworkLogs) printLogger(Level.INFO)
        androidContext(context)
        modules(listOf(
            providersModuleDataStore(ProvideFileImpl(context))
        ))
        appDeclaration()
    }



private fun KoinApplication.modules(vararg moduleLists: List<Module>): KoinApplication {
    return modules(moduleLists.toList().flatten())
}