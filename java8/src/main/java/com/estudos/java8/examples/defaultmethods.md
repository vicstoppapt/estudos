# `DefaultMethods`

Exemplo: `DefaultMethods.java`.

## Objetivo

Evoluir **interface** sem quebrar implementações antigas: método **`default`** com corpo; método **`static`** na interface (não sobrescrito por implementadores).

## Nuances

- **Herança diamante:** duas interfaces com default igual — a classe concreta deve resolver com `Interface.super.metodo()`.
- `default` não substitui `Object` methods de forma perigosa; há regras na linguagem.
- `static` na interface: chamada `Logger.header()` — não passa por polimorfismo de instância.

## Uso real

`Iterable.forEach`, `Comparator` com defaults, etc.
