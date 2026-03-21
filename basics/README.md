# Java basics (`java-basics`)

Módulo **anterior** ao `core` na trilha mental: só linguagem Java mínima (variáveis, métodos, `static`, objetos, fluxo, arrays). Nada de threads nem JVM profunda — isso fica em `core`.

**JDK 17** (`maven.compiler.release` no `pom.xml`).

## Ordem sugerida

| # | Classe (`exec:mainClass`) | Tema |
|---|---------------------------|------|
| 1 | `com.estudos.basics.VariablesAndTypes` | primitivos vs referência (`String`, `Integer`), `==`/`equals`, `null`, wrappers |
| 2 | `com.estudos.basics.MethodsAndParameters` | assinatura, retorno, sobrecarga |
| 3 | `com.estudos.basics.ObjectsConstructorsThis` | `new`, construtor, campos de instância, `this` |
| 4 | `com.estudos.basics.StaticMembers` | `static`, pertence à classe |
| 5 | `com.estudos.basics.FinalStaticInterfacesAndEncap` | `final`, `interface`, encapsulamento |
| 6 | `com.estudos.basics.ControlFlow` | `if`, laços, `switch` |
| 7 | `com.estudos.basics.ArraysAndNull` | arrays, `null` |

Teoria longa: um **`.md` por classe** + documentos transversais (`memory*.md`, `cpucache*.md`, [apiconceitogeral.md](src/main/java/com/estudos/basics/apiconceitogeral.md)) em [`src/main/java/com/estudos/basics/README.md`](src/main/java/com/estudos/basics/README.md).

## Executar

```bash
cd basics
mvn -q compile exec:java "-Dexec.mainClass=com.estudos.basics.VariablesAndTypes"
```

**PowerShell:** mantenha aspas no `-Dexec.mainClass=...`.

## Depois

Seguir para `../core` (`core/README.md` — trilha numerada).
