# `PassByValue`

**English:** [passbyvalue-en.md](passbyvalue-en.md)

Exemplo em: `PassByValue.java`.

## Objetivo

Fixar o modelo de **passagem de parâmetros em Java**: sempre **por valor**. O “valor” de uma variável de tipo referência é **a cópia do ponteiro** para o objeto; o “valor” de um `int` é **a cópia do número**.

## Os três métodos

### `tentarTrocarReferencia(StringBuilder sb)`

- O parâmetro `sb` é uma **cópia** da referência que o `main` passou.
- `sb = new StringBuilder("outro")` só **reaponta essa cópia** para outro objeto. A variável `a` no `main` continua apontando para o mesmo `StringBuilder` de antes.
- **Conclusão:** Java **não** permite que o chamador “troque” para qual objeto a variável dele aponta via reatribuição no parâmetro.

### `mutarConteudo(StringBuilder sb)`

- `sb` ainda é cópia da referência, mas **aponta para o mesmo objeto** que `a` no `heap`.
- `append` **altera o estado interno** desse objeto. Quem mais referencia o mesmo objeto vê a mudança.
- **Conclusão:** efeito colateral no objeto compartilhado é esperado; não é “passagem por referência” no sentido C++, é **dois ponteiros para o mesmo alvo**.

### `incrementar(int x)`

- `x` é cópia do valor primitivo. `x++` só altera a cópia local.
- **Conclusão:** o `n` do `main` permanece inalterado.

## Nuance comum em entrevistas

“Objetos são passados por referência” é **linguagem informal**; a especificação fala em **pass-by value** onde o valor é a referência. O que importa é prever o efeito: **reatribuição do parâmetro** não vaza; **mutação do objeto** vaza se compartilhado.

## Comparação mental com C++

Em C++ existe referência explícita (`int&`). Java não tem equivalente para rebind da variável do chamador.
