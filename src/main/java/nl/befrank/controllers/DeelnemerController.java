package nl.befrank.controllers;

import lombok.RequiredArgsConstructor;
import nl.befrank.api.DeelnemersApi;
import nl.befrank.model.PensioenPrognose;
import nl.befrank.services.PensioenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeelnemerController implements DeelnemersApi {

    private final PensioenService pensioenService;

    @Override
    public ResponseEntity<PensioenPrognose> berekenPensioenprognose(
            Long deelnemerId,
            Integer gewenstePensioenLeeftijd) {

        PensioenPrognose prognose =
                pensioenService.berekenPrognose(deelnemerId, gewenstePensioenLeeftijd);

        return ResponseEntity.ok(prognose);
    }
}
