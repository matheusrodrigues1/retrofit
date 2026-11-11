App Room + Retrofit

Aplicativo Android feito em Kotlin que mostra dados vindos de duas origens diferentes: o banco de dados do próprio aparelho e uma API na internet. Foi desenvolvido como atividade prática para integrar banco de dados local, consumo de API e Coroutines.

O que o app faz

O app tem duas telas, acessadas por um menu na parte de baixo:

Tela	O que mostra	De onde vêm os dados
Livros	Título, autor e ano de cada livro	Banco de dados local (Room), tabela livros
Álbuns	Título, número e usuário de cada álbum	API na internet (Retrofit), rota GET /albums

As telas só exibem dados: não há cadastro nem atualização de informações.

O que é cada coisa

API: é um serviço na internet que entrega dados quando o app pede. O app envia uma requisição para um endereço (por exemplo, https://jsonplaceholder.typicode.com/albums) e recebe uma resposta com os dados. A API usada aqui é a JSONPlaceholder, uma API gratuita criada para testes, cujos dados são fictícios.

Rota (endpoint): é o caminho específico dentro da API que entrega um tipo de dado. A rota /albums entrega a lista de álbuns. Outras rotas da mesma API entregam outros dados, como /posts e /users.

JSON: é o formato em que a API responde, um texto organizado em chave e valor. Exemplo: {"userId": 1, "id": 1, "title": "quidem molestiae enim"}.

Retrofit: biblioteca que faz a requisição à API e converte o JSON recebido em objetos Kotlin (a conversão é feita pelo conversor Gson). Com ele, buscar os álbuns vira a chamada de uma função.

Banco de dados local: é onde o app guarda informações dentro do próprio aparelho. Funciona sem internet e os dados permanecem salvos mesmo se o app for fechado.

Room: biblioteca que facilita o uso do banco de dados do Android (SQLite). Ela é formada por três partes:

Entity (Livro): a classe que vira uma tabela; cada propriedade é uma coluna.
DAO (LivroDao): a interface com as consultas ao banco.
Database (AppDatabase): o banco em si, que também insere os livros iniciais na primeira vez que o app abre.

Coroutines: é a forma de executar tarefas demoradas, como acessar o banco ou a internet, sem travar a tela. O app usa suspend (função que pode esperar a resposta e continuar depois) e Flow (fluxo que entrega os dados e se atualiza quando eles mudam).

ViewModel: guarda o estado de cada tela e faz a ligação entre a tela e os dados. Ele mantém as informações mesmo quando o aparelho é girado.

Jetpack Compose: forma moderna de criar telas no Android, escrevendo a interface apenas em Kotlin, sem arquivos XML.

Gradle: ferramenta que configura e compila o projeto. GitHub Actions é o serviço que compila o app automaticamente no GitHub a cada envio de código e gera o APK.

Como o app funciona
Tela (Compose)  →  ViewModel  →  Fonte de dados (Room ou Retrofit)
Livros: tabela livros → LivroDao → LivrosViewModel → LivrosScreen
Álbuns: API /albums → ApiService → AlbunsViewModel → AlbunsScreen

Na tela Álbuns, enquanto a resposta não chega aparece um indicador de carregamento. Se der erro (por exemplo, sem internet), aparece uma mensagem com o botão "Tentar novamente".

Como executar
Android Studio: abra a pasta do projeto, aguarde o Gradle Sync e clique em Run ▶ com um celular conectado ou um emulador.
GitHub: a cada push, o workflow Build APK (aba Actions) gera o APK em Artifacts, que pode ser instalado em um aparelho Android.
