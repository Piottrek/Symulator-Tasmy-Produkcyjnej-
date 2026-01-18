package org.example.model;

import jakarta.persistence.*;
import org.example.obserwator.ObserwatorStatusuZadania;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Zadanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    @Enumerated(EnumType.STRING)
    private StatusZadania status;

    private boolean priorytet;


    private LocalDateTime dataUtworzenia;


    @Transient
    private final List<ObserwatorStatusuZadania> obserwatorzy = new ArrayList<>();

    public void dodajObserwatora(ObserwatorStatusuZadania obserwator) {
        obserwatorzy.add(obserwator);
    }

    public void usunObserwatora(ObserwatorStatusuZadania obserwator) {
        obserwatorzy.remove(obserwator);
    }

    private void powiadomObserwatorow() {
        for (ObserwatorStatusuZadania obserwator : obserwatorzy) {
            obserwator.aktualizuj(this);
        }
    }


    public Zadanie() {
        this.dataUtworzenia = LocalDateTime.now();
        this.status = StatusZadania.NOWE;
    }

    public Zadanie(String nazwa) {
        this();
        this.nazwa = nazwa;
    }

    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public StatusZadania getStatus() {
        return status;
    }

    public void setStatus(StatusZadania status) {
        this.status = status;
        powiadomObserwatorow();
    }

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(LocalDateTime dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }


    public boolean isPriorytet() {
        return priorytet;
    }

    public void setPriorytet(boolean priorytet) {
        this.priorytet = priorytet;
    }


}
