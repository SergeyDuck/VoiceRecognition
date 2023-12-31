@file:OptIn(kotlinx.coroutines.DelicateCoroutinesApi::class)

package ru.axas.contacts.common.memory

import ru.axas.contacts.common.models.local.DataSingleLive
import ru.axas.contacts.common.models.local.EventProject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var GlobalDada = SingleLiveEvent<DataSingleLive>()

private val dataScope = CoroutineScope(Dispatchers.Main)

fun gDMessage(text: String?) = funInScope { it.copy(messageSnack = EventProject(text)) }
fun gDLoaderStart() = funInScope { it.copy(isLoad = true) }
fun gDLoaderStop() = funInScope { it.copy(isLoad = false) }

private fun funInScope(unit: (DataSingleLive) -> DataSingleLive) = dataScope.launch {
    GlobalDada.value = unit.invoke(GlobalDada.value ?: DataSingleLive())
}