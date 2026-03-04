package logic.Card;

import logic.Card.Base.ActionCard;
import logic.interfaces.Targetable;

public class DrawTwo extends ActionCard implements Targetable {

    public DrawTwo(Color color) {
        super(color);
    }
}