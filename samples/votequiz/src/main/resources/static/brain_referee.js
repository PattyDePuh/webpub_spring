/* brain_referee.js von "static -> referee.html" */

received_votes = 0;

function joinAsReferee(){
	//Verbindungsaufbau
	var socket = new SockJS('/referee');
    stompClient = Stomp.over(socket);            
    stompClient.connect({}, function(frame) {
        showDiv("wait-front");
        //WebSocketListener
        stompClient.subscribe('/channel/all', function(next_move){
            parseMessage(next_move);
        });
    });
}

function startNewRound(){
	//Auswahl an den Server schicken.
	stompClient.send("/quiz/referee", {}, JSON.stringify({ 'stage': 'start' }));
	showDiv("choice-front");
	//Vote-Counter resetten und anzeigen
	received_votes = 0;
	document.getElementById("vote-container").classList.remove("hidden");
	$("#vote-counter").html(received_votes);

}

function showAnswer(letter){
	stompClient.send("/quiz/referee", {}, JSON.stringify({ 'stage': 'solve', 'letter': letter }));
	document.getElementById("vote-container").classList.add("hidden");
	showDiv("wait-front");
}

function parseMessage(message){
	if(JSON.parse(message.body).what == "vote-received"){
		received_votes++;
		$("#vote-counter").html(received_votes);
	}
}

function showDiv(id_name){
	document.getElementById("join-front").classList.add("hidden");
	document.getElementById("wait-front").classList.add("hidden");
	document.getElementById("choice-front").classList.add("hidden");
	document.getElementById(id_name).classList.remove("hidden");
}