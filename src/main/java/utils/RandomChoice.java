package utils;

import java.util.Random;

public class RandomChoice {

    public int getNumber(int min, int max) {

        Random random = new Random();

        return random.nextInt(max - min + 1) + min;
    }
}
