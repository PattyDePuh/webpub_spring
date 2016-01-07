package quiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
class SummaryController{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public VotingSummary getSummary(){
    	//Result-Variablen
    	char[] option_array = new char[4];
    	int[] counter_array = new int[4];
    	//Erhalte alle Votes
    	List<Map<String,Object>> result_list = jdbcTemplate.queryForList("SELECT * FROM votes ORDER BY option");
    	//Pack sie ins VotingSummary
    	int i = 0;
    	for(Map<String,Object> row : result_list){
    		option_array[i] = row.get("option").toString().charAt(0);
    		counter_array[i++] = (int)row.get("counter");
    	}
    	return new VotingSummary(option_array, counter_array);
    }
}