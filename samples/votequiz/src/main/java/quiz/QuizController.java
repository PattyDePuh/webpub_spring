package quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Integer;
import java.lang.Character;
import java.sql.Types;

@Controller
public class QuizController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	//Verarbeite die Antwort der Kandidaten
    @MessageMapping("/player")
    @SendTo("/channel/all")
    public ServerMessage getVote(PlayerVote vote) throws Exception {
    	String letter = vote.getChoice();
    	log.info("Erhalte Voting vom Kandidaten: '" + letter + "'");

        //Mit JDBC und Param-Bindung das Voting abspeichern.
        String query = "UPDATE votes SET counter = counter + 1 WHERE option = ?";
        Object[] params = { letter };
        int[] types = { Types.VARCHAR };
    	int effect = jdbcTemplate.update( query, params, types );

        return new ServerMessage("vote-received");
    }


    @MessageMapping("/referee")
    @SendTo("/channel/all")
    public ServerMessage executeRefereeOrder(RefereeOrder order) throws Exception{
    	log.info("Erhalte Order vom Referee");
    	//die Message empfangen und an die Member weitergeben.
    	switch(order.getStage()){
    		case "start":
                //Starte eine neue Fragerunde und lösch hierfür alle vorherigen Votes
				resetVoting();    		
    			return new ServerMessage("choice", 'Z');
    		case "solve":
                //Schick die Lösung an die Spieler
    			return new ServerMessage("result", order.getLetter());
    	}
    	return new ServerMessage("standby");
    }


    public void resetVoting(){
    	//Resette die Counter
    	jdbcTemplate.execute("DROP TABLE votes IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE votes(" +
                "option CHAR(1), counter INT(2048))");
        jdbcTemplate.update("INSERT INTO votes(option, counter) VALUES ('A', 0), ('B', 0), ('C', 0), ('D', 0)");
    }

}