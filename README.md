# SimpleCatalog — Android MVVM + Room + Retrofit + Coroutines (Offline-First)

Este é um projeto Android simples e objetivo, criado para demonstrar competências essenciais no desenvolvimento **Android nativo com Kotlin**, incluindo boundaries limpas, persistência local com Room, consumo de API com Retrofit/OkHttp/Gson e concorrência moderna com **Coroutines (`viewModelScope` + `delay`)**.

---

## Stack utilizada

- **Linguagem:** Kotlin (intermediário/avançado)
- **UI:** XML (**ConstraintLayout**) + **RecyclerView** + **ViewBinding**
- **Concorrência:** Coroutines com `viewModelScope` (não-bloqueante, cancelamento automático por lifecycle)
- **Networking:** Retrofit + OkHttp + Gson (Converter)
- **Persistência local:** Room (cache local sobre SQLite)
- **Arquitetura:** MVVM + princípios de Clean Architecture (UI → Domain → Data)
- **Ferramentas:** Gradle (Version Catalog), Git, Android Studio
- **Testes:**
  - **Unitários:** JUnit + Mockito
  - **UI:** Espresso (AndroidX Test)

---

## Arquitetura do projeto

```
br.com.simplecatalog_kotlin
│
├── ui/            → Activity, Adapter, ViewModel, ViewModelFactory
├── domain/        → Modelos de domínio (imutáveis)
├── repository/    → Repositório (API + cache local)
├── data/
│   ├── remote/    → Retrofit/OkHttp/Gson + DTOs da API
│   ├── local/     → Room Database + Entities + DAO
│   └── mapper/    → Conversores DTO ↔ Entity ↔ Domain
└── config/        → Configurações compartilhadas
```

### Responsabilidades principais
- A UI **apenas observa `LiveData`**, sem conhecer DTOs ou Entities.
- O `ViewModel` usa `viewModelScope.launch { delay(...) }` para simular ou executar IO sem travar a Main Thread.
- O domínio usa **modelos imutáveis** (`data class Item`) para previsibilidade e segurança.
- A mutabilidade interna do `ViewModel` é protegida por **backing properties (`_items`, `_loading`) privados**.

---

## Funcionalidades do app

- Carrega lista hardcoded para simular IO pesado de forma **não-bloqueante** (via coroutines).
- Exibe itens em `RecyclerView` com `ListAdapter + DiffUtil` (atualização otimizada).
- Mantém contrato observável de estados: `loading`, `items`.
- Permite `refresh()` (gancho para discutir migrações, cache, interceptors, observabilidade, etc).

---

## Como rodar o app

1. Clone o projeto
2. Abra no **Android Studio**
3. Sincronize o Gradle
4. Execute (`Run ▶️`) em emulador ou dispositivo físico

---

## Como rodar os testes

### Testes unitários
- Clique com botão direito em `domain/` ou em `GetItemsUseCaseTest`
- `Run Tests`

### Testes de UI (Espresso)
- Selecione o build variant `debug`
- Clique com botão direito em `androidTest/`
- `Run Tests`

---

## Pontos de destaque para entrevistas

- Separação clara de camadas e boundaries limpas
- **Coroutines + `viewModelScope`** para concorrência moderna
- **Room** para persistência local tipada
- **Retrofit + OkHttp + Gson** para networking
- **ListAdapter + DiffUtil** para updates otimizados
- Testes com **Mockito** e **Espresso**
- Tópicos para discutir:
  - Cache local e offline-first
  - Migrações do Room
  - Interceptors do OkHttp
  - Observabilidade e métricas
  - Versionamento debug/release
  - Boundaries via Mapper

---

## Licença

MIT License  
Copyright (c) 2026 Luiz Camilo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Este projeto foi criado para fins de estudo e entrevistas. Pode ser reutilizado e adaptado livremente.

---

**Desenvolvido por:** Luiz Camilo
