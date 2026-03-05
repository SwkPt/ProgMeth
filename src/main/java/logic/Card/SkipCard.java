package logic.Card;

import logic.Card.Base.ActionCard;
import logic.GameState;

public class SkipCard extends ActionCard {

    public SkipCard(Color color) {
        super(color);
    }

    @Override
    public void applyEffect(GameState state) {
        // skip next player by advancing twice
        state.advanceTurn();
        state.advanceTurn();
    }
}