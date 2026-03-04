package logic.Card;

import logic.Card.Base.BaseCard;

public class NumberCard extends BaseCard {

    private final int number;

    public NumberCard(Color color, int number) {
        super(color);
        this.number = number;
    }

    public int getNumber() { return number; }
}