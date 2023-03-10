package org.sid.serviceevent.repository;
import org.sid.serviceevent.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EventRepository extends JpaRepository<Event,Long> {
}
