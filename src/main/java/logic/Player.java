package logic;

import logic.Card.Base.BaseCard;
import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<BaseCard> hand = new ArrayList<>();

    public Player(String name) { this.name = name; }

    public String getName()                  { return name; }
    public ArrayList<BaseCard> getHand()     { return hand; }
    public void addCard(BaseCard card)       { hand.add(card); }
    public void removeCard(int index)        { hand.remove(index); }
    public boolean removeCard(BaseCard card) { return hand.remove(card); }
    public int handSize()                    { return hand.size(); }
    public boolean hasWon()                  { return hand.isEmpty(); }
}