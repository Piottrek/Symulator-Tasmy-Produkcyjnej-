package org.example.strategie;

import org.example.model.Zadanie;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriorytetStrategia implements StrategiaPlanowania {

    @Override
    public String pobierzNazwe() {
        return "PRIORYTET";
    }

    @Override
    public List<Zadanie> wybierzZadania(List<Zadanie> zadania) {
        return zadania.stream()
                .sorted(
                        Comparator
                                .comparing(Zadanie::isPriorytet).reversed()
                                .thenComparing(Zadanie::getDataUtworzenia)
                )
                .collect(Collectors.toList());
    }
}
