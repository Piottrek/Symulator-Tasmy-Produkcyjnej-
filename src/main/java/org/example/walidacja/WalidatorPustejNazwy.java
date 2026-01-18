package org.example.walidacja;

public class WalidatorPustejNazwy extends WalidatorZadania {

    @Override
    protected void sprawdz(String nazwa) {
        if (nazwa == null || nazwa.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa zadania nie może być pusta!");
        }
    }
}