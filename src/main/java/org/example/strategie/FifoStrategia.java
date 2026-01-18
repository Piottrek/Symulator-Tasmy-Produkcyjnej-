package org.example.strategie;

import org.example.model.Zadanie;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FifoStrategia implements StrategiaPlanowania
{

    @Override
    public List<Zadanie> wybierzZadania(List<Zadanie> dostepneZadania)
    {
        return dostepneZadania.stream()
                .sorted(Comparator.comparing(Zadanie::getDataUtworzenia))
                .collect(Collectors.toList());
    }

    @Override
    public String pobierzNazwe() {
        return "FIFO";
    }

}