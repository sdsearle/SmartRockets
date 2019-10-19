package com.zolon.spenc.smartrockets.model;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Population {

    // Amount of rockets
    private static int popsize = 250;
    // Array of rockets
    private Rocket[] rockets = new Rocket[popsize];
    // Amount parent rocket partners
    private ArrayList<Rocket> matingpool;


    public Population(){
        // Associates a rocket to an array index
        for(int i = 0; i< popsize; i++){
            rockets[i] = new Rocket();
        }
    }

    public static void setPopSize(int population) {
        popsize = population;
    }

    public Rocket[] getRockets() {
        return rockets;
    }


    public void evaluate(Barrier barrier) {

        double maxfit = 0;
        // Iterate through all rockets and calcultes their fitness
        for (int i = 0; i < popsize; i++) {
            // Calculates fitness
            this.rockets[i].calcFitness(barrier );
            // If current fitness is greater than max, then make max equal to current
            if (this.rockets[i].getFitness() > maxfit) {
                maxfit = rockets[i].getFitness();
            }
        }
        // Normalises fitnesses
        for (int i = 0; i < popsize; i++) {
            if (rockets != null && rockets[i] != null) {
                rockets[i].normaliseFitness(maxfit);
            }
        }

        matingpool = new ArrayList<Rocket>();
        // Take rockets fitness make in to scale of 1 to 100
        // A rocket with high fitness will highly likely will be in the mating pool
        for (int i = 0; i < popsize; i++) {
            int n = (int) (this.rockets[i].getFitness() * 100);
            for (int j = 0; j < n; j++) {
                this.matingpool.add(this.rockets[i]);
            }
        }
    }
    // Selects appropriate genes for child
  public void selection(){
        Rocket[] newRockets = new Rocket[popsize];
        for (int i = 0; i < rockets.length; i++) {
            // Picks random dna
            DNA parentA = matingpool.get((int) (Math.random()*matingpool.size())).dna;
            DNA parentB = matingpool.get((int) (Math.random()*matingpool.size())).dna;
            // Creates child by using crossover function
            DNA child = parentA.crossover(parentB);
            child.mutation();
            // Creates new rocket with child dna
            newRockets[i] = new Rocket(child);
        }
        // This instance of rockets are the new rockets
        this.rockets = newRockets;
    }

    //update each rocket
    public void update(ZVector windowSize, int count, Barrier barrier) {
        for (Rocket rocket :
                rockets) {
            rocket.update(windowSize, count, barrier);
        }
    }

    //Draw each rocket
    public void drawRockets(Canvas canvas) {
        for (Rocket rocket :
                rockets) {
            rocket.draw(canvas);
        }
    }
}
