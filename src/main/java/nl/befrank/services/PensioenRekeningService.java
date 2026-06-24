package nl.befrank.services;

import lombok.RequiredArgsConstructor;
import nl.befrank.model.Fonds;
import nl.befrank.model.PensioenRekening;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PensioenRekeningService {

    private final ExterneRekeningService externeRekeningService;
    /**
     * Bereken de huidige waarde van de pensioenrekening op basis van de som van de waardes van alle fondsen
     *
     * @param rekening
     * @return de huidige waarde van de pensioenrekening
     */
    public Double getHuidigeWaarde(PensioenRekening rekening) {
        List<Fonds> fondsen = externeRekeningService.getFondsen(rekening.getRekeningnummer());
        return fondsen.stream().map(Fonds::getWaarde).reduce(0d, Double::sum);
    }
}
