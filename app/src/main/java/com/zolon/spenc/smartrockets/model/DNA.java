package com.zolon.spenc.smartrockets.model;


public class DNA {
    ZVector[] genes;

    private static int lifespan;
    private static double mutationChance;


    DNA() {
        //Creates genes
        genes = new ZVector[lifespan];
        for (int i = 0; i < lifespan; i++) {
            ZVector gene = ZVector.randVector();
            genes[i] = gene;
        }
    }

    DNA(ZVector[] genes) {
        this.genes = genes;
    }

    public static void setLifespan(int lifespan) {
        DNA.lifespan = lifespan;
    }

    public static void setMutation(float mutationChance) {
        DNA.mutationChance = mutationChance;
    }

    public static int getLifespan() {
        return lifespan;
    }

    // Performs a crossover with another member of the species
    public DNA crossover(DNA partner) {
        ZVector[] newgenes = new ZVector[lifespan];
        // Picks random midpoint
        //TODO: add check if random mid point is necessary
        int mid = (int) Math.floor(genes.length/2 + Math.random()*genes.length/2 - genes.length/4);
        boolean isFirst = Math.random() >= 0.49;
        for (int i = 0; i < this.genes.length; i++) {
            //TODO: Add possibility to switch gene side...
            if(isFirst || true) {
                // If i is greater than mid the new gene should come from this partner
                if (i > mid) {
                    newgenes[i] = this.genes[i];
                }
                // If i < mid new gene should come from other partners gene's
                else {
                    newgenes[i] = partner.genes[i];
                }
            }else{
                if (i > mid) {
                    newgenes[i] = partner.genes[i];
                }
                // If i < mid new gene should come from other partners gene's
                else {
                    newgenes[i] = this.genes[i];
                }
            }
        }
        // Gives DNA object an array
        return new DNA(newgenes);
    }

    // Adds random mutation to the genes to add variance.
    public void mutation() {
        for (int i = 0; i < this.genes.length; i++) {
            // if random number less than 0.01, new gene is then random vector
            if (Math.random() < mutationChance) {
                this.genes[i] = new ZVector();
            }
        }
    }
}
