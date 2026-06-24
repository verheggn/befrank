package nl.befrank.services;

import nl.befrank.model.Fonds;
import nl.befrank.model.PensioenRekening;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PensioenRekeningServiceTest {

    @InjectMocks
    PensioenRekeningService pensioenRekeningService;

    @Mock
    ExterneRekeningService externeRekeningService;


    @Test
    void getHuidigeWaardeHaaltFondsenOpEnBerekentDeSom() {
        //given
        PensioenRekening rekening1 = new PensioenRekening().rekeningnummer("rekening1");
        PensioenRekening rekening2 = new PensioenRekening().rekeningnummer("rekening2");
        Fonds fonds1a = new Fonds().waarde(50000d);
        Fonds fonds1b = new Fonds().waarde(40000d);
        Fonds fonds2a = new Fonds().waarde(30000d);
        Fonds fonds2b = new Fonds().waarde(40000d);
        Fonds fonds2c = new Fonds().waarde(100000d);
        when(externeRekeningService.getFondsen("rekening1")).thenReturn(List.of(fonds1a, fonds1b));
        when(externeRekeningService.getFondsen("rekening2")).thenReturn(List.of(fonds2a, fonds2b, fonds2c));

        double waarde1 = pensioenRekeningService.getHuidigeWaarde(rekening1);
        double waarde2 = pensioenRekeningService.getHuidigeWaarde(rekening2);

        assertEquals(90000d, waarde1);
        assertEquals(170000d, waarde2);
    }
}