package me.fadishawki.kingdomconquer.algorithm;

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
public class Kingdom {

    private final int id;

    private final int cost;

    private boolean conquered;

    public Kingdom(int id, int cost) {
        this.id = id;
        this.cost = cost;
        this.conquered = false;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public void setConquered(Quest quest) {
        if (quest.getSettledIn().getId() == id || quest.getAdjacentKingdoms(quest.getSettledIn()).contains(this)) {
            this.conquered = true;
            System.out.println("[CONQUER LOG] #" + id + " has been conquered.");
        }
    }

    public boolean isConquered() {
        return conquered;
    }
}
