import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import java.text.SimpleDateFormat

plugins {
    with(libs.plugins) {
        id(application.get().pluginId)
        id(kotlinAndroid.get().pluginId)
        id(kotlinSerialization.get().pluginId)
        id(kotlinSerializationPlugin.get().pluginId)
        id(devtools.get().pluginId)
        id(kotlinParcelize.get().pluginId)
        id(kotlinKapt.get().pluginId)
    }
}



android {
    val props = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
    val storeFileDirName = "storeFileDir"
    val releaseName = "release"
    val storePasswordName = "storePassword"
    val keyAliasName = "keyAlias"
    val keyPasswordName = "keyPassword"

    namespace = "com.example.voicerecognition.common"
    compileSdk = libs.versions.targetSdk.get().toInt()

    if (props.containsKey(storeFileDirName)) {
        signingConfigs {
            create(releaseName) {
                storeFile = file(props.getProperty(storeFileDirName))
                storePassword = props.getProperty(storePasswordName)
                keyAlias = props.getProperty(keyAliasName)
                keyPassword = props.getProperty(keyPasswordName)
            }
        }
    }

    defaultConfig {
        applicationId = namespace

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = "${libs.versions.versionName.get()}($versionCode)"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        archivesName.convention(getNameFileForeConvention(applicationId, versionName))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            if (props.containsKey(storeFileDirName)) {
                signingConfig = signingConfigs.getByName(releaseName)
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            versionNameSuffix = "-DEBUG"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions { jvmTarget = "17" }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions { kotlinCompilerExtensionVersion = libs.versions.kotlinCompiler.get() }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    with(libs) {
        //    #   Kotlin
        implementation(kotlin.serialization)

        //    #   Kotlin Datetime
        implementation(kotlinx.datetime)

        //    #   Androidx core-ktx
        implementation(androidx.core.ktx)

        //    #   Androidx runtime-ktx
        implementation(androidx.lifecycle)

        //    #   AndroidxWork runtime
        implementation(androidxWork.runtime.ktx)

        //    #   Google play
        implementation(google.play.core)

        //    #   Datastore
        implementation(datastore.preferences)

        //    #   Coroutines
        implementation(kotlin.android.coroutines)
        implementation(kotlin.coroutines)

//    #   Koin
        runtimeOnly(koin.androidx.compose)
        implementation(koin.android)
        implementation(koin.core)
        implementation(koin.ktor)


//    #   Voyager
        implementation(voyager.navigator)
        implementation(voyager.transitions)
        implementation(voyager.androidx)
        implementation(voyager.koin)

        //    #   Coil
        implementation(coil.compose)
        implementation(coil.svg)

        //    #   Zelory
        implementation(zelory.compressor)

//    #   Ktor
        implementation(platform(ktor.boom))
        implementation(ktor.client.core)
        implementation(ktor.client.cio)
        implementation(ktor.client.logging)
        implementation(ktor.client.negotiation)
        implementation(ktor.serialization.json)
        implementation(ktor.serialization.gson)
        implementation(ktor.serialization.jackson)
        implementation(ktor.utils)

        //    #   Paging
        implementation(paging.compose)

        // Logger
        implementation(napier)

        //    #   Compose
        implementation(compose.activity)
        implementation(platform(compose.bom))
        implementation(compose.material)
        runtimeOnly(compose.animation)
        runtimeOnly(compose.foundation)
        implementation(compose.ui)
        implementation(compose.ui.graphics)
        implementation(compose.ui.tooling.preview)

    }
}

fun getNameFileForeConvention(namespace: String?, version: String?): String {
    val dateName = SimpleDateFormat("(dd'd_'HH'h'-MM'm')")
        .format(System.currentTimeMillis())
    val versionName = (version ?: "").replace(".", "-")
    var minNameSpace = namespace ?: ""
    minNameSpace = minNameSpace.substring(minNameSpace.lastIndexOf(".") + 1)
    minNameSpace = minNameSpace.replace(".", "-")
    return "app_${minNameSpace}_${dateName}_${versionName}"
}