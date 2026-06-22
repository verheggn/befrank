package nl.befrank.repositories;

import nl.befrank.model.Deelnemer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeelnemerRepository extends JpaRepository<Deelnemer, Long> {
}

