pluginManagement { // diz onde achar os plugins
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() //é o repositório padrão de plugins do Gradle
    }
}
dependencyResolutionManagement {  // diz onde achar as bibliotecas. Room e Compose estão no google, e Retrofit e Coroutines no mavenCentral.
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // se algum módulo tentar declarar repositório próprio, o build falha.
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "AppRoomRetrofit" //nome do projeto
include(":app") //registra o módulo app. Sem essa linha, nada é compilado.
