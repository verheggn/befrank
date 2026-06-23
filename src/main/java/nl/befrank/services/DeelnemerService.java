package nl.befrank.services;

import lombok.RequiredArgsConstructor;
import nl.befrank.model.Deelnemer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DeelnemerService {

    // naive implementatie van leeftijd, we nemen aan dat de deelnemer dit jaar al jarig is geweest
    public static Integer getLeeftijd(Deelnemer deelnemer){
        int geboorteJaar = deelnemer.getGeboortedatum().getYear();
        return LocalDate.now().getYear() - geboorteJaar;
    }
}
