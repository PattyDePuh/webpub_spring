package webpub;

import org.springframework.data.repository.Repository;

import java.util.List;

interface GreetingsRepository extends Repository<Greeting, Long> {
	
	//Folgende Methoden sollen zur Verfügung stehen:
	//Speichen einer Entity
	void save(Greeting entity);
	//Zugriff auf alle abgespeicherten Entities im Repo
	List<Greeting> findAll();
}