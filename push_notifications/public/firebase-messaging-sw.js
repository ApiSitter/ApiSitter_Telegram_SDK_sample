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

