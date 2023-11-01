package ru.axas.spechrecognizer.screen.setting

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
import ru.axas.spechrecognizer.base.common_composable.BoxFillWeight
import ru.axas.spechrecognizer.base.common_composable.BoxSpacer
import ru.axas.spechrecognizer.base.common_composable.IconButtonApp
import ru.axas.spechrecognizer.base.common_composable.TextFieldApp
import ru.axas.spechrecognizer.base.res.DimApp
import ru.axas.spechrecognizer.base.theme.ThemeApp
import ru.axas.spechrecognizer.common.R
import ru.axas.spechrecognizer.common.base.util.getQualifiedName
import ru.axas.spechrecognizer.common.base.util.rememberModel
import ru.axas.spechrecognizer.common.base.util.rememberState

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
//    val textNetwork by model.responseVoice.collectAsState()
    val domain by model.domain.collectAsState()
    val url by model.url.collectAsState()

    var textFildAddress by rememberState(domain) { TextFieldValue(domain) }
    var textFildPort by rememberState (url){ TextFieldValue(url) }

    Column(modifier = Modifier.fillMaxSize().imePadding()) {
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        Box(modifier = Modifier
            .align(Alignment.End)
            .padding(DimApp.screenPadding)
        ) {
            IconButtonApp(
                onClick = model::goBackStack,
                rawImage = R.raw.ic_back
            )
        }
        BoxFillWeight()
        Text(
            text = "Порт:",
            style = ThemeApp.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        BoxSpacer()
        TextFieldApp(
            value = textFildPort,
            onValueChange = { textFildPort = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DimApp.screenPadding),
        )
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        Text(
            text = "Адрес:",
            style = ThemeApp.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        BoxSpacer()
        TextFieldApp(
            value = textFildAddress,
            onValueChange = { textFildAddress = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DimApp.screenPadding),
        )
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()


        IconButtonApp(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                model.changeAddress(textFildAddress.text)
                model.changePort(textFildPort.text)
                model.goBackStack()
            },
            rawImage = R.raw.ic_save
        )
        BoxFillWeight()
    }
}

