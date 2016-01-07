package webpub;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Nagger {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final Logger log = LoggerFactory.getLogger(Nagger.class);

    @Scheduled(fixedRate = 5000)
    public void currentTime() {
        log.info("Hey World! Its " + dateFormat.format(new Date()) + " now ^^");
    }
}