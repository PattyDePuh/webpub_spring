package quiz;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//Einrichtung des WebsocketNamespaces
		config.enableSimpleBroker("/channel");
		//Präfix für die eingehenden Websocket-Controller
		config.setApplicationDestinationPrefixes("/quiz");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//Deklariere die Websocket-Endpunkte
		//"quiz/candidate" und "quiz/referee"
		registry.addEndpoint("/player", "/referee").withSockJS();
	}

}