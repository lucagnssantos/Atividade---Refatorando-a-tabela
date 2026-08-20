package br.pucpr.planet;

import br.pucpr.table.TableData;
import java.util.ArrayList;
import java.util.List;

public class PlanetasTableData implements TableData {
    private final ArrayList<Planet> planets;

    public PlanetasTableData(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public List<String> getHeaders() {
        return List.of("nome", "diametro", "dist. sol (km)", "dist. sol (ua)", "tipo");
    }

    @Override
    public List<List<String>> getRows() {
        var rows = new ArrayList<List<String>>();
        for (var planet : planets) {
            if (planet == null) continue;
            rows.add(List.of(
                    formatName(planet.name()),
                    String.format("%,.1f", planet.diameterKm()),
                    String.format("%,d", planet.sunDistanceKm()),
                    String.format("%.2f", Planet.kmToAu(planet.sunDistanceKm())),
                    formatType(planet.type())));
        }
        return rows;
    }

    private static String formatName(String name) {
        if (name.length() > 20) {
            name = name.substring(0, 17) + "...";
        }
        return name;
    }

    private static String formatType(PlanetType type) {
        return switch (type) {
            case ROCK -> "Rochoso";
            case GAS -> "Gasoso";
            case ICE -> "Gelado";
            case DWARF -> "Anão";
        };
    }
}
