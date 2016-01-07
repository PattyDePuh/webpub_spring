/* brain.js vom "votequiz -> index.html" */

//Flag für die selbst gewählte Variable
var own_choice = 'Z';

//Referenz zur Schnittstelle
var stompClient = null;

//Der Buzzer-Sound !!!
var wrooong = new Audio('Wrong-answer-sound-effect.mp3');

function join(){
	//Verbindungsaufbau
	var socket = new SockJS('/candidate');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        showDiv("wait-front");
        console.log('Connected: ' + frame);
        //WebSocketListener
        stompClient.subscribe('/channel/all', function(next_move){
            parseNextMove(next_move);
        });
    });
}

function choose(choice){
	own_choice = choice;
	$("#your-choice").html(own_choice);
	document.getElementById("result-container").classList.add("hidden");
	showDiv("result-front");
	//Auswahl an den Server schicken.
	stompClient.send("/quiz/candidate", {}, JSON.stringify({ 'choice': choice }));
}

function showDiv(id_name){
	document.getElementById("join-front").classList.add("hidden");
	document.getElementById("wait-front").classList.add("hidden");
	document.getElementById("choice-front").classList.add("hidden");
	document.getElementById("result-front").classList.add("hidden");
	document.getElementById(id_name).classList.remove("hidden");
}

function parseNextMove(next_move){
	switch(JSON.parse(next_move.body).what){
		case "standby":
			showDiv("wait-front");
			break;
		case "choice":
			showDiv("choice-front");
			break;
		case "result":
			showDiv("result-front");
			var letter_result = JSON.parse(next_move.body).letter;
			$("#result-letter").html(letter_result);
			//Icons, ob richtig oder falsch.
			if(letter_result == own_choice){
				document.getElementById("you-are-right").classList.remove("hidden");
				document.getElementById("you-are-wrong").classList.add("hidden");
			}else{
				document.getElementById("you-are-right").classList.add("hidden");
				document.getElementById("you-are-wrong").classList.remove("hidden");
				wrooong.play();
			}
			document.getElementById("result-container").classList.remove("hidden");
			break;
	}

}