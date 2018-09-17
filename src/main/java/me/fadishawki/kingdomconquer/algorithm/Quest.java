package me.fadishawki.kingdomconquer.algorithm;

import com.sun.istack.internal.NotNull;
import me.fadishawki.kingdomconquer.controller.QuestLoader;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
public class Quest {

    private final String name;

    private final Kingdom[] kingdoms;
    private final Road[] roads;

    private Kingdom startedIn;

    private Kingdom settledIn;
    private int cost;

    private START_HERE algorithm;

    public Quest(int size, String name) {
        this.name = name;

        System.out.println();
        System.out.println("    o  o                o                 o-o");
        System.out.println("    | /  o              |                /");
        System.out.println("    OO     o-o  o--o  o-O o-o o-O-o     O     o-o o-o   o-o o  o o-o o-o");
        System.out.println("    | \\  | |  | |  | |  | | | | | |      \\    | | |  | |  | |  | |-' |");
        System.out.println("    o  o | o  o o--O  o-o o-o o o o       o-o o-o o  o  o-O o--o o-o o");
        System.out.println("                   |                                      |");
        System.out.println("                o--o                                      o");
        System.out.println();

        QuestLoader loader = new QuestLoader(size, name);
        loader.load();

        kingdoms = loader.getKingdoms();
        roads = loader.getRoads();

        start();
    }

    public Kingdom[] getKingdoms() {
        return kingdoms;
    }

    public Road[] getRoads() {
        return roads;
    }

    public Kingdom getStartedIn() {
        return startedIn;
    }

    public Kingdom getSettledIn() {
        return settledIn;
    }

    public void startAt(@NotNull Kingdom kingdom) {
        if (startedIn != null) {
            stop("There is already a starting Kingdom assigned.");
        }

        this.startedIn = kingdom;
        this.settledIn = kingdom;
        this.cost = kingdom.getCost();

        System.out.println();
        System.out.println("Starting algorithm at Kingdom #" + kingdom.getId() + "...");

        this.settledIn.setConquered(this);
    }

    public void travel(@NotNull Road road) {
        if (startedIn == null) {
            stop("There is no starting Kingdom assigned.");
        }

        if (road.getFrom().getId() != settledIn.getId()) {
            stop("This road is not connected to Kingdom #" + settledIn.getId() + "!");
            return;
        }

        System.out.println("[TRAVEL LOG] #" + road.getFrom().getId() + " -> #" + road.getTo().getId() + " (Cost: " + road.getCost() + ")");

        this.settledIn = road.getTo();
        this.cost += road.getCost() + road.getTo().getCost();

        this.settledIn.setConquered(this);
        for (Kingdom adjacent : getAdjacentKingdoms(this.settledIn)) {
            adjacent.setConquered(this);
        }
    }

    public boolean conqueredAllKingdoms() {
        for (Kingdom kingdom : kingdoms) {
            if (!kingdom.isConquered())
                return false;
        }
        return true;
    }

    public List<Kingdom> getAdjacentKingdoms(Kingdom kingdom) {
        List<Kingdom> list = new ArrayList<>();

        for (Road road : roads) {
            if (road.getFrom().getId() == kingdom.getId())
                list.add(road.getTo());
            else if (road.getTo().getId() == kingdom.getId())
                list.add(road.getFrom());
        }

        return list;
    }

    public void finish() {
        if (startedIn == null) {
            stop("There is no starting Kingdom assigned.");
        }

        if (!conqueredAllKingdoms()) {
            List<Integer> notConquered = new ArrayList<>();
            for (Kingdom kingdom : kingdoms) {
                if (!kingdom.isConquered())
                    notConquered.add(kingdom.getId());
            }

            stop("Not all Kingdoms are conquered! The following Kingdoms weren't conquered: " + Arrays.toString(notConquered.toArray()));
        }

        if (settledIn.getId() != startedIn.getId()) {
            stop("You have to return to the starting Kingdom when your conquered all Kingdoms.");
        }

        System.out.println();
        System.out.println("    o  o                o                 o-o");
        System.out.println("    | /  o              |                /");
        System.out.println("    OO     o-o  o--o  o-O o-o o-O-o     O     o-o o-o   o-o o  o o-o o-o");
        System.out.println("    | \\  | |  | |  | |  | | | | | |      \\    | | |  | |  | |  | |-' |");
        System.out.println("    o  o | o  o o--O  o-o o-o o o o       o-o o-o o  o  o-O o--o o-o o");
        System.out.println("                   |                                      |");
        System.out.println("                o--o                                      o");
        System.out.println();
        System.out.println();
        System.out.println("Finished Quest with name '" + this.name + "' with " + kingdoms.length + " and " + roads.length + " Roads.");
        System.out.println();
        System.out.println("You have conquered all kingdoms at a total cost of " + NumberFormat.getNumberInstance(Locale.US).format(cost) + ".");
    }

    private void start() {
        System.out.println();
        System.out.println("Starting algorithm...");

        this.algorithm = new START_HERE();
        this.algorithm.init(this);
    }

    private void stop(String reason) {
        System.out.println();
        System.out.println("The algorithm stopped with the reason:");
        System.out.println(reason);

        System.exit(1);
    }
}
