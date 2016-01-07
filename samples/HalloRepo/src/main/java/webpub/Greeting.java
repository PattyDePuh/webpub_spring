package webpub;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Greeting {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String content;

    //Why JPA, Whyy!!!
    protected Greeting(){} 

    public Greeting(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}