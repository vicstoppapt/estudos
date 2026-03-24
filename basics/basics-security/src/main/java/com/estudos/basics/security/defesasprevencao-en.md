# Forms of defense and prevention (architect view)

**PT:** [defesasprevencao.md](defesasprevencao.md)

**`basics-security`**. Human factor: [engenhariasocialgolpes-en.md](engenhariasocialgolpes-en.md). Application/infra: [injecaosqleddos-en.md](injecaosqleddos-en.md).

---

## 1. CIA triad (goals)

| Pillar | Question |
|--------|----------|
| **Confidentiality** | Only those who should see it, see it? |
| **Integrity** | Data not altered by unauthorized parties? |
| **Availability** | Service reachable when needed? |

*Trade-offs:* encrypting everything can cost latency; *rate limiting* protects availability but may block legitimate users if miscalibrated.

---

## 2. Defense in depth

Several **independent** layers: if one fails, others still limit damage.

```text
Internet → WAF/CDN → balanceador → firewall → aplicação → BD
              ↓           ↓            ↓           ↓         ↓
         DDoS/mitiga   ACL/rede    AuthZ/MFA   validação  least priv
```

Do not rely only on the **perimeter** or only on **code**.

---

## 3. Control types (by function)

| Type | Examples |
|------|----------|
| **Preventive** | MFA, TLS, *input validation*, *patching*, policies |
| **Detective** | IDS/IPS, logs, SIEM, traffic anomalies |
| **Corrective** | *Backups*, *rollback*, *incident response*, node isolation |

Frameworks like **NIST CSF** (Identify, Protect, Detect, Respond, Recover) structure mature programs.

---

## 4. Cross-cutting principles

- **Least privilege:** service and human accounts with the **minimum** needed (DB, cloud IAM).
- **Network segmentation:** *tiers* (DMZ, app, data); *microsegmentation* in *zero trust*.
- **Assume breach:** monitor **lateral movement**, not only perimeter.
- **Secure by default:** dangerous features off; OS and middleware *hardening*.
- **Shift left:** *threat modelling* and security review **before** production; *SAST/DAST* in the pipeline (see root README — future CI/CD steps).

---

## 5. Incident response (summary)

Written plan: **who** triggers, **how** to preserve evidence, **communication** legal/PR (GDPR, *breach* notification), **lessons learned**. *Tabletop exercises* with engineering and business.

---

## 6. What is **not** enough defense

- “We are on the VPN” without MFA or internal segmentation.  
- **Security through obscurity** alone.  
- Trusting **one** vendor (WAF) to solve all *AppSec*.
