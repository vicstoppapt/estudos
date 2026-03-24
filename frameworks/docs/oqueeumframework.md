# O que é um framework?

**English:** [oqueeumframework-en.md](oqueeumframework-en.md)

**Agregador `frameworks`** (vários submódulos Maven). Níveis de profundidade: [niveisdefinicao.md](niveisdefinicao.md).

---

## 1. Framework vs biblioteca

| | **Biblioteca** | **Framework** |
|---|----------------|-----------------|
| Quem manda no fluxo | **Tu** chamas funções da biblioteca quando queres | O **framework** chama o teu código em **ganchos** (*callbacks*, anotações, interfaces) |
| Exemplo em Java | `Jackson.readValue(...)`, `java.net.http` | **Spring**, **Jakarta EE** (servidor chama *servlets*), **JUnit** (corre os teus `@Test`) |

Em framework: **inversão de controlo** (*Inversion of Control* — IoC): o **runtime** orquestra; tu preenches **extensões**.

---

## 2. Para que serve na prática

- **Menos código de infraestrutura** repetido (HTTP, serialização JSON, transações, filas).
- **Convenções** partilhadas na equipa (pastas, nomes, anotações).
- **Ecossistema** (*starters*, documentação, ferramentas).

Custo: **curva de aprendizagem**, **versões** acopladas, **magia** se não souberes o que o default faz.

---

## 3. Porque Spring Boot como modelo neste módulo

- **Muito usado** em empresas Java (APIs, *batch*, integrações).
- **Spring Boot** = Spring + **auto-configuração** + **servidor embutido** + **starters** Maven — bom “caso de estudo” de framework **opinativo** com **pontos de extensão** claros.

Não implica que seja sempre a melhor escolha — ver [outrosframeworksjvm.md](outrosframeworksjvm.md).

Seguinte: [injecaodependencias.md](injecaodependencias.md) → [mvcerestcamadas.md](mvcerestcamadas.md) (MVC REST e camadas no código).
