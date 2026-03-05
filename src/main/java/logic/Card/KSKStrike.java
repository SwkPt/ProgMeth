package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.interfaces.ColorChange;
import logic.GameState;

public class KSKStrike extends KSKCard implements ColorChange {

    public KSKStrike() { super(); }

    @Override
    public void chooseColor(BaseCard.Color color, GameState state) {
        state.setActiveColor(color);
        state.advanceTurn();
    }
}