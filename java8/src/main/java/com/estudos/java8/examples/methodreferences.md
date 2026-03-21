# `MethodReferences`

Exemplo: `MethodReferences.java`.

## Objetivo

Formas `Tipo::método` quando a lambda só **delega** a um método existente.

## No código

- **`String::toUpperCase`** em `map`: referência de **instância** implícita (cada elemento do stream vira `this`).
- **`StringBuilder::new`** com `map` a partir de `Stream<String>`: o compilador escolhe o construtor `StringBuilder(String)`.

## Nuances

Quatro categorias clássicas: estático (`Math::abs`), instância de objeto particular, instância de tipo arbitrário (`String::length`), construtor (`ArrayList::new`). A assinatura deve bater com o **tipo funcional** esperado.
