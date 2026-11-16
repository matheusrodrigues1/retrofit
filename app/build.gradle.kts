plugins { //Aqui os plugins são aplicados, sem versão
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.grupo.app" //o pacote base do código (com.grupo.app). Ele precisa coincidir com a pasta java/com/grupo/app/
    compileSdk = 34 //compila contra o Android 14.

    defaultConfig {
        applicationId = "com.grupo.app" //a identidade única do app no aparelho
        minSdk = 24 //só instala em Android 7.0 ou superior
        targetSdk = 34 //a versão do Android para a qual o app foi pensado
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.09.00") //uma lista de versões compatíveis entre si. Por causa dela, material3 e ui ficam sem número de versão.
    implementation(composeBom)
    implementation("androidx.compose.material3:material3") //os componentes visuais (Card, Button, NavigationBar)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.activity:activity-compose:1.9.2") //traz o setContent, que liga a Activity ao Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5") //traz a função viewModel() usada nas telas

    // Room (banco local)
    implementation("androidx.room:room-runtime:2.6.1") //room-runtime: o núcleo do Room
    implementation("androidx.room:room-ktx:2.6.1") //o que dá suporte a Flow e coroutines
    ksp("androidx.room:room-compiler:2.6.1") //gera o código do banco. Usa ksp(...), e não implementation, porque só roda na compilação

    // Retrofit (API)
    implementation("com.squareup.retrofit2:retrofit:2.11.0") //o cliente HTTP e o conversor de JSON para objetos.
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1") //coroutines com suporte à thread principal do Android.
}
