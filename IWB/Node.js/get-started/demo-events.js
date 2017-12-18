var events = require('events');
var fs = require('fs');
var eventEmitter = new events.EventEmitter();

var rs = fs.createReadStream('./demo-file1.html');
rs.on('open', function () {
  console.log('The file is open');
});

//Create an event handler:
var myEventHandler = function () {
  console.log('I hear a scream!');
}

//Assign the event handler to an event:
eventEmitter.on('scream', myEventHandler);

//Fire the 'scream' event:
eventEmitter.emit('scream');