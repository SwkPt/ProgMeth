package logic.Card;

import logic.Card.Base.ActionCard;
import logic.interfaces.Targetable;

public class SkipCard extends ActionCard implements Targetable {

    public SkipCard(Color color) {
        super(color);
    }
}