package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.interfaces.ColorChange;
import logic.interfaces.Targetable;
import logic.GameState;

public class KSKTrade extends KSKCard implements Targetable, ColorChange {

    public KSKTrade() {
        super();
    }

    @Override
    public void chooseColor(BaseCard.Color color, GameState state) {
        state.setActiveColor(color);
        state.advanceTurn();
    }
}