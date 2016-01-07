package quiz;

public class ServerMessage {

    private String what;
    private char letter;

    public ServerMessage(String what) {
        this.what = what;
        this.letter = 'Z';
    }

    public ServerMessage(String what, char letter){
    	this.what = what;
    	this.letter = letter;
    }

    public String getWhat() {
        return what;
    }

    public char getLetter(){
    	return letter;
    }

}