package nl.befrank.services;

import lombok.RequiredArgsConstructor;
import nl.befrank.model.Fonds;
import nl.befrank.model.PensioenRekening;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PensioenRekeningService {

    public Double getHuidigeWaarde(PensioenRekening rekening) {
        List<Fonds> fondsen = getFondsen(rekening.getRekeningnummer());
        return fondsen.stream().map(Fonds::getWaarde).reduce(0d, Double::sum);
    }

    public List<Fonds> getFondsen(String rekeningnummer) {
        // implementatie van aanroep externe API

        return List.of(new Fonds().waarde(40000d), new Fonds().waarde(60000d));
    }
}
