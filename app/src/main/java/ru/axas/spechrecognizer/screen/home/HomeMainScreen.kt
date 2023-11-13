package ru.axas.spechrecognizer.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import ru.axas.spechrecognizer.base.common_composable.BoxFillWeight
import ru.axas.spechrecognizer.base.common_composable.BoxSpacer
import ru.axas.spechrecognizer.base.extension.getFormattedDate
import ru.axas.spechrecognizer.base.theme.ThemeApp
import ru.axas.spechrecognizer.base.util.PermissionsModule
import ru.axas.spechrecognizer.common.base.util.LifeScreen
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
    var isAccept by rememberState {
        false
    }

    PermissionsModule.PermissionAccessContact { flag ->
        isAccept = flag
    }

    val postDate by model.timeSend.collectAsState()
    val getDate by model.timeGet.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {

        BoxFillWeight()
        TextButton(onClick = model::postData, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Отправить данные",
                style = ThemeApp.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        BoxSpacer()
        postDate?.let {
            Text(
                text = "Последняя отправка: ${it.getFormattedDate()}",
                style = ThemeApp.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        BoxSpacer()
        BoxSpacer()
        TextButton(onClick = model::getData, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Получить данные",
                style = ThemeApp.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        BoxSpacer()
        getDate?.let{
            Text(
                text = "Контакты получены: ${it.getFormattedDate()}",
                style = ThemeApp.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        BoxSpacer()
        BoxFillWeight()
    }
}

