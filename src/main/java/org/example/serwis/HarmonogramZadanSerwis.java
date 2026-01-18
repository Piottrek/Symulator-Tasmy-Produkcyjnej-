package org.example.serwis;

import lombok.Getter;
import org.example.model.Zadanie;
import org.example.strategie.StrategiaPlanowania;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HarmonogramZadanSerwis
{

    private final Map<String, StrategiaPlanowania> strategie;
    private StrategiaPlanowania aktualnaStrategia;
    @Getter
    private String nazwaAktualnejStrategii;

    public HarmonogramZadanSerwis(List<StrategiaPlanowania> strategieLista)
    {
        this.strategie = strategieLista.stream()
                .collect(Collectors.toMap(StrategiaPlanowania::pobierzNazwe, s -> s));

        if (!strategieLista.isEmpty())
        {
            this.aktualnaStrategia = strategieLista.get(0);
            this.nazwaAktualnejStrategii = strategieLista.get(0).pobierzNazwe();
        }
    }

    public void ustawStrategie(String nazwaStrategii)
    {
        if (strategie.containsKey(nazwaStrategii))
        {
            this.aktualnaStrategia = strategie.get(nazwaStrategii);
            this.nazwaAktualnejStrategii = nazwaStrategii;
        }
    }

    public List<Zadanie> zaplanujZadania(List<Zadanie> zadania)
    {
        if (aktualnaStrategia == null)
        {
            return zadania;
        }
        return aktualnaStrategia.wybierzZadania(zadania);
    }

}
