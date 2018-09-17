package me.fadishawki.kingdomconquer.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
public class RandomUtils {

    public static Random RANDOM = new Random();

    /* Returns a random int between 0 and (max-1), e.g. setting max to 100 will return 0-99 */
    public static int nextInt(int max) {
        return RANDOM.nextInt(max);
    }

    /* Returns a random int between min and max */
    public static int nextInt(int min, int max) {
        return min + nextInt(max - min);
    }

    public static boolean chance(int percentage) {
        return percentage >= 100 || percentage < nextInt(100);
    }

    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    /* Returns a random object from a collection */
    public static <T> T randomFrom(Collection<T> collection) {
        if (collection.isEmpty())
            return null;

        int random = nextInt(collection.size());
        int step = 0;

        for (T obj : collection) {
            if (random == step)
                return obj;
            step++;
        }
        return null;
    }

    /* Returns a random object from an array */
    public static <T> T randomFrom(T... objects) {
        return objects[nextInt(objects.length)];
    }

    public static <T> List<T> randomFrom(List<T> list, int count) {
        List<T> selected = new ArrayList<>();
        List<T> newList = new ArrayList<>(list);

        for (int i = 0; i < count; i++) {
            T obj = randomFrom(newList);
            newList.remove(obj);
            selected.add(obj);
        }

        return selected;
    }
}
