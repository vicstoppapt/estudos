# Social engineering, scams, and prevention

**PT:** [engenhariasocialgolpes.md](engenhariasocialgolpes.md)

**`basics-security`**. Layered defense and process: [defesasprevencao-en.md](defesasprevencao-en.md).

---

## 1. What social engineering is

Manipulating **people** to obtain information, access, or actions (clicks, transfers, *malware* install). Exploits **trust**, **urgency**, **authority**, and **fear** — not only technical flaws.

---

## 2. Common forms

| Technique | Description | Example |
|-----------|-------------|---------|
| **Phishing** | Fake email/SMS/site mimicking a known brand | “Renew your password on this urgent link” |
| **Spear phishing** | Specific target (person or company) with data from *LinkedIn* | Email to CFO with context on project X |
| **Vishing** | Voice (phone call) | “I’m from IT, I need your VPN” |
| **Pretexting** | Fabricate a credible scenario | Fake auditor asks for client list |
| **Baiting** | Physical or digital lure | *USB* labeled “salaries” left in parking lot |
| **CEO fraud / BEC** | Impersonate executive for urgent payment | “Wire to this IBAN, confidential” |

**Scams** mix these vectors with **fake investments**, **romance**, **fake tech support**, **cryptocurrency** recovery, etc.

---

## 3. Prevention — organization

1. **Ongoing training** (*phishing* simulations with feedback, not only punishment).
2. **Financial processes:** **out-of-band** confirmation (known internal phone) for IBAN changes or atypical payments; **four eyes** on large amounts.
3. **Strong MFA** for email, VPN, admin consoles; **policies** discouraging credential sharing.
4. **Minimal disclosure** on sites and socials (who is admin, internal structure, vendors).
5. **Official channel** to report suspicion (SOC, *abuse@*, *security@*).

---

## 4. Prevention — individual

- **Pause** before clicking: real domain? URL matches official site?
- **Do not** confirm sensitive data by phone without **call back** to an **official** number (card, bank, IT).
- **Distrust** extreme urgency, secrets (“don’t tell your boss”), and atypical requests.
- **Unknown hardware** → **do not** plug into work machine.

---

## 5. If you already clicked or shared data

1. Notify **IT / security** immediately.  
2. **Change password** (if applicable) and review active sessions.  
3. **Freeze** transfers if financial.  
4. Document for **post-mortem** without toxic blame — attackers adapt to humans.

---

## 6. Link to technical security

Social engineering **bypasses** MFA if the user **gives** the code; **FIDO2/WebAuthn** (*passkeys*) reduces code *phishing* by being **bound to the domain**. Policies and **CASB** help with SaaS.

Next: [injecaosqleddos-en.md](injecaosqleddos-en.md) (application and infra threats).
