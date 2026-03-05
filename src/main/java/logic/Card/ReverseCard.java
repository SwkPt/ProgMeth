package logic.Card;

import logic.Card.Base.ActionCard;
import logic.GameState;

public class ReverseCard extends ActionCard {

    public ReverseCard(Color color) {
        super(color);
    }

    @Override
    public void applyEffect(GameState state) {
        // flip direction, acts as skip with 2 players
        state.reverse();
        if (state.getPlayers().size() == 2) state.advanceTurn();
        state.advanceTurn();
    }
}