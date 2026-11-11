# App Room + Retrofit

Aplicativo Android em **Kotlin** que lê dados de duas origens: o **banco local** (Room) e uma **API externa** (Retrofit), usando **Coroutines**.

## O que o app faz

Duas telas, acessadas por um menu inferior:

| Tela | Dados | Origem |
|------|-------|--------|
| **Livros** | Título, autor e ano | Banco local (Room), tabela `livros` |
| **Álbuns** | Título, número e usuário | API JSONPlaceholder, rota `GET /albums` (Retrofit) |

As telas apenas exibem dados, sem cadastro nem atualização.

## Requisitos da atividade

| Requisito | Onde está |
|-----------|-----------|
| Duas telas | `LivrosScreen` e `AlbunsScreen` |
| Tela com Room | `LivrosScreen` lê a tabela `livros` pelo `LivroDao` |
| Tela com Retrofit | `AlbunsScreen` consome `/albums` pelo `ApiService` |
| Tabela própria | `livros` |
| Rota própria da API | `/albums` |
| Coroutines | `Flow` no Room e `suspend` no Retrofit, usados nos ViewModels |

## Tecnologias

Kotlin, Jetpack Compose, Room, Retrofit + Gson, Coroutines/Flow, ViewModel, Gradle e GitHub Actions.

## Estrutura

- `data/`: entidade, DAO e banco (Room); modelo e serviço da API (Retrofit)
- `ui/`: ViewModels e telas
- `MainActivity.kt`: menu de navegação entre as telas

## Como executar

- **Android Studio:** abra a pasta do projeto, aguarde o Gradle Sync e clique em Run ▶.
- **GitHub:** a cada push, o workflow **Build APK** (aba *Actions*) gera o APK em *Artifacts*.
