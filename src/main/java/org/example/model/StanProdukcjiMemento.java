package org.example.model;

import java.util.Map;

public class StanProdukcjiMemento {

    private final Map<Long, StatusZadania> statusyZadan;
    private final String nazwaStrategii;

    public StanProdukcjiMemento(Map<Long, StatusZadania> statusyZadan, String nazwaStrategii) {
        this.statusyZadan = Map.copyOf(statusyZadan);
        this.nazwaStrategii = nazwaStrategii;
    }

    public Map<Long, StatusZadania> getStatusyZadan() {
        return statusyZadan;
    }

    public String getNazwaStrategii() {
        return nazwaStrategii;
    }
}
