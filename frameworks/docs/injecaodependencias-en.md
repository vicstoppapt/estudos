# Dependency injection (DI) and IoC

**Submodule `frameworks-springboot`**. Spring Boot as container: [springbootniveis-en.md](springbootniveis-en.md). Layers: [mvcerestcamadas-en.md](mvcerestcamadas-en.md).

**PT:** [injecaodependencias.md](injecaodependencias.md)

---

## 1. The problem without DI

```java
class OrderService {
    private final EmailSender email = new SmtpEmailSender(); // rigid, hard to test
}
```

The class **creates** the dependency → tight coupling; in tests you cannot easily swap a *fake*.

---

## 2. Inversion of control (IoC)

Who **creates** and **wires** objects is not your domain class but a **container** (*ApplicationContext* in Spring). The class only **declares** what it needs (constructor, field, setter).

---

## 3. Dependency injection (DI)

**Inject** = the container **provides** the instance (or a *proxy*).

Common styles in Spring:

| Style | Notes |
|-------|--------|
| **Constructor** | Preferred for **required** dependencies; immutability; easy to test |
| Field `@Autowired` | Avoid in new code — hides dependencies, hurts tests |
| **Setter** | Rare; useful if optional or *reconfigurable* |

In this module the typical chain is:

- [`ArticleController`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/ArticleController.java) receives **`ArticleService`** in the constructor.
- [`DefaultArticleService`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/application/DefaultArticleService.java) receives **`ArticleRepository`**.
- [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java) is a `@Repository` with no application dependencies.

Spring resolves **creation order** from the dependency graph.

---

## 4. Bean

In Spring a **bean** = object managed by the container (lifecycle and *wiring*). Registration via **annotations** (`@Component`, `@Service`, `@Repository`, `@RestController`, …) or **`@Bean`** in `@Configuration`.

---

## 5. Interface + implementation

Injecting **`ArticleService`** / **`ArticleRepository`** as **interfaces** lets you swap implementations (tests with *mock*, JPA in production) without changing consumers — see [`ArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/repository/ArticleRepository.java) vs [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java).

Next: [springbootniveis-en.md](springbootniveis-en.md).
