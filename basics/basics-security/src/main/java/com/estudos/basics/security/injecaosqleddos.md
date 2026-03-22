# SQL injection, DDoS e defesas (com exemplos)

**`basics-security`**. JDBC e dados: `basics-storage` → [jdbcfilesobjectstorage.md](../../../../../../../../basics-storage/src/main/java/com/estudos/basics/storage/jdbcfilesobjectstorage.md). Exemplo Java ilustrativo: [`SqlInjectionConcept.java`](SqlInjectionConcept.java).

---

## 1. SQL injection (SQLi)

### 1.1 Ideia

A aplicação **concatena** input do utilizador numa **string SQL**. O atacante injeta **sintaxe SQL** que altera o sentido da query (ignorar *password*, ler outras tabelas, apagar dados).

### 1.2 Exemplo clássico (anti-padrão — nunca em produção)

Código conceptual (pseudo):

```sql
SELECT id, name FROM users WHERE name = '<userInput>' AND password = '<pass>';
```

Se `userInput` for (com aspas incluídas na lógica do atacante):

```text
' OR '1'='1' --
```

A query torna-se logicamente em algo como: condição **sempre verdadeira** + comentário que anula o resto:

```sql
SELECT id, name FROM users WHERE name = '' OR '1'='1' -- ' AND password = '...'
```

Resultado: **autenticação contornada** ou **dados expostos**, conforme a query.

### 1.3 Outras variantes (mapa)

| Variante | Ideia |
|----------|--------|
| **Union-based** | `UNION SELECT` para extrair colunas de outras tabelas |
| **Blind (boolean/time)** | Inferir dados por diferença de resposta ou atraso (`SLEEP`) |
| **Second-order** | Input guardado hoje, executado noutro fluxo depois |

### 1.4 Defesas (ordem de preferência)

1. **`PreparedStatement` / parâmetros ligados** — o motor trata valores como **dados**, não como SQL. Em JPA, usar **parâmetros nomeados**, não concatenação em `@Query` nativo.
2. **ORM** com API parametrizada; **cuidado** com SQL nativo dinâmico e `createNativeQuery` + string.
3. **Least privilege** na BD: a app não precisa de `DROP`, `FILE`, nem `SELECT` em tabelas irrelevantes.
4. **Validação** de input (tipo, tamanho, *allowlist*) — **complemento**, não substituto de *parameterization*.
5. **WAF** / regras IDS como **camada extra**, não única linha de defesa.
6. **Escapar** manualmente é **frágil**; evita como estratégia principal.

---

## 2. DDoS (*Distributed Denial of Service*)

### 2.1 Objetivo

Tornar o serviço **indisponível** ou **degradado** para utilizadores legítimos, saturando **largura de banda**, **estado** (ligações TCP, *sockets* da app) ou **lógica** (pedidos caros).

### 2.2 Formas (exemplos de cenário)

| Tipo | Exemplo de mecanismo |
|------|----------------------|
| **Volumétrico** | Muitos Gbps de tráfego UDP/ICMP refletido (*amplification*) |
| **Protocolo** | *SYN flood*, esgotar tabela de ligações half-open |
| **Camada aplicação (L7)** | HTTP GET a URLs pesadas; *Slowloris* (ligações lentas); *login flood* |

**Botnets** distribuem origem; **difficulta** bloqueio por IP simples.

### 2.3 Defesas (arquitetura)

1. **CDN / *scrubbing* center** (Cloudflare, Akamai, proteções cloud provider) — absorve e filtra antes do teu *origin*.
2. **Rate limiting** por IP, por utilizador, por rota; *challenge* (CAPTCHA) sob suspeita.
3. **Auto-scaling** ajuda em alguns picos, mas **não** resolve *volumetric* maior que o pipe ou custo infinito — precisa mitigação na borda.
4. **Cache** e **páginas estáticas** reduzem carga no *origin*.
5. **WAF** com regras para padrões óbvios; listas de ASNs / *geoblock* quando aceitável ao negócio.
6. **Plano operacional:** contacto com ISP/cloud, comunicação interna, **não** pagar *ransom* a extorsionadores DDoS (ilegalidade e reincidência).

### 2.4 DDoS ≠ SQLi

SQLi é **exploração de lógica** na aplicação; DDoS é **abuso de volume** ou **estado** na rede/app. Ambos atacam **disponibilidade** ou **dados**, mas mitigações diferem.

---

## 3. Checklist rápido para revisão de código

- [ ] Nenhuma query SQL construída com `+` / `String.format` a partir de input HTTP.  
- [ ] Credenciais de BD não na imagem Docker nem no Git.  
- [ ] Endpoints públicos com *timeout* e limite de corpo de pedido.  
- [ ] *Health checks* separados de trabalho pesado (evitar que *LB* martele rotas caras).

---

## 4. Onde praticar em Java

- **`basics-storage`** → conceito JDBC.  
- **`java11`** → `HttpClient` (timeouts, cabeçalhos).  
- OWASP: [SQL Injection](https://owasp.org/www-community/attacks/SQL_Injection), [*Denial of Service* cheat sheet](https://cheatsheetseries.owasp.org/cheatsheets/Denial_of_Service_Cheat_Sheet.html).
