package org.example.serwis;

import org.example.model.StanProdukcjiMemento;
import org.example.model.StatusZadania;
import org.example.model.Zadanie;
import org.example.obserwator.WebSocketPowiadamiacz;
import org.example.repository.ZadanieRepozytorium;
import org.example.walidacja.WalidatorDlugosci;
import org.example.walidacja.WalidatorPustejNazwy;
import org.example.walidacja.WalidatorZadania;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenedzerProdukcjiFasada {

    private final ZadanieRepozytorium repozytorium;
    private final HarmonogramZadanSerwis harmonogram;
    private final WebSocketPowiadamiacz powiadamiacz;

    private final Deque<StanProdukcjiMemento> historia = new ArrayDeque<>();

    private final WalidatorZadania walidatorChain;

    public MenedzerProdukcjiFasada(ZadanieRepozytorium repozytorium,
                                   HarmonogramZadanSerwis harmonogram,
                                   WebSocketPowiadamiacz powiadamiacz) {
        this.repozytorium = repozytorium;
        this.harmonogram = harmonogram;
        this.powiadamiacz = powiadamiacz;


        WalidatorZadania walidatorPusty = new WalidatorPustejNazwy();
        WalidatorZadania walidatorDlugosci = new WalidatorDlugosci();

        walidatorPusty.ustawNastepny(walidatorDlugosci);

        this.walidatorChain = walidatorPusty;
    }


    private void zapiszMemento() {
        Map<Long, StatusZadania> statusy = repozytorium.findAll()
                .stream()
                .collect(Collectors.toMap(Zadanie::getId, Zadanie::getStatus));
        String nazwaStrategii = harmonogram.getNazwaAktualnejStrategii();
        historia.push(new StanProdukcjiMemento(statusy, nazwaStrategii));
    }

    @Transactional
    public void cofnijOstatniaOperacje() {
        if (historia.isEmpty()) return;
        StanProdukcjiMemento memento = historia.pop();
        memento.getStatusyZadan().forEach((id, status) -> {
            repozytorium.findById(id).ifPresent(z -> {
                z.setStatus(status);
                repozytorium.save(z);
                powiadamiacz.aktualizuj(z);
            });
        });
        harmonogram.ustawStrategie(memento.getNazwaStrategii());
    }

    @Transactional
    public Zadanie dodajZadanie(String nazwa, boolean priorytet) {

        walidatorChain.waliduj(nazwa);

        zapiszMemento();

        Zadanie zadanie = new Zadanie();
        zadanie.setNazwa(nazwa);
        zadanie.setStatus(StatusZadania.NOWE);
        zadanie.setDataUtworzenia(LocalDateTime.now());
        zadanie.setPriorytet(priorytet);

        Zadanie zapisane = repozytorium.save(zadanie);
        powiadamiacz.aktualizuj(zapisane);

        return zapisane;
    }
    @Transactional
    public void zmienStatus(Long id, StatusZadania status) {
        zapiszMemento();
        Zadanie zadanie = repozytorium.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono zadania " + id));
        zadanie.setStatus(status);
        repozytorium.save(zadanie);
        powiadamiacz.aktualizuj(zadanie);
    }

    public void zmienStrategie(String nazwa) {
        zapiszMemento();
        harmonogram.ustawStrategie(nazwa);
    }

    public List<Zadanie> pobierzZadania() {
        return harmonogram.zaplanujZadania(repozytorium.findAll());
    }
}