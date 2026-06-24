package nl.befrank.services;

import lombok.RequiredArgsConstructor;
import nl.befrank.mappers.DeelnemerMapper;
import nl.befrank.model.Deelnemer;
import nl.befrank.model.Dienstverband;
import nl.befrank.model.PensioenPrognose;
import nl.befrank.repositories.DeelnemerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PensioenService {

    private static final Double RENDEMENT = 0.03;

    private final DeelnemerRepository deelnemerRepository;
    private final DeelnemerService deelnemerService;
    private final PensioenRekeningService rekeningService;
    private final DeelnemerMapper deelnemerMapper;


    /**
     * bereken een pensioenprognose van een deelnemer op basis van deelnemerID en de gewenste pensioenleeftijd
     *
     * @param deelnemerId
     * @param gewenstePensioenLeeftijd
     * @return de pensioenPrognose
     */
    public PensioenPrognose berekenPrognose(Long deelnemerId, Integer gewenstePensioenLeeftijd) {

        Deelnemer deelnemer = deelnemerRepository.findById(deelnemerId).orElseThrow();
        return berekenPrognose(deelnemer, gewenstePensioenLeeftijd);
    }

    /**
     * bereken een pensionprognose van een deelnemer op basis van de gewenste pensioenleeftijd
     *
     * @param deelnemer
     * @param gewenstePensioenLeeftijd
     * @return de pensioenPrognose
     */
    public PensioenPrognose berekenPrognose(Deelnemer deelnemer, Integer gewenstePensioenLeeftijd) {

        Double huidigeWaarde = rekeningService.getHuidigeWaarde(deelnemer.getPensioenrekening());
        int leeftijd = deelnemerService.getLeeftijd(deelnemer);
        Dienstverband dienstverband = deelnemer.getDienstverband();
        Double jaarlijksePremie = berekenJaarlijksePremie(dienstverband);

        Double verwachteWaarde = huidigeWaarde;
        int jaar = leeftijd;
        while(jaar < gewenstePensioenLeeftijd) {
            verwachteWaarde = berekenJaarlijkseGroei(verwachteWaarde, jaarlijksePremie, RENDEMENT);
            jaar++;
        }

        return new PensioenPrognose()
                .deelnemer(deelnemerMapper.toDto(deelnemer))
                .huidigeLeeftijd(leeftijd)
                .gewenstePensioenLeeftijd(gewenstePensioenLeeftijd)
                .huidigeWaarde(huidigeWaarde)
                .jaarlijksePremie(jaarlijksePremie)
                .verwachteWaarde(verwachteWaarde);
    }

    public static Double berekenJaarlijksePremie(Dienstverband dienstverband) {
        if (!dienstverband.getIsIndienst()) {
            return 0d;
        }
        return (dienstverband.getFulltimeSalaris() - dienstverband.getFranchise()) * dienstverband.getParttimePercentage()/100 * dienstverband.getPremiePercentage()/100;
    }

    public static Double berekenJaarlijkseGroei(Double huidigeWaarde, Double jaarlijksePremie, double rendement) {
        return huidigeWaarde + jaarlijksePremie + (huidigeWaarde + jaarlijksePremie/2) * rendement;
    }
}
