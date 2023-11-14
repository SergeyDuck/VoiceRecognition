package ru.axas.contacts.screen.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import ru.axas.contacts.base.common_composable.BoxFillWeight
import ru.axas.contacts.base.common_composable.BoxSpacer
import ru.axas.contacts.base.common_composable.IconButtonApp
import ru.axas.contacts.base.common_composable.TextFieldApp
import ru.axas.contacts.base.res.DimApp
import ru.axas.contacts.base.theme.ThemeApp
import ru.axas.contacts.common.R
import ru.axas.contacts.common.base.util.getQualifiedName
import ru.axas.contacts.common.base.util.rememberModel
import ru.axas.contacts.common.base.util.rememberState

class SettingScreen : Screen {

    override val key: ScreenKey = keyName

    companion object {
        val keyName = object {}::class.getQualifiedName
    }

    @Composable
    override fun Content() {
        val model = rememberModel<SettingModel>()
        SettingScr(model)
    }
}

@Composable
fun SettingScr(
    model: SettingModel,
) {

}

