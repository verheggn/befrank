package nl.befrank.services;

import lombok.RequiredArgsConstructor;
import nl.befrank.model.Fonds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExterneRekeningService {

    public List<Fonds> getFondsen(String rekeningnummer) {

        // ToDo: implementatie van aanroep externe API
        return List.of(new Fonds().waarde(40000d), new Fonds().waarde(60000d));
    }
}
