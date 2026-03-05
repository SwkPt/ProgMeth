package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.interfaces.ColorChange;
import logic.GameState;

public class TimeBomb extends KSKCard implements ColorChange {

    public static final int FUSE_SECONDS = 7;

    public TimeBomb() { super(); }

    @Override
    public boolean isTimeBomb() { return true; }

    @Override
    public void chooseColor(BaseCard.Color color, GameState state) {
        state.setActiveColor(color);
        state.advanceTurn();
    }
}