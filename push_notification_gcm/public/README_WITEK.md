## Setting di Telegram

Nella pagina https://my.telegram.org/ bisogna entrare nella pagina della propria app di Telegram che deve:

1) Essere, assolutamente, un'**app android**, altrimenti non riceveremmo le gcm.

2) Nella sezione **PUSH-notifications settings > GCM API key** va messa la **Chiave server** che troviamo su 
   **Firebase > Console > Tuo Progetto > Impostazioni progetto > Cloud Messaging**, volendo si può anche usare la 
   **chiave server precedente** (Abbiamo verificato medesimo funzionamento) ma consigliamo quella nuova nel caso si 
   passasse a delle più moderne fcm (Non bisognerebbe far cambiare a tutti gli utenti ApiSitter questo valore).

## Conderazioni sul progetto

- Il progetto così come è strutturato funzione sia su Chrome che si Firefox ma **non su Safari**. Il motivo è che safari 
  non accetta le push notification GCM. 

- Abbiamo aggiunto una cartella /server che contiene un web server in node.js

- Da notare che in manifest.json va specificato il **gcm_sender_id** che lo si trova Firebase > Console > Tuo Progetto >
  Impostazioni progetto > Cloud Messaging ed è il **ID Mittente**
  
- Nel file /config.js va specificato il campo **gcmAPIKey** con la chiave che troviamo in Firebase > Console > 
  Tuo Progetto > Impostazioni progetto > Cloud Messaging nella voce **Chiave Server**
  
- La **subscription** ovvero l'oggetto che poi va passato a Telegram come token per associare le Push a questo client, 
  viene determinata nella funzione **subscribe()** del file /manin.js. Per essere precisi il valore da inviare nel campo
  token è ottenuto parsificando **subscription** in JSON con `subscription.toJSON()` e come token viene messa la stringa
  ottentuta con `JSON.stringify(subscription.toJSON())`.
  
- Una volta ottenuto il token nel passo precedente, possiamo fare l'API **/registerDevice** ad ApiSitter passandoli un 
  come **token_type** il valore **10** (non abbiamo corrispondenza sulla documentazione di telegram ma l'abbiamo 
  determinato analizzando webogram). Questa api può essere effettuta nella funzione **sendSubscriptionToServer()** (per
  ora non fa l'API perchè i server erano giù al momento dei test e la facevamo con postman)
  
- Fatto ciò, il nostro client è in grado ricevere notifiche che vengono lette nel file **service-worker.js** nel primo
  **addListener** (quello sulla push). Quindi attraverso l'oggetto **event.data** possiamo recuperare tutte le info
  della push in arrivo. Ma **bisogna assolutamente parsiicare event.data.json()**, in caso contrario non otteremo mai
  i dati associati alla notifica.
  
- Chi si occupa di visualizzare la notifica sul browser è la funzione **self.registration.showNotification** a cui 
  basterà inviare il titolo (preso dai dati della push) e il corpo della notifica per visualizzarla a schermo.
  
- Il file /demo.js non serve per i nostri scopi ma per una corretta visualizzazione della pagina, quidi può essere 
  tranquillamente eliminato

## Problemi & Soluzioni

- Se si vogliono vedere le modifiche apportate al file **service-worker.js** bisogna pulire tutta la cronologia ma con 
  la pagina di view chiusa. Altrimenti rimarrà sempre lo stesso. Questo era il problema che riscontravamo su **Firefox**.

- Inoltre su **Firefox** se puliamo la cache sembra continuare a funzionare tutto normalmente ma in realtà è come se 
  togliessimo i permessi per la visualizzazione delle push e quindi le notifiche non vengono più catturate.
  
- Quindi per aggiornare bisogna chiudere la vista, pulire la cronoslogia e infine riaprire la vista.

## Considerazioni su Safari

Abbiamo analizzato come potesse webogram mandare le notifiche anche su Safari che non risulta compatibile con le **gcm**.
Analizzando il codice si è visto che la visualizzazione avviane utilizzando le notifiche locali. In sostanza, non 
arriva e quindi non viene gestita una **gcm** ma, il **long pool** (equivalente del nostro socket), quanto riceve uno 
degli aggiornamenti che sarebbero da notificare crea una notifica locale. Quindi è un discorso decisamente diverso dalle
**gcm**. 

Occhio a non farsi trarre in inganno dal fatto che l'aggiornamento, per esempio, del top messagge alla ricezione di un
update di quel tipo, avvenga immediatamente mentre la notifica push con un po' di ritardo. Perchè questo è regolato da 
un semplice timeout, forse per imitare il comportamento degli altri browser dove questo avviane solo perchè la gcm 
arriva con del ritardo.