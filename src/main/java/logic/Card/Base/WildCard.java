package logic.Card.Base;

import logic.interfaces.ColorChange;

public abstract class WildCard extends BaseCard implements ColorChange {

    public WildCard() {
        super(Color.WILD);
    }
}