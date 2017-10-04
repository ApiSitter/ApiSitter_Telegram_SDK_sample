# Guida esempio push notification di Firebase

## Avviare il server

1) Aprire il terminale e muoversi nella cartella **/server**

2) Digitare il comando:
    > sudo npm install
   
3) Avviare il server con il comando:
    > sudo node server.js

Il server verrà aperto sulla porta **4001** e quindi, la pagina di test sarà visibile all'indirizzo **http://localhost:4001/**.

## Setting di Firebase

1) Aprire il file **/public/index.html**

2) Sovrascrivere la variabile `config` con i valori reperibili nella console del tuo progetto firebase in **settings>Impostazioni progetto>generale**
   e cliccando, nella sezione **Le tue appplicazioni**, sulla voce **Aggiungi Firebase all'applicazione web**
   
3) Aprire il file **/public/firebase-messaging.sw.js**

4) Sovrascrivere la variabile `config` con i valori reperibili nella console del tuo progetto firebase in **settings>Impostazioni progetto>generale**
   e cliccando, nella sezione **Le tue appplicazioni**, sulla voce **Aggiungi Firebase all'applicazione web**
   
## Inviare notifiche push di test usando *curl*

Digitare dal terminale il seguente comando:

```
curl --header "Authorization: key=<YOUR_SERVER_KEY>" --header "Content-Type: application/json" -d '{"to": "<YOUR_GENRATED_API_TOKEN>", "notification": {"title": "Hello", "body": "World", "icon": "/logo.png"}}' https://fcm.googleapis.com/fcm/send
```

Dobbiamo sostituire **<YOUR_SERVER_KEY>** e **<YOUR_GENRATED_API_TOKEN>** in questo modo:

- **<YOUR_SERVER_KEY>** va copiata dalla console di Firebase, nel percorso **settings>Impostazioni progetto>Cloud messaging** ed è la prima voce della tabella

- **<YOUR_GENRATED_API_TOKEN>** va copiata dalla console del browser all'esecuzione di **index.html** (http://localhost:4001/)

Fatto ciò possiamo inviare notifiche push al browser.

## Passi seguiti per la creazione dell'esempio

Siamo partiti dal video https://www.youtube.com/watch?time_continue=493&v=BsCBCudx58g della guida di Firebase (https://firebase.google.com/docs/cloud-messaging/js/client).

I passi seguiti sono i seguenti

- Abbiamo creato il file manifest.json così composto (ricorda di tenere questo valore di gcm_sender_id):
```json
{
  "gcm_sender_id": "103953800507",
  "permissions": [ "gcm","storage","pushMessaging","notifications"]
}
```
- Abbiamo creato il file index.html, in cui abbiamo inserito l'importazione di Firebase con:
 ```html
 <script src="https://www.gstatic.com/firebasejs/4.5.0/firebase-app.js"></script>
 <script src="https://www.gstatic.com/firebasejs/4.5.0/firebase-messaging.js"></script>
 <link rel="manifest" href="/manifest.json">
```
- Abbiamo inizializzato Firebase e la messaging di Firebase con (prendendo `config` dalla console del nostro progetto):
```javascript
var config = {
    apiKey: "AIzaSyDjZzTwicXvJX_CiSiTtj3TyBxuVge7RYE",
    authDomain: "telegramserver-b82eb.firebaseapp.com",
    databaseURL: "https://telegramserver-b82eb.firebaseio.com",
    projectId: "telegramserver-b82eb",
    storageBucket: "telegramserver-b82eb.appspot.com",
    messagingSenderId: "313388324591"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();
```

- Abbiamo aggiunto, nello stesso script, la richiesta del permesso di ricezione di notifiche e alla conferma otteremo il token associato all'autorizzazione:
```javascript
messaging.requestPermission()
.then(function() {
    console.log('Notification permission granted.');
    return messaging.getToken();
})
.then(function(token){
    console.log(token);
})
.catch(function(err) {
    console.log('Unable to get permission to notify.', err);
});
```

- Infine abbiamo aggiunto la sottoscrizione alla ricezione di messaggi con:
```javascript
messaging.onMessage(function(payload){
    console.log("onMessage: ", payload);
});
```

- Per ricevere le notifiche anche in background abbiamo creato il file firebase-messaging-sw.js con questo codice:
```javascript
importScripts('https://www.gstatic.com/firebasejs/4.5.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.5.0/firebase-messaging.js');

var config = {
    apiKey: "AIzaSyDjZzTwicXvJX_CiSiTtj3TyBxuVge7RYE",
    authDomain: "telegramserver-b82eb.firebaseapp.com",
    databaseURL: "https://telegramserver-b82eb.firebaseio.com",
    projectId: "telegramserver-b82eb",
    storageBucket: "telegramserver-b82eb.appspot.com",
    messagingSenderId: "313388324591"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();
```