package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.WildCard;
import logic.interfaces.EffectCard;
import logic.GameState;

public class WildFour extends WildCard implements EffectCard {

    public WildFour() {
        super();
    }

    @Override
    public void applyEffect(GameState state) {
        // next player draws 4 and loses their turn
        state.drawForPlayer(state.getNextPlayer(), 4);
        state.advanceTurn();
        state.advanceTurn();
    }

    @Override
    public void applyEffect(BaseCard.Color color, GameState state) {
        // only set color — turn already advanced by EffectCard
        state.setActiveColor(color);
    }
}