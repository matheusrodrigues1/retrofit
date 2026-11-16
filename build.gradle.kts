plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.20" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20" apply false
    id("com.google.devtools.ksp") version "2.0.20-1.0.25" apply false
}
 // Configura as versões dos plugins centralizadamente (apply false), incluindo Android e Kotlin.
// O plugin Compose acompanha a versão do Kotlin, enquanto o KSP gera código na compilação e deve ter a mesma versão-base do Kotlin para evitar erros no build.