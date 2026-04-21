var webSocket = new WebSocket(
		"ws://localhost:8080/chat-bot-v4/websocketendpoint");
var echoText = document.getElementById("formText:echoText");
echoText.value = "";
var message = document.getElementById("formText:message");
webSocket.onopen = function(message) {
	wsOpen(message);
};
webSocket.onmessage = function(message) {
	wsGetMessage(message);
};
webSocket.onclose = function(message) {
	wsClose(message);
};
webSocket.onerror = function(message) {
	wsError(message);
};

function wsOpen(message) {
	echoText.value += "Connected ... \n";
}
function wsSendMessage() {
	webSocket.send(message.value);
	echoText.value += message.value + "\n";
	message.value = "";
}
function wsCloseConnection() {
	webSocket.close();
}
function wsGetMessage(message) {
	echoText.value += message.data + "\n";
}
function wsClose(message) {
	echoText.value += "Disconnect ... \n";
}

function wsError(message) {
	echoText.value += "Error ... \n";
}