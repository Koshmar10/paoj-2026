# KoshBank

Aplicatie bancara dezvoltata in Java care simuleaza operatiunile de baza ale unui sistem bancar.

## 1. Actiuni posibile in sistem

1. **Autentificare utilizator** — login cu username si parola
2. **Inregistrare client nou** — adaugarea unui client nou in sistem de catre un angajat
3. **Deschidere cont bancar** — crearea unui cont nou (SAVINGS / CHECKING) pentru un client
4. **Vizualizare conturi client** — listarea tuturor conturilor unui client, sortate dupa sold
5. **Depunere numerar** — adaugarea unei sume intr-un cont bancar
6. **Retragere numerar** — extragerea unei sume dintr-un cont (cu verificare sold)
7. **Transfer intre conturi proprii** — mutarea unei sume intre doua conturi ale aceluiasi client
8. **Vizualizare istoric tranzactii** — listarea tuturor tranzactiilor unui cont
9. **Blocare card** — schimbarea statusului unui card din ACTIVE in BLOCKED
10. **Cautare client dupa username** — gasirea unui utilizator in sistem dupa username

## 2. Tipuri de obiecte din domeniu

| Clasa | Descriere |
|---|---|
| `User` | Clasa abstracta de baza pentru toti utilizatorii sistemului |
| `Client` | Clasa de baza pentru clienti, detine conturi bancare |
| `IndividualClient` | Client persoana fizica, are CNP |
| `CorporateClient` | Client persoana juridica, are denumire companie si CUI |
| `Employee` | Utilizator cu rol de angajat, efectueaza operatiuni administrative |
| `Account` | Cont bancar cu IBAN, sold si tip (SAVINGS / CHECKING) |
| `Card` | Card bancar atasat unui cont, cu numar, CVV si data expirarii |
| `Transaction` | Tranzactie inregistrata pe un cont (DEPOSIT / WITHDRAWAL) |
| `Notification` | Notificare generata automat pentru utilizator la diverse evenimente |

