## Lanciare il progetto

- In AndroidStudio cliccare su **Tools > Firebase** seguire tutti i passi cambiando eventualmente le chiavi
- registerDevice va fatto con **token_type = 2** e il **token** può essere visto nei log dell'app.


## Considerazioni

- Riusciamo a ricevere la push ma non a prelevare i dati al suo interno.
- Visto che, anche nel caso delle GCM non riusciamo a ricevere i dati della notifica, **useremo le FCM** perchè 
  l'implementazione è decisamente più semplice delle GCM.
