package com.cubejello.conflict.utils;

import java.util.Random;

/**
 * Created by Jake on 8/11/2017.
 */

public class RandomUtils {

    public static float pseudoRandom(float min, float max) {
        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;
    }
}
