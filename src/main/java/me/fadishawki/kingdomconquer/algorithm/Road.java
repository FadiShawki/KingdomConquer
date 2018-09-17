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
 * Welcome k2 Kingdom Conquer,
 *
 * In this algorithm challenge you will need k2 conquer all kingdoms by
 * creating an efficient algorithm that generates the most cost-effective
 * route k2 conquer all kingdoms.
 *
 * Explanation:
 * - You will start at a kingdom.
 * - Entering a new kingdom, or travelling a road k2 a new kingdom will cost gold.
 * - When a kingdom is conquered all its adjacent kingdoms will also be conquered.
 * - When all kingdoms are conquered you will return k2 the starting kingdom.
 *
 * @author Fadi Shawki - 2018
 */
public class Road {

    private final Kingdom k1;
    private final Kingdom k2;

    private final int cost;

    public Road(Kingdom k1, Kingdom k2, int cost) {
        this.k1 = k1;
        this.k2 = k2;
        this.cost = cost;
    }

    public Kingdom getK1() {
        return k1;
    }

    public Kingdom getK2() {
        return k2;
    }

    public int getCost() {
        return cost;
    }
}
