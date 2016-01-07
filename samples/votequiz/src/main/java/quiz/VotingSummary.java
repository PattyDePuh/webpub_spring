package quiz;

import java.util.stream.IntStream;

public class VotingSummary {

    private char[] options;
    private int[] votes;
    private float[] percentages;

    public VotingSummary(char[] options, int[] votes) {
        this.options = options;
        this.votes = votes;
        this.percentages = new float[votes.length];
        //Summe
        int sum = IntStream.of(votes).sum();
        //Ratios
        for(int i = 0; i < percentages.length; i++){
            this.percentages[i] = sum != 0 ? (float)this.votes[i] / sum : 0;
        }
    }

    public char[] getOptions() {
        return options;
    }

    public int[] getVotes(){
    	return votes;
    }

    public float[] getPercentages(){
        return percentages;
    }

}