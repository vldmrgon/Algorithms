package com.my.pro;

import java.util.concurrent.Callable;

interface Reptile {
    ReptileEgg lay();
}

public class FireDragon implements Reptile {

    public FireDragon() {
    }

    public static void main(String[] args) throws Exception {
        FireDragon fireDragon = new FireDragon();
        System.out.println(fireDragon instanceof Reptile);
    }

    @Override
    public ReptileEgg lay() {
        return new ReptileEgg(()->new FireDragon());
    }
}

class ReptileEgg {
    public ReptileEgg(Callable<Reptile> createReptile) {
        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public Reptile hatch() throws Exception {
        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

}