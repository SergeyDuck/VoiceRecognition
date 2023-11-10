package com.example.voicerecognition.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import com.example.voicerecognition.base.common_composable.BoxFillWeight
import com.example.voicerecognition.base.common_composable.BoxSpacer
import com.example.voicerecognition.base.theme.ThemeApp
import com.example.voicerecognition.base.util.PermissionsModule
import com.example.voicerecognition.common.base.util.getQualifiedName
import com.example.voicerecognition.common.base.util.rememberModel
import com.example.voicerecognition.common.models.logger.LogCustom.logD
import com.example.voicerecognition.common.models.util.addContact
import com.example.voicerecognition.common.models.util.getContacts
import com.example.voicerecognition.model.Contact

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
    val context = LocalContext.current

    PermissionsModule.PermissionAccessContact { flag ->
        if (flag) {
             addContact(context =context, list = listOf(
                 Contact(
                     phone = "+7999999999",
                     firstName = "firstName",
                     lastName = "lastName",
                     company = "company"
                 )
             ))

        }
    }
    Column(modifier = Modifier.fillMaxSize()) {

        BoxFillWeight()
        TextButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "выгрузить на сервер",
                style = ThemeApp.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        TextButton(onClick = { }, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "экспортировать на устройство",
                style = ThemeApp.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        BoxFillWeight()
    }
}

