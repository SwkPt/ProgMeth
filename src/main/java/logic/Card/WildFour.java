package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.WildCard;
import logic.interfaces.ColorChange;
import logic.interfaces.Targetable;
import logic.GameState;

public class WildFour extends WildCard implements ColorChange, Targetable {

    public WildFour() {
        super();
    }

    @Override
    public void chooseColor(BaseCard.Color color, GameState state) {
        state.setActiveColor(color);
        state.drawForPlayer(state.getNextPlayer(), 4);
        state.advanceTurn();
        state.advanceTurn();
    }
}