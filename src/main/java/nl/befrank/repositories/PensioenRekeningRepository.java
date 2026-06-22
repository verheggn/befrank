package nl.befrank.repositories;

import nl.befrank.model.PensioenRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PensioenRekeningRepository extends JpaRepository<PensioenRekening, Long> {
}
