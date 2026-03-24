# Segredos, OWASP e supply chain

**English:** [segredosowasp-en.md](segredosowasp-en.md)

**`basics-security`**. Auth: [autenticacaoautorizacao.md](autenticacaoautorizacao.md).

---

## 1. Gestão de segredos

- **Segredos:** passwords de BD, API keys, chaves privadas, tokens de CI.
- **Onde colocar:** variáveis de ambiente injectadas pelo orquestrador, **Vault**, **Secrets Manager** da cloud, *sealed secrets* em Kubernetes — **não** ficheiros versionados nem logs.
- **Rotação:** planear troca periódica e após incidente.

---

## 2. OWASP Top 10 (mapa mental, não a lista oficial completa)

Categorias mudam de edição para edição; ideias recorrentes:

| Risco típico | O que arquitetar |
|--------------|------------------|
| Injeção (SQL, comando, LDAP) | *Prepared statements*, validação, least privilege na BD |
| Quebra de autenticação | MFA, sessões curtas, bloqueio de brute force |
| Exposição de dados sensíveis | TLS, encriptação em repouso, minimização de dados |
| XXE / desserialização insegura | Desligar features perigosas, formatos seguros |
| XSS / CSRF | CSP, tokens anti-CSRF, framework atualizado |
| Configuração errada | *hardening*, imagens mínimas, portas fechadas |
| Componentes vulneráveis | *Dependency scanning*, SBOM, upgrades |
| Logging / monitorização falhos | Não logar segredos; alertas (observabilidade — ver README principal, próximos passos) |

Lista e detalhe: [OWASP Top 10](https://owasp.org/www-project-top-ten/).

---

## 3. Supply chain

- Dependências Maven/npm com **versões fixas** e **verificação** (hashes, *signing* onde existir).
- **SLSA**, *provenance*, pipelines que **não** aceitam PR sem revisão.

---

## 4. Threat modelling (uma frase)

Identifica **ativos**, **adversários**, **superfícies de ataque** e **mitigações** antes de codificar à escala — STRIDE, diagramas de dados.

---

## 5. Aprofundamentos neste submódulo

| Ficheiro | Tema |
|----------|------|
| [defesasprevencao.md](defesasprevencao.md) | CIA, defesa em profundidade, preventivo/detetivo/correctivo, princípios |
| [engenhariasocialgolpes.md](engenhariasocialgolpes.md) | Phishing, BEC, prevenção organizacional e individual |
| [injecaosqleddos.md](injecaosqleddos.md) | SQL injection (exemplos), DDoS (tipos e mitigação), checklist |
| [`SqlInjectionConcept.java`](SqlInjectionConcept.java) | Demo textual *payload* vs `PreparedStatement` |
