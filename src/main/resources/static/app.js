var socket = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    socket = new WebSocket('ws://192.168.99.100:8080/name_of_handler');
    socket.addEventListener('open', function (event) {
        console.log("Connected!");
        socket.send('Hello Server!');
        connect_callback();
    });
    // Listen for messages

    socket.addEventListener('message', function (event) {
        console.log('Message from server ', event.data);

        var json = JSON.parse(event.data);


        if (json.order.valueOf() === "clear"){
            console.log('Order to: ', json.order);
            clear();
        }else {
            showGreeting( event.data );
        }

    });

    // stompClient = Stomp.over(socket);
    // console.log('-try to connect-');
    // stompClient.connect({}, connect_callback);
}

var connect_callback = function() {
    setConnected(true);
    console.log('--> Connected: ');
};

function disconnect() {
    if (socket !== null) {
        socket.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function clear() {
    $("#greetings").empty();
}

function sendName() {
    socket.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    var json = JSON.parse(message);
    if (json.data.valueOf() === "nie"){
        $("#greetings").append("<tr><td></td><td>" + "File name: " + json.data + "</td></tr>");
    }else {
        $("#greetings").append("<tr><td>" + "File name: " + json.data + "</td></tr>");

    }
}

$(function () {
    $("#conversation").hide();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

