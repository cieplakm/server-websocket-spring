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
    socket = new WebSocket('ws://localhost:8080/name_of_handler');
    socket.addEventListener('open', function (event) {
        console.log("Connected!")
        socket.send('Hello Server!');
        setConnected(true);
    });
    // Listen for messages
    socket.addEventListener('message', function (event) {
        console.log('Message from server ', event.data);
        showGreeting( event.data );
    });

    // stompClient = Stomp.over(socket);
    // console.log('-try to connect-');
    // stompClient.connect({}, connect_callback);
}

var connect_callback = function() {

    setConnected(true);
    console.log('-------------->>>>>>>>>>>>>>> Connected: ');
    // stompClient.subscribe('/topic/files', function (greeting) {
    //
    // });
};

function disconnect() {
    if (socket !== null) {
        socket.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    socket.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

