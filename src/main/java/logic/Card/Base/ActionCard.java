package logic.Card.Base;

import logic.interfaces.EffectCard;

public abstract class ActionCard extends BaseCard implements EffectCard {

    public ActionCard(Color color) {
        super(color);
    }
}