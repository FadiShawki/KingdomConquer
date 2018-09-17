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
public class START_HERE {

    /*
        quest
            #getKindoms -> returns an array of all Kingdoms
            #getRoads -> returns an array of all Roads
            #getSettledIn -> returns the current Kingdom you are settled in
            #startAt -> set the starting Kingdom of the quest
            #travel -> travel a certain road to another Kingdom
            #finish -> use this method when you have conquered all kingdoms and returned to your starting Kingdom

        Good Luck!
     */
    public void init(Quest quest) {
        quest.startAt(quest.getKingdoms()[0]);

        /*
            TODO: Magic
         */

        quest.finish();
    }
}
