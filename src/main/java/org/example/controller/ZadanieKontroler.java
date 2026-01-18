package org.example.controller;

import org.example.model.StatusZadania;
import org.example.serwis.MenedzerProdukcjiFasada;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ZadanieKontroler {

    private final MenedzerProdukcjiFasada fasada;

    public ZadanieKontroler(MenedzerProdukcjiFasada fasada) {
        this.fasada = fasada;
    }

    @PostMapping("/zadania")
    public void dodajZadanie(@RequestParam String nazwa, @RequestParam(required = false, defaultValue = "false") boolean priorytet)
    {
        fasada.dodajZadanie(nazwa, priorytet);
    }


    @PostMapping("/zadania/{id}/status")
    public void zmienStatus(@PathVariable Long id, @RequestParam StatusZadania status) {
        fasada.zmienStatus(id, status);
    }

    @PostMapping("/strategia")
    public void ustawStrategie(@RequestParam String strategia) {
        fasada.zmienStrategie(strategia);
    }

    @GetMapping("/zadania")
    public Object pobierz() {
        return fasada.pobierzZadania();
    }

    @PostMapping("/cofnij")
    public void cofnij() {
        fasada.cofnijOstatniaOperacje();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<String> obslugaBledow(IllegalArgumentException e) {

        return org.springframework.http.ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}


