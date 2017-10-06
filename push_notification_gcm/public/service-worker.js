'use strict';

self.addEventListener('push', function(event) {
    console.log("PUSH ARRIVATA 7");
    var data = event.data.json();
    console.log(data);

    var title = data.title;
    var body = data.description;

    console.log(title);
    console.log(body);

    self.registration.showNotification(title, {
        body: body
    });

    // console.log('Received a push message', event);
    //
    // var data = event.data;
    // console.log(JSON.stringify(data));
    // console.log(JSON.stringify(event.notification));
    //
    //
    //
    //
    // event.waitUntil(
    //
    // );
});

self.addEventListener('notificationclick', function(event) {
    console.log('On notification click: ', event.notification.tag);
    // Android doesn’t close the notification when you click on it
    // See: http://crbug.com/463146
    event.notification.close();

    // This looks to see if the current is already open and
    // focuses if it is
    event.waitUntil(clients.matchAll({
        type: 'window'
    }).then(function(clientList) {
        for (var i = 0; i < clientList.length; i++) {
            var client = clientList[i];
            if (client.url === '/' && 'focus' in client) {
                return client.focus();
            }
        }
        if (clients.openWindow) {
            return clients.openWindow('/');
        }
    }));
});
