# App Room + Retrofit

Aplicativo Android em **Kotlin** que exibe dados de duas origens: um **banco de dados local** e uma **API na internet**. Foi desenvolvido como atividade prática de integração entre banco de dados, API e Coroutines.

## O que é o app

Tem duas telas, acessadas por um menu inferior:

- **Livros:** lista de livros (título, autor e ano) lida do banco de dados do aparelho.
- **Álbuns:** lista de álbuns (título, número e usuário) obtida de uma API na internet.

As telas apenas exibem dados, sem cadastro nem atualização.

## Banco de dados

- **Room**, biblioteca do Android que funciona sobre o **SQLite**.
- Tabela `livros` (entidade `Livro`), com `id`, `titulo`, `autor` e `ano`.
- Os livros iniciais são inseridos na primeira vez que o app abre, e a leitura funciona sem internet.

## API

- **JSONPlaceholder** (`https://jsonplaceholder.typicode.com`), uma API pública e gratuita para testes, com dados fictícios.
- Rota usada: `GET /albums`, consumida com **Retrofit** e convertida de JSON para objetos Kotlin com **Gson**.

## Tecnologias e ferramentas

- **Kotlin**, linguagem do projeto
- **Jetpack Compose** (Material 3), para as telas
- **Room**, para o banco local
- **Retrofit + Gson**, para a API
- **Coroutines e Flow**, para executar banco e API sem travar a tela
- **ViewModel**, para guardar o estado de cada tela
- **KSP**, que gera o código do Room na compilação
- **Gradle**, para configurar e compilar
- **GitHub Actions**, que gera o APK automaticamente a cada push
- **Android Studio**, como ambiente de desenvolvimento

## Como executar

- **Android Studio:** abra a pasta do projeto, aguarde o Gradle Sync e clique em Run ▶.
- **GitHub:** o workflow **Build APK** (aba *Actions*) gera o APK em *Artifacts*.

A tela Álbuns precisa de internet.
