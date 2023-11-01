package ru.axas.spechrecognizer.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import ru.axas.spechrecognizer.common.R
import ru.axas.spechrecognizer.base.common_composable.BoxFillWeight
import ru.axas.spechrecognizer.base.common_composable.BoxSpacer
import ru.axas.spechrecognizer.base.common_composable.IconButtonApp
import ru.axas.spechrecognizer.base.res.DimApp
import ru.axas.spechrecognizer.base.theme.ThemeApp
import ru.axas.spechrecognizer.base.util.PermissionsModule
import ru.axas.spechrecognizer.common.base.util.getQualifiedName
import ru.axas.spechrecognizer.common.base.util.rememberModel
import ru.axas.spechrecognizer.common.base.util.rememberState

class HomeMainScreen : Screen {

    override val key: ScreenKey = keyName

    companion object {
        val keyName = object {}::class.getQualifiedName
    }

    @Composable
    override fun Content() {
        val model = rememberModel<HomeMainModel>()
        HomeScr(model)
    }
}

@Composable
fun HomeScr(model: HomeMainModel) {
    val textNetwork by model.responseVoice.collectAsState()
    var textLocal by rememberState { "" }
    val recognizeIntent = PermissionsModule.recognizeIntent { str ->
        textLocal = str
        model.postText(str)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        Box(modifier = Modifier
            .align(Alignment.End)
            .padding(DimApp.screenPadding)
        ) {
            IconButtonApp(
                onClick = model::goToSetting,
                rawImage = R.raw.ic_setting
            )
        }
        BoxFillWeight()
        Text(
            text = "Текст:",
            style = ThemeApp.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        BoxSpacer()
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DimApp.screenPadding),
            textAlign = TextAlign.Center,
            color = ThemeApp.colors.goodContent,
            text = textLocal
        )
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        IconButtonApp(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = recognizeIntent::invoke,
            rawImage = R.raw.ic_mic
        )
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        Text(
            text = "Ответ:",
            style = ThemeApp.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        BoxSpacer()
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DimApp.screenPadding),
            text = textNetwork,
            textAlign = TextAlign.Center,
            color = ThemeApp.colors.attentionContent,
        )
        BoxFillWeight()
    }
}

