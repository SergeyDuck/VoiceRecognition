package ru.axas.spechrecognizer.store

import androidx.datastore.core.DataStoreFactory
import ru.axas.spechrecognizer.common.models.local.LocalFileValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.axas.spechrecognizer.store.di.ProvideFile

class FileStoreApp(private val provideFile: ProvideFile) {

    private val dataStore = DataStoreFactory.create(
        produceFile = { provideFile.getFileMain() },
        serializer = LocalFileSerializer(provideFile.getFileCrypto())
    )



    fun getLocalData(): LocalFileValue = runBlocking {
        dataStore.data.first()
    }

    fun updateLocalData(
        onUpdate: (LocalFileValue) -> LocalFileValue
    ) = runBlocking {
        dataStore.updateData { onUpdate.invoke(it) }
    }

    fun deleteAppSettings() {
        updateLocalData { LocalFileValue() }
    }
}
