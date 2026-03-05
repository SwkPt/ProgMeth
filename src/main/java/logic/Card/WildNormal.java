package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.WildCard;
import logic.interfaces.ColorChange;
import logic.GameState;

public class WildNormal extends WildCard implements ColorChange {

    public WildNormal() { super(); }

    @Override
    public void chooseColor(BaseCard.Color color, GameState state) {
        state.setActiveColor(color);
        state.advanceTurn();
    }
}