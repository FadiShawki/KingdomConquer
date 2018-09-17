package me.fadishawki.kingdomconquer.utils;

import java.lang.reflect.Array;

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
public class ArrayUtils {

    public static <T> T[] add(Class<?> clazz, T[] array, T... elements) {
        if (array == null || array.length == 0)
            return elements;

        T[] newArray = (T[]) Array.newInstance(clazz, array.length + elements.length);
        System.arraycopy(array, 0, newArray, 0, array.length);

        for (int i = 0; i < elements.length; i++) {
            newArray[array.length + i] = elements[i];
        }

        return newArray;
    }
}
