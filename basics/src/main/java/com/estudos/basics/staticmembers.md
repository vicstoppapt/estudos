# `StaticMembers`

Exemplo: `StaticMembers.java` (classe interna `Visitante`).

## Campo `static`

`private static int totalCriados` — **um** contador para **toda** a classe, não uma cópia por objeto. Todas as instâncias de `Visitante` compartilham o mesmo `totalCriados`.

## Método `static`

`Visitante.getTotalCriados()` — chama-se pelo **nome da classe**, não precisa de `new`. Dentro de `static` **não** existe `this` de instância: não há “este objeto” fixo.

## Instância vs classe

- **`id`** (não static): cada `Visitante` tem o seu.
- **`totalCriados` (static):** único, incrementado em **cada** construtor.

Isto prepara o terreno para `static` em `MultithreadingIntro` (`LOCK`, variáveis de classe) e para concorrência no `core`.

## Cuidado

`static` em excesso vira estado global difícil de testar; use quando o significado for realmente “da classe toda”.
