# API — conceito geral em TI (não é só web)

Texto transversal (sem `.java` dedicado). Serve para alinhar o vocabulário: quando alguém diz **“não existe API Java para X”** (ex.: forçar dados na L1 da CPU), **API** aqui significa **interface de programação exposta** — não necessariamente HTTP.

---

## 1. O que significa **API**

**API** = *Application Programming Interface* → **interface de programação de aplicações**.

Em termos simples: um **contrato** (explícito ou implícito) que diz **como** um pedaço de software **pode** usar outro pedaço — **sem** precisar saber todos os detalhes internos desse outro.

- **Quem oferece** a API expõe operações “públicas” (funções, endpoints, classes, syscalls…).  
- **Quem consome** escreve código ou pedidos **em cima** desse contrato.  
- Se o interior muda mas o contrato se mantém, o consumidor **continua** funcionando (idealmente).

“Interface” no nome é no sentido de **fronteira entre sistemas ou camadas**, não só “interface gráfica”.

### 1.1 Criador e consumidor

Podes ser **os dois**, em bases de código ou projetos diferentes:

- **Consumidor:** chamas `String.isBlank()`, usas `ArrayList`, pedidos HTTP a um serviço — usas o contrato que outro expôs.
- **Criador:** publicas biblioteca, expões classes `public` ou endpoints; ou defines `interface` / contrato que outros implementam.

`String` é `final`: na prática só és **consumidor** da API da classe (não a estendes por herança). Com **`List`**, o JDK define o contrato; podes ser **consumidor** (`new ArrayList<>()`) ou **criador** (`MinhaLista implements List`). Ver também [finalstaticinterfacesandencap.md](finalstaticinterfacesandencap.md).

---

## 2. Por que associas API a **web**?

Na prática diária de muitos projetos, **API REST / HTTP** é o exemplo mais visível:

- URLs, métodos `GET`/`POST`, JSON, OpenAPI/Swagger, autenticação com token…

Isso **é** uma API — mas é **um tipo**: API **remota**, **sobre a rede**, muitas vezes documentada como “API pública” do serviço.

O mesmo conceito de **contrato** aplica-se **fora** da web.

---

## 3. Exemplos de API em vários contextos de TI

| Contexto | O quê é a “API” | Exemplo |
|----------|------------------|---------|
| **Linguagem / biblioteca padrão** | Classes e métodos **públicos** que a documentação oficial garante | Em Java: `List.add`, `Files.readString`, `String.isBlank` — costuma-se dizer **“Java API”** ou **“API do JDK”** |
| **Framework** | O que o framework **deixa** chamar (hooks, anotações, interfaces a implementar) | Spring: `@RestController`, `JpaRepository`; Android SDK |
| **Sistema operacional** | Chamadas que pedem serviços ao kernel (**syscalls**) ou bibliotecas que as embrulham | **POSIX**, **Win32 API** — abrir arquivo, criar processo, sockets |
| **Base de dados** | Forma de enviar queries e receber resultados | **JDBC** (Java), protocolo **wire** do Postgres/MySQL |
| **Hardware / firmware** | Quando existe: registos, instruções especiais documentadas | Drivers, BIOS exposta a certas chamadas; **muita** microarquitetura da CPU **não** tem API de aplicação |
| **Rede (além de REST)** | Contratos em outros protocolos | **gRPC**, filas **AMQP**, **WebSockets** com mensagens acordadas |
| **CLI** | “API” informal: argumentos e códigos de saída estáveis | `git`, `kubectl` — automação trata o CLI como contrato |
| **Biblioteca nativa (JNI)** | Camada entre Java e C/C++ | Assinaturas `native` + `.so`/`.dll` — contrato entre JVM e código nativo |

Em todos estes casos, a ideia é: **há um limite documentado** entre “o que podes pedir” e “como o outro lado responde”.

---

## 4. Ligação com “não existe API para a L1”

A cache L1 é gerida por **hardware** da CPU. Para **programador de aplicação Java**, **não há**:

- método `Cache.putInL1(...)` no JDK;  
- syscall padrão “fixar linha no L1” no Windows/Linux para apps normais.

Ou seja: **não há interface de programação** **estável** e **suportada** a esse nível — por isso diz-se coloquialmente **“não há API”**.  
Não quer dizer “não existe software na Terra que fale com a CPU”, quer dizer **“não há contrato público para a tua app usar”** (o que existe é microcódigo / silício opaco).

---

## 5. API vs implementação

- **API** = o que está **prometido** (documentação, assinaturas públicas).  
- **Implementação** = **como** é feito por baixo (pode mudar desde que o contrato se mantenha).  

Exemplo: `List` em Java é **interface** (tipo de API); `ArrayList` e `LinkedList` são **implementações** diferentes.

---

## 6. API pública vs interna

- **Pública** (ou “externa”): clientes de outras equipes ou terceiros dependem dela — mudanças são **versionadas** e comunicadas.  
- **Interna**: só uso dentro do mesmo produto — você pode refatorar com mais liberdade.

---

## 7. Resumo em uma frase

**API** = **forma oficial e acordada de um programa usar outro serviço, biblioteca, SO ou serviço remoto** — **web** é só **uma** categoria muito comum.

---

## 8. Onde cruzar com o resto deste repositório

- [cpucachejvmenavegador.md](cpucachejvmenavegador.md) — onde falamos de “não há API” para L1.  
- [finalstaticinterfacesandencap.md](finalstaticinterfacesandencap.md) — `interface` como contrato; criador vs consumidor no código.  
- Código Java: cada método/classe `public` que você usa do JDK é parte da **Java SE API** (documentada em [Oracle Docs](https://docs.oracle.com/en/java/javase/) ou equivalente).
