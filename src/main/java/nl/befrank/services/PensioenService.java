package nl.befrank.services;

import lombok.RequiredArgsConstructor;
import nl.befrank.model.Deelnemer;
import nl.befrank.model.Dienstverband;
import nl.befrank.model.PensioenPrognose;
import nl.befrank.repositories.DeelnemerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PensioenService {

    private final DeelnemerRepository deelnemerRepository;
    private final DeelnemerService deelnemerService;
    private final PensioenRekeningService rekeningService;

    public PensioenPrognose berekenPrognose(Long deelnemerId, Integer gewenstePensioenLeeftijd) {

        Deelnemer deelnemer = deelnemerRepository.getReferenceById(deelnemerId);
        return berekenPrognose(deelnemer, gewenstePensioenLeeftijd);
    }

    public PensioenPrognose berekenPrognose(Deelnemer deelnemer, Integer gewenstePensioenLeeftijd) {

        Double huidigeWaarde = rekeningService.getHuidigeWaarde(deelnemer.getPensioenrekening());
        PensioenPrognose prognose = new PensioenPrognose().huidigeWaarde(huidigeWaarde);
        int leeftijd = deelnemerService.getLeeftijd(deelnemer);
        if (gewenstePensioenLeeftijd < leeftijd) {
            return prognose.verwachteWaarde(prognose.getHuidigeWaarde());
        } else {
            Dienstverband dienstverband = deelnemer.getDienstverband();
            Double jaarlijkseInleg = berekenJaarlijkseInleg(dienstverband);
            while(leeftijd < gewenstePensioenLeeftijd) {

            }
        }

        return prognose;
    }

    public Double berekenJaarlijkseInleg(Dienstverband dienstverband) {
        if (!dienstverband.getIsIndienst()) {
            return 0d;
        }
        return (dienstverband.getFulltimeSalaris() - dienstverband.getFranchise()) * dienstverband.getParttimePercentage() * dienstverband.getPremiePercentage();
    }
}
