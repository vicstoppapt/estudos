# Formas de defesa e prevenção (visão de arquiteto)

**`basics-security`**. Humano: [engenhariasocialgolpes.md](engenhariasocialgolpes.md). Aplicação/infra: [injecaosqleddos.md](injecaosqleddos.md).

---

## 1. Triade CIA (objetivos)

| Pilar | Pergunta |
|-------|----------|
| **Confidencialidade** | Só quem deve ver, vê? |
| **Integridade** | Os dados não foram alterados por não autorizados? |
| **Disponibilidade** | O serviço está acessível quando necessário? |

*Trade-offs:* cifrar tudo pode custar latência; *rate limiting* protege disponibilidade mas pode bloquear legítimos se mal calibrado.

---

## 2. Defesa em profundidade (*defense in depth*)

Várias **camadas** independentes: falha uma, outras ainda reduzem dano.

```text
Internet → WAF/CDN → balanceador → firewall → aplicação → BD
              ↓           ↓            ↓           ↓         ↓
         DDoS/mitiga   ACL/rede    AuthZ/MFA   validação  least priv
```

Não confiar só na **periferia** nem só no **código**.

---

## 3. Tipos de controlo (função)

| Tipo | Exemplos |
|------|----------|
| **Preventivo** | MFA, TLS, *input validation*, *patching*, políticas |
| **Detetivo** | IDS/IPS, logs, SIEM, anomalias de tráfego |
| **Corretivo** | *Backups*, *rollback*, *incident response*, isolamento de nós |

Frameworks como **NIST CSF** (Identificar, Proteger, Detetar, Responder, Recuperar) organizam programas maduros.

---

## 4. Princípios transversais

- **Menor privilégio:** contas de serviço e humanos com o **mínimo** necessário (BD, cloud IAM).
- **Segmentação de rede:** *tiers* (DMZ, app, data); *microsegmentation* em *zero trust*.
- **Assume breach:** monitorizar **lateral movement**, não só perímetro.
- **Secure by default:** features perigosas desligadas; *hardening* de SO e middleware.
- **Shift left:** *threat modelling* e revisão de segurança **antes** de produção; *SAST/DAST* na pipeline (ver README raiz — próximos passos CI/CD).

---

## 5. Resposta a incidentes (resumo)

Plano escrito: **quem** aciona, **como** preservar evidências, **comunicação** legal/PR (RGPD, notificação de *breach*), **lições aprendidas**. *Tabletop exercises* com equipa técnica e negócio.

---

## 6. O que **não** é defesa suficiente

- “Estamos na VPN” sem MFA nem segmentação interna.  
- **Security through obscurity** sozinha.  
- Confiar que **um** fornecedor (WAF) resolve todo o *AppSec*.
