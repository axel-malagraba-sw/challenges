package com.smallworldfs.middleearth;

import com.smallworldfs.middleearth.army.Army;
import com.smallworldfs.middleearth.battle.Outcome;
import com.smallworldfs.middleearth.race.SauronAdversary;
import com.smallworldfs.middleearth.race.SauronAlly;

/**
 * Enunciado: ¡La Tierra Media está en guerra! En ella lucharán razas leales a Sauron contra otras
 * que no quieren que el mal reine sobre sus tierras. Cada raza tiene asociado un "valor" entre 1 y
 * 5:
 * 
 * <li>Razas good: Pelosos (1), Sureños buenos (2), Enanos (3), Númenóreanos (4), Elfos (5)</li>
 * 
 * <li>Razas bad: Sureños malos (2), Orcos (2), Goblins (2), Huargos (3), Trolls (5)</li>
 * 
 * <p>Crea un programa que calcule el resultado de la batalla entre los 2 tipos de ejércitos:</p>
 * 
 * <li>El resultado puede ser que gane el bien, el mal, o exista un empate. Dependiendo de la suma
 * del valor del ejército y el número de integrantes.</li>
 * 
 * <li>Cada ejército puede estar compuesto por un número de integrantes variable de cada raza.</li>
 * 
 * <li>Tienes total libertad para modelar los datos del ejercicio.</li>
 * 
 * <p>Ej: 1 Peloso pierde contra 1 Orco, 2 Pelosos empatan contra 1 Orco, 3 Pelosos ganan a 1
 * Orco.</p>
 */
public class MiddleEarthChallenge {

    public Outcome simulateBattle(Army<SauronAlly> sauronAllies, Army<SauronAdversary> sauronAdversaries) {
        return mapBattleResult(sauronAdversaries.getArmyStrength() - sauronAllies.getArmyStrength());
    }

    private Outcome mapBattleResult(long result) {
        if (result < 0) {
            return Outcome.SAURON_ALLIES_WIN;
        }
        if (result > 0) {
            return Outcome.SAURON_ADVERSARIES_WIN;
        }
        return Outcome.DRAW;
    }
}
