package nl.befrank.services;

import nl.befrank.mappers.DeelnemerMapper;
import nl.befrank.model.*;
import nl.befrank.repositories.DeelnemerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PensioenServiceTest {

    @InjectMocks
    private PensioenService pensioenService;

    @Mock
    private DeelnemerService deelnemerService;
    @Mock
    private DeelnemerRepository deelnemerRepository;
    @Mock
    private PensioenRekeningService rekeningService;
    @Mock
    private DeelnemerMapper deelnemerMapper;


    @Test
    void berekenPrognose() {
        Dienstverband dienstverband = new Dienstverband()
                .franchise(15599d)
                .fulltimeSalaris(60000d)
                .parttimePercentage(80d)
                .premiePercentage(5d)
                .isIndienst(true);
        PensioenRekening pensioenRekening = new PensioenRekening().rekeningnummer("rekening1");
        Deelnemer deelnemer = new Deelnemer().id(1l).achternaam("Jansen").dienstverband(dienstverband).pensioenrekening(pensioenRekening);
        DeelnemerDto deelnemerDto = new DeelnemerDto().id(1l).achternaam("Jansen");

        when(deelnemerRepository.findById(1l)).thenReturn(Optional.of(deelnemer));
        when(deelnemerService.getLeeftijd(deelnemer)).thenReturn(60);
        when(deelnemerMapper.toDto(deelnemer)).thenReturn(deelnemerDto);
        when(rekeningService.getHuidigeWaarde(pensioenRekening)).thenReturn(100000d);

        PensioenPrognose prognose1 = pensioenService.berekenPrognose(1l, 61);
        PensioenPrognose prognose2 = pensioenService.berekenPrognose(1l, 65);

        assertEquals(104802.68, Math.round(prognose1.getVerwachteWaarde() * 100d) / 100d);
        assertEquals(125498.08, Math.round(prognose2.getVerwachteWaarde() * 100d) / 100d);
    }
}