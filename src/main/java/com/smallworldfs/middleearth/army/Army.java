package com.smallworldfs.middleearth.army;

import com.smallworldfs.middleearth.race.Race;
import java.util.EnumMap;
import java.util.Map;

public class Army<RACE extends Enum<RACE> & Race> {

    private final Map<RACE, Integer> troopsByRace;

    @SuppressWarnings("unchecked")
    private Army(int troopSize, RACE race) {
        troopsByRace = new EnumMap<>((Class<RACE>) race.getClass());
        addTroopSize(troopSize, race);
    }

    public Army<RACE> and(int troopSize, RACE race) {
        addTroopSize(troopSize, race);
        return this;
    }

    public long getArmyStrength() {
        return troopsByRace.keySet().stream().mapToLong(this::getTroopStrength).sum();
    }

    private void addTroopSize(int troopSize, RACE race) {
        if (troopSize <= 0)
            throw new IllegalArgumentException("Cannot add 0 or less troops to an army, duh!");
        troopsByRace.put(race, getTroopSize(race) + troopSize);
    }

    private long getTroopStrength(RACE race) {
        return (long) race.getStrength() * getTroopSize(race);
    }

    private int getTroopSize(RACE race) {
        return troopsByRace.getOrDefault(race, 0);
    }

    public static <RACE extends Enum<RACE> & Race> Army<RACE> of(int initialSize, RACE race) {
        return new Army<>(initialSize, race);
    }
}
