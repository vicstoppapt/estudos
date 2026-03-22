# Injeção de dependências (DI) e IoC

**Submódulo `frameworks-springboot`**. Spring Boot como container: [springbootniveis.md](springbootniveis.md). Camadas: [mvcerestcamadas.md](mvcerestcamadas.md).

---

## 1. Problema sem DI

```java
class PedidoService {
    private final EmailSender email = new SmtpEmailSender(); // rígido, difícil de testar
}
```

A classe **cria** a dependência → acoplamento forte; em testes não substitui facilmente por um *fake*.

---

## 2. Inversão de controlo (IoC)

Quem **cria** e **liga** os objetos não é a tua classe de domínio, mas um **container** (*ApplicationContext* no Spring). A classe só **declara** o que precisa (construtor, campo, setter).

---

## 3. Injeção de dependências (DI)

**Injetar** = o container **fornece** a instância já criada (ou um *proxy*).

Formas comuns no Spring:

| Estilo | Notas |
|--------|--------|
| **Construtor** | Preferido para dependências **obrigatórias**; imutabilidade; fácil de testar |
| **Campo** `@Autowired` | Evita em código novo — esconde dependências, dificulta testes |
| **Setter** | Raro; útil se a dependência é opcional ou *reconfigurável* |

Neste módulo, a cadeia típica é:

- [`ArticleController`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/ArticleController.java) recebe **`ArticleService`** no construtor.
- [`DefaultArticleService`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/application/DefaultArticleService.java) recebe **`ArticleRepository`**.
- [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java) é um `@Repository` sem dependências de aplicação.

O Spring resolve a **ordem** de criação com base no grafo de dependências.

---

## 4. Bean

No Spring, **bean** = objeto gerido pelo container (vida útil e *wiring* controlados). Registo por **anotações** (`@Component`, `@Service`, `@Repository`, `@RestController`, …) ou **`@Bean`** em `@Configuration`.

---

## 5. Interface + implementação

Injetar **`ArticleService`** / **`ArticleRepository`** como **interface** permite trocar implementação (testes com *mock*, JPA em produção) sem alterar o consumidor — ver [`ArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/repository/ArticleRepository.java) vs [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java).

Seguinte: [springbootniveis.md](springbootniveis.md).
