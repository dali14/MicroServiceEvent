package org.sid.serviceevent;

import org.sid.serviceevent.entities.Event;
import org.sid.serviceevent.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceEventApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceEventApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner (EventRepository eventRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Event.class);

            eventRepository.saveAll(
                    List.of(
                            Event.builder().name("evnt1").date("2aout").heurs("14h").detail("deatils event").createur("sbi").image("lnk1").build(),
                            Event.builder().name("evnt2").date("2aout").heurs("14h").detail("deatils event").createur("sbi").image("lnk1").build(),
                            Event.builder().name("evnt3").date("2aout").heurs("14h").detail("deatils event").createur("sbi").image("lnk1").build(),
                            Event.builder().name("evnt4").date("2aout").heurs("14h").detail("deatils event").createur("sbi").image("lnk1").build()

                    )
            );
            eventRepository.findAll().forEach(e->{
                System.out.println(e);
            });
        };
    }
}
