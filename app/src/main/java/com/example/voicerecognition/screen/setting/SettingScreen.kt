package com.example.voicerecognition.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.voicerecognition.base.common_composable.BoxFillWeight
import com.example.voicerecognition.base.common_composable.BoxSpacer
import com.example.voicerecognition.base.common_composable.IconButtonApp
import com.example.voicerecognition.base.common_composable.TextFieldApp
import com.example.voicerecognition.base.res.DimApp
import com.example.voicerecognition.base.theme.ThemeApp
import com.example.voicerecognition.common.R
import com.example.voicerecognition.common.base.util.getQualifiedName
import com.example.voicerecognition.common.base.util.rememberModel
import com.example.voicerecognition.common.base.util.rememberState

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

    var textFildDomain by rememberState(domain) { TextFieldValue(domain) }
    var textFildUrl by rememberState (url){ TextFieldValue(url) }

    Column(modifier = Modifier.fillMaxSize().imePadding()) {
        BoxFillWeight()
        Text(
            text = "Порт:",
            style = ThemeApp.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        BoxSpacer()
        TextFieldApp(
            value = textFildDomain,
            onValueChange = { textFildDomain = it },
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
            value = textFildUrl,
            onValueChange = { textFildUrl = it },
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
                model.changeDomain(textFildDomain.text)
                model.changeUrl(textFildUrl.text)
                model.goBackStack()
            },
            rawImage = R.raw.ic_save
        )
        BoxFillWeight()
    }
}

