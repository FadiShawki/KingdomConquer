package me.fadishawki.kingdomconquer.controller;

import me.fadishawki.kingdomconquer.algorithm.Kingdom;
import me.fadishawki.kingdomconquer.algorithm.Road;
import me.fadishawki.kingdomconquer.utils.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
public class QuestLoader {

    private final int size;
    private final String name;

    private Kingdom[] kingdoms;
    private Road[] roads;

    public QuestLoader(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public void load() {
        System.out.println();
        System.out.println("Loading Quest named '" + name + "' with " + size + " Kingdoms...");

        if (loadFromFile())
            return;

        QuestGenerator generator = new QuestGenerator(size, name);
        generator.generate();

        this.kingdoms = generator.getKingdoms();
        this.roads = generator.getRoads();
    }

    private boolean loadFromFile() {
        File file = new File(name + "_quest.txt");

        if (!file.exists())
            return false;

        String line = null;

        LoadType loadType = LoadType.values[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    loadType = LoadType.values[loadType.ordinal() + 1];
                    continue;
                }

                String[] components = line.split(" ");

                switch (loadType) {

                    case SIZE:
                        if (size != Integer.parseInt(components[0])) {
                            file.delete();
                            return false;
                        }

                        break;
                    case KINGDOM:
                        int id = Integer.parseInt(components[0]);
                        int kingdomCost = Integer.parseInt(components[1]);

                        this.kingdoms = ArrayUtils.add(Kingdom.class, this.kingdoms, new Kingdom(id, kingdomCost));
                        break;
                    case ROAD:
                        int from = Integer.parseInt(components[0]);
                        int to = Integer.parseInt(components[1]);
                        int roadCost = Integer.parseInt(components[2]);

                        this.roads = ArrayUtils.add(Road.class, this.roads, new Road(kingdoms[from], kingdoms[to], roadCost));
                        break;
                }
            }

            reader.close();

            System.out.println();
            System.out.println("Successfully loaded " + this.kingdoms.length + " Kingdoms and " + this.roads.length + " Roads.");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Kingdom[] getKingdoms() {
        return kingdoms;
    }

    public Road[] getRoads() {
        return roads;
    }

    private enum LoadType {

        SIZE,
        KINGDOM,
        ROAD;

        private static final LoadType[] values = values();

    }
}
