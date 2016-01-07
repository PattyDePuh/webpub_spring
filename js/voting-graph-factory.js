/* voting-graph-factory */

//Liste aller QuizGraphen in der Präsentation.
var voting_graph_list = []; 

//Initialisiere jeden Canvas, der die Klasse "vote-result" besitzt, mit einem leeren Graphen.
function initVotingGraphs(){
	$(".vote-result").each(function(index){
		voting_graph_list.push(makeVotingGraph($(this)));
	});
}

function makeVotingGraph(canvas){
	var chart = new Chart( canvas, {
		type: 'bar',
		data: {
			labels: ["Votes"],
			datasets: [
				{label: "A",
				backgroundColor: "rgba(150,150,220,1.0)",
				data:[0]},
				{label: "B",
				backgroundColor: "rgba(150,220,150,1.0)",
				data:[0]},
				{label: "C",
				backgroundColor: "rgba(220,220,150,1.0)",
				data:[0]},
				{label: "D",
				backgroundColor: "rgba(220,150,150,1.0)",
				data:[0]}
				]
		},
		options: {
            scales:{
                yAxes:[{
                        ticks:{
                            beginAtZero:true,
                            min:0
                        }
                    }]
            },
            onClick: function(event){
            	console.log("click");
            	getVotingResult(this);
            }
        }
	});
	chart.data.datasets;
	return chart;
}

function getVotingResult(chart){
	$.getJSON('http://vm009.rz.uos.de:8080/summary?callback=?', null, function (results) {
	        console.log(results);
	        chart.data.datasets[0].data = [results.votes[0]];
	        chart.data.datasets[1].data = [results.votes[1]];
	        chart.data.datasets[2].data = [results.votes[2]];
	        chart.data.datasets[3].data = [results.votes[3]];
	        chart.update();
	      });
}

