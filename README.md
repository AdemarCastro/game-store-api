# API da Loja de Jogos 🎮🕹️

Bem-vindo ao repositório da nossa incrível API da Loja de Jogos! A API permite gerenciar dados de jogos e usuários, realizando operações CRUD, verificando os dados inseridos por Validadores e futuramente fornecendo estatísticas sobre os jogos disponíveis. 🚗🚆

## Funcionalidades Principais 🚀

### 1. Operações com Jogos

#### 1.1 Criar Jogo

**Endpoint:** `POST /api-gamestore/games`

Crie um novo jogo fornecendo os detalhes no corpo da requisição, como exemplificado abaixo:

```json
{
    "id": 1,
    "name": "Super Metroid",
    "genero": "Ação e Aventura",
    "description": "Super Metroid é um jogo eletrônico de ação-aventura lançado em 1994 para o Super Nintendo Entertainment System. É o terceiro jogo da série Metroid e foi desenvolvido para a Nintendo pela sua subsidiária, R&D1.",
    "year": "1991",
    "price": "59.99",
    "urlImage": "https://upload.wikimedia.org/wikipedia/en/thumb/e/e4/Smetroidbox.jpg/220px-Smetroidbox.jpg",
    "available": "Disponível"
}
```

#### Respostas:

- ✅ 201: Jogo criado com sucesso.
- ❌ 400: JSON inválido.
- ❌ 422: Campos não parseáveis.

#### 1.2 Atualizar Jogo

**Endpoint:** `PUT /api-gamestore/games/{id}`

Atualize as informações de um jogo existente enviando um objeto modificado no corpo da requisição. Vamos adicionar uma promoção ao jogo abaixo.

```json
{
    "id": 1,
    "name": "Super Metroid",
    "genero": "Ação e Aventura",
    "description": "Super Metroid é um jogo eletrônico de ação-aventura lançado em 1994 para o Super Nintendo Entertainment System. É o terceiro jogo da série Metroid e foi desenvolvido para a Nintendo pela sua subsidiária, R&D1.",
    "year": "1991",
    "price": "39.99",
    "urlImage": "https://upload.wikimedia.org/wikipedia/en/thumb/e/e4/Smetroidbox.jpg/220px-Smetroidbox.jpg",
    "available": "Disponível"
}
```

#### Respostas:

- ✅ 200: Atualização bem-sucedida.
- ❌ 400: JSON inválido.
- ❌ 404: Jogo não encontrado.
- ❌ 422: Campos não parseáveis (JSON mal formatado).

#### 1.3 Listar Jogos

**Endpoint:** `GET /api-gamestore/games`

Recupere uma lista de todos os jogos disponíveis.

#### Respostas:

- ✅ 200: Jogos encontrados.
- ❌ 404: Nenhum jogo encontrado.

#### 1.4 Remover Todos os Jogos

**Endpoint:** `DELETE /api-gamestore/games`

Remova todos os jogos com uma requisição de corpo vazio e receba um tranquilo 204.

### 2. Operações com Usuários

#### 2.1 Criar Usuário

**Endpoint:** `POST /api-gamestore/users`

Crie um novo usuário fornecendo os detalhes no corpo da requisição, como exemplificado abaixo:

```json
{
   "cpf": "84112592000",
   "name": "João da Silva",
   "email": "joao.silva@example.com",
   "password": "senha123",
   "urlImage": "https://example.com/image.jpg",
   "phone": "+55 (11) 98765-4321"
}
```

#### Respostas:

- ✅ 201: Usuário criado com sucesso.
- ❌ 400: JSON inválido.
- ❌ 422: Campos não parseáveis.

#### 2.2 Atualizar Usuário

**Endpoint:** `PUT /api-gamestore/users/{cpf}`

Atualize as informações de um usuário existente enviando um objeto modificado no corpo da requisição. Vamos atualizar a senha do usuário abaixo.

```json
{
   "cpf": "84112592000",
   "name": "João da Silva",
   "email": "joao.silva@example.com",
   "password": "newPassword",
   "urlImage": "https://example.com/image.jpg",
   "phone": "+55 (11) 98765-4321"
}
```

#### Respostas:

- ✅ 200: Atualização bem-sucedida.
- ❌ 400: JSON inválido.
- ❌ 404: Usuário não encontrado.
- ❌ 422: Campos não parseáveis (JSON mal formatado).

#### 2.3 Listar Usuários

**Endpoint:** `GET /api-gamestore/users`

Recupere uma lista de todos os usuários cadastrados.

#### Respostas:

- ✅ 200: Usuários encontrados.
- ❌ 404: Nenhum usuário encontrado.

#### 2.4 Remover Todos os Usuários

**Endpoint:** `DELETE /api-gamestore/users`

Remova todos os usuários com uma requisição de corpo vazio e receba um tranquilo 204.

## Prefixos dos Commits
- 📚 [DOCS]: Apenas mudanças na documentação.
- ✨ [FEAT]: Adição de uma nova funcionalidade ao projeto, componente, etc.
- 🐞 [FIX]: Correção de um bug.
- ⚡ [PERF]: Melhoria de performance.
- 🛠️ [REFACTOR]: Refatoração do código que não adiciona uma funcionalidade nem corrige um bug.
- 🎨 [STYLE]: Mudanças no código que não afetam seu significado (espaço em branco, formatação, ponto e vírgula, etc).
- 🧪 [TEST]: Adição ou correção de testes.

## Contato
Email: seu.email@example.com <br>
LinkedIn: [Seu LinkedIn](https://www.linkedin.com/in/seu-linkedin/)
