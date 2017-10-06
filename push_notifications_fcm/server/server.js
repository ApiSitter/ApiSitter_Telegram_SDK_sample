var express = require('express');
var cors = require('cors');
var path = require('path');
var fs = require('fs');
var http = require('http');

var dirSite  = path.join(path.dirname(fs.realpathSync(__filename)), '../public');

var serverWeb = express();
serverWeb.use(cors());
serverWeb.use(express.static(dirSite));

var port = 4001;

http.createServer(serverWeb).listen(port, function () {
    console.log('Started on port: ' + port);
});