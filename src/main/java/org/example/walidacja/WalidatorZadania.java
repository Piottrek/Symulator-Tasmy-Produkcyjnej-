package org.example.walidacja;

public abstract class WalidatorZadania {

    protected WalidatorZadania nastepny;


    public WalidatorZadania ustawNastepny(WalidatorZadania nastepny) {
        this.nastepny = nastepny;
        return nastepny;
    }

    public void waliduj(String nazwa) {

        sprawdz(nazwa);


        if (nastepny != null) {
            nastepny.waliduj(nazwa);
        }
    }

    protected abstract void sprawdz(String nazwa);
}