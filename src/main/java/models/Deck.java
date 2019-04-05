package models;

import java.util.ArrayList;

public abstract class Deck {
    protected ArrayList<Card> availableCards;
    private final Boolean canRefill;
    private final int deckSize;

    /**
     * Generate cards. Implemented in the sub classes.
     */
    abstract void generateCards ();

    /**
     * Gets deck size.
     *
     * @return the deck size
     */
    public int getDeckSize() {
        return deckSize;
    }

    public int getRemainingCards (){
        return availableCards.size();
    }

    /**
     * Pick card.
     *
     * @return A card
     */
    public Card pick () {
        if (availableCards.isEmpty()) return null; // TODO exception? custom?
        Card removed = availableCards.remove(0);

        if (availableCards.isEmpty() && canRefill) {
            generateCards();
        }

        return removed;
    }

    /**
     * Instantiates a new Deck.
     *
     * @param deckSize The deck size
     */
    public Deck (Boolean canRefill, int deckSize) {
        this.canRefill = canRefill;
        this.deckSize = deckSize;
        availableCards = new ArrayList<>();
        generateCards();
    }

}