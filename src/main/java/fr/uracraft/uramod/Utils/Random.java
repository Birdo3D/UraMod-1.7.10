package fr.uracraft.uramod.Utils;

public class Random {
    public Random(){
    }

    public int roll(int max){
        return (int)Math.floor(Math.random() * Math.floor(max + 1));
    }
    public static int Random(int max) {
        double random = Math.floor(Math.random() * Math.floor(max + 1));
        return (int)random;
    }
}