# App Room + Retrofit (Kotlin / Jetpack Compose)

App Android com duas telas por integrante:

| Tela | Fonte de dados | Tecnologia |
|------|----------------|------------|
| Livros | Banco local (tabela `livros`) | Room + Coroutines/Flow |
| Álbuns | API `GET /albums` (JSONPlaceholder) | Retrofit + Coroutines |

## Como compilar sem Android Studio
Todo `push` dispara o workflow **Build APK** (aba *Actions* do GitHub).
Ao terminar, baixe o APK em *Artifacts* e instale no celular.

## Estrutura
- `data/`: entidade, DAO, banco Room, modelo e serviço Retrofit
- `ui/`: ViewModels e telas (Compose)
- `MainActivity.kt`: navegação entre as telas

## Integrantes
| Integrante | Tabela Room | Rota da API |
|------------|-------------|-------------|
| (nome) | `livros` | `/albums` |
