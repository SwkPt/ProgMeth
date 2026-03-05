package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.WildCard;
import logic.GameState;

public class WildNormal extends WildCard {

    public WildNormal() {
        super();
    }

    @Override
    public void applyEffect(BaseCard.Color color, GameState state) {
        // only set color — no advance turn, no extra effect
        state.setActiveColor(color);
    }
}