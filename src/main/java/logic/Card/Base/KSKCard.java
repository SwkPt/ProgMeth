package logic.Card.Base;

import logic.interfaces.ColorChange;
import logic.interfaces.EffectCard;

public abstract class KSKCard extends BaseCard implements EffectCard, ColorChange {

    public KSKCard() {
        super(Color.KSK);
    }
}