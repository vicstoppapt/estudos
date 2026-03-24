# Engenharia social, golpes e prevenção

**English:** [engenhariasocialgolpes-en.md](engenhariasocialgolpes-en.md)

**`basics-security`**. Defesa em camadas e processos: [defesasprevencao.md](defesasprevencao.md).

---

## 1. O que é engenharia social

Manipulação de **pessoas** para obter informação, acesso ou acções (cliques, transferências, instalação de *malware*). Explora **confiança**, **urgência**, **autoridade** e **medo** — não depende só de falhas técnicas.

---

## 2. Formas comuns

| Técnica | Descrição | Exemplo |
|---------|-----------|---------|
| **Phishing** | Email/SMS/site falso que imita marca conhecida | “Renove a password no link urgente” |
| **Spear phishing** | Alvo específico (pessoa ou empresa) com dados colhidos no *LinkedIn* | Email ao CFO com contexto do projeto X |
| **Vishing** | Voz (telefonema) | “Sou do IT, preciso da VPN” |
| **Pretexting** | Inventar cenário credível | Falso auditor pede lista de clientes |
| **Baiting** | Isca física ou digital | *USB* “salários” deixada no parque |
| **CEO fraud / BEC** | Impersonar dirigente para pagamento urgente | “Transfere para este IBAN, confidencial” |

**Scams** (golpes) misturam estes vetores com **investimentos falsos**, **romance**, **suporte técnico falso**, **cryptocurrency** recovery, etc.

---

## 3. Prevenção — organização

1. **Formação contínua** (simulações de *phishing* com feedback, não só punição).
2. **Processos financeiros:** confirmação **fora de banda** (telefone interno conhecido) para alteração de IBAN ou pagamentos atípicos; **quatro olhos** em valores altos.
3. **MFA** forte para email, VPN, consolas admin; **políticas** que desincentivam partilha de credenciais.
4. **Menor divulgação** em sites e redes (quem é admin, estrutura interna, fornecedores).
5. **Canal oficial** para reportar suspeitas (SOC, *abuse@*, *security@*).

---

## 4. Prevenção — indivíduo

- **Pausa** antes de clicar: domínio real? URL coincide com o site oficial?
- **Não** confirmar dados sensíveis por telefone sem **call back** a número **oficial** (cartão, banco, IT).
- **Desconfiar** de urgência extrema, segredos (“não conte ao teu chefe”) e pedidos atípicos.
- **Hardware** desconhecido → **não** ligar à máquina de trabalho.

---

## 5. Se já clicaste ou partilhaste dados

1. Avisar **IT / segurança** de imediato.  
2. **Mudar password** (se aplicável) e rever sessões ativas.  
3. **Congelar** transferências se for caso financeiro.  
4. Documentar para **post-mortem** sem culpabilização tóxica — o atacante adapta-se ao humano.

---

## 6. Ligação com segurança técnica

Engenharia social **contorna** MFA se o utilizador **entrega** o código; **FIDO2/WebAuthn** (passkeys) reduz *phishing* de códigos por ser **ligado ao domínio**. Políticas e **CASB** ajudam em SaaS.

Seguinte: [injecaosqleddos.md](injecaosqleddos.md) (ameaças à aplicação e infra).
