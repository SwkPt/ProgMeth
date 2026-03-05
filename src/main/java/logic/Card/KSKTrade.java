package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.interfaces.ColorChange;
import logic.GameState;

public class KSKTrade extends KSKCard implements ColorChange {

    public KSKTrade() { super(); }

    @Override
    public void chooseColor(BaseCard.Color color, GameState state) {
        state.setActiveColor(color);
        state.advanceTurn();
    }
}