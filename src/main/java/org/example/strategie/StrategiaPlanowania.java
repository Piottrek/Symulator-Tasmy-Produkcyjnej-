package org.example.strategie;


import org.example.model.Zadanie;
import java.util.List;

public interface StrategiaPlanowania
{
    List<Zadanie> wybierzZadania(List<Zadanie> dostepneZadania);
    String pobierzNazwe();
}