package models.decks;

import models.cards.Card;

import java.util.ArrayList;

public abstract class Deck {
    protected ArrayList<Card> availableCards;
    private ArrayList<Card> discardedCards;
    private final Boolean canRefill;
    private final int deckSize;

    /**
     * Generate cards. Implemented in the sub classes.
     */
    abstract void generateCards ();


    /**
     * Refill from discarded.
     */
    public void refillFromDiscarded () {
        for (Card card : discardedCards) {
            Card toAdd = discardedCards.remove(0);
            availableCards.add(toAdd);
        }
    }

    /**
     * Gets deck size.
     *
     * @return the deck size
     */
    public int getDeckSize() {
        return deckSize;
    }

    /**
     * Get number of remaining cards.
     *
     * @return the int
     */
    public int getRemainingCards (){
        return availableCards.size();
    }

    /**
     * Pick a card.
     *
     * @return A card
     */
    public Card pick () {
        if (availableCards.isEmpty()) return null;
        Card picked = availableCards.remove(0);

        if (availableCards.isEmpty() && canRefill) {
            refillFromDiscarded();
        }

        return picked;
    }

    /**
     * Discard.
     *
     * @param card the card
     */
    public void discard (Card card) {
        discardedCards.add(card);
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