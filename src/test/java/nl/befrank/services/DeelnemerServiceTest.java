package nl.befrank.services;

import nl.befrank.model.Deelnemer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class DeelnemerServiceTest {

    @InjectMocks
    DeelnemerService deelnemerService;
    @Test
    void getLeeftijdBerekentJuisteLeeftijd() {
        //given
        Deelnemer deelnemer = new Deelnemer().geboortedatum(LocalDate.of(1966,1,1));

        //when
        int leeftijd = deelnemerService.getLeeftijd(deelnemer);

        //then
        assertEquals(60, leeftijd);
    }
}