package me.fadishawki.kingdomconquer.controller;

import me.fadishawki.kingdomconquer.algorithm.Kingdom;
import me.fadishawki.kingdomconquer.algorithm.Road;
import me.fadishawki.kingdomconquer.utils.ArrayUtils;
import me.fadishawki.kingdomconquer.utils.RandomUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *
 *    o  o                o                 o-o
 *    | /  o              |                /
 *    OO     o-o  o--o  o-O o-o o-O-o     O     o-o o-o   o-o o  o o-o o-o
 *    | \  | |  | |  | |  | | | | | |      \    | | |  | |  | |  | |-' |
 *    o  o | o  o o--O  o-o o-o o o o       o-o o-o o  o  o-O o--o o-o o
 *                   |                                      |
 *                o--o                                      o
 *
 * Welcome to Kingdom Conquer,
 *
 * In this algorithm challenge you will need to conquer all kingdoms by
 * creating an efficient algorithm that generates the most cost-effective
 * route to conquer all kingdoms.
 *
 * Explanation:
 * - You will start at a kingdom.
 * - Entering a new kingdom, or travelling a road to a new kingdom will cost gold.
 * - When a kingdom is conquered all its adjacent kingdoms will also be conquered.
 * - When all kingdoms are conquered you will return to the starting kingdom.
 *
 * @author Fadi Shawki - 2018
 */
public class QuestGenerator {

    private final int size;
    private final String name;

    private Kingdom[] kingdoms;
    private Road[] roads;

    public QuestGenerator(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public void generate() {
        System.out.println();
        System.out.println("Generating Quest named '" + name + "' with " + size + " Kingdoms...");
        System.out.println();

        this.kingdoms = new Kingdom[size];
        this.roads = new Road[0];

        for (int i = 0; i < size; i++) {
            int cost = RandomUtils.nextInt(5, 20) + 1;
            this.kingdoms[i] = new Kingdom(i, cost);
            System.out.println("Generated Kingdom #" + i + " with an entrance cost of " + cost + ".");
        }

        System.out.println();

        for (Kingdom kingdom : this.kingdoms) {
            int randomRoadCount = RandomUtils.nextInt(2, 5) + 1;
            int currentRoadCount = getRoadCount(kingdom);

            if (currentRoadCount >= randomRoadCount)
                continue;

            List<Kingdom> kingdoms = new ArrayList<>(Arrays.asList(this.kingdoms));
            kingdoms.remove(kingdom);

            for (Road road : roads) {
                if (road.getFrom().getId() == kingdom.getId())
                    kingdoms.remove(road.getTo());
                else if (road.getTo().getId() == kingdom.getId())
                    kingdoms.remove(road.getFrom());
            }

            for (Kingdom kd : new ArrayList<>(kingdoms)) {
                if (getRoadCount(kd) > 5)
                    kingdoms.remove(kd);
            }

            for (int i = 0; i < (randomRoadCount - currentRoadCount); i++) {
                Kingdom random = RandomUtils.randomFrom(kingdoms);
                kingdoms.remove(random);

                int cost = RandomUtils.nextInt(2, 13) + 1;

                this.roads = ArrayUtils.add(Road.class, roads, new Road(kingdom, random, cost));
                System.out.println("Generated Road between Kingdom #" + kingdom.getId() + " and Kingdom #" + random.getId() + " with a cost of " + cost + ".");
            }
        }

        System.out.println();
        System.out.println("Generating Quest File...");
        System.out.println();

        generateFile();

        System.out.println("Successfully created quest with the name '" + name + "'.");
    }

    private int getRoadCount(Kingdom kingdom) {
        int count = 0;

        for (Road road : this.roads) {
            if (road.getFrom().getId() == kingdom.getId() || road.getTo().getId() == kingdom.getId())
                count++;
        }

        return count;
    }

    private void generateFile() {
        File file = new File(name + "_quest.txt");

        try {
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(size + "");
            writer.newLine();

            for (Kingdom kingdom : kingdoms) {
                writer.newLine();
                writer.write(kingdom.getId() + " " + kingdom.getCost());
            }

            writer.newLine();

            for (Road road : roads) {
                writer.newLine();
                writer.write(road.getFrom().getId() + " " + road.getTo().getId() + " " + road.getCost());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Kingdom[] getKingdoms() {
        return kingdoms;
    }

    public Road[] getRoads() {
        return roads;
    }
}
