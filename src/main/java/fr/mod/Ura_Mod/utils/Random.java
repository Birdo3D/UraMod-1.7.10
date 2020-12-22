package fr.mod.Ura_Mod.utils;

public class Random {
    public static int Random(int max) {
        java.util.Random randomGen = new java.util.Random();
        return randomGen.nextInt(max);
    }
}
