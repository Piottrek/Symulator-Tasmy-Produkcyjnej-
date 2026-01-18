package org.example.walidacja;

public class WalidatorDlugosci extends WalidatorZadania {

    private static final int MAX_DLUGOSC = 20;

    @Override
    protected void sprawdz(String nazwa) {
        if (nazwa.length() > MAX_DLUGOSC) {
            throw new IllegalArgumentException("Nazwa jest za długa! Limit to " + MAX_DLUGOSC + " znaków.");
        }
    }
}