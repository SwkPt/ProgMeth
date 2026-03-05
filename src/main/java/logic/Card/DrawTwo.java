package logic.Card;

import logic.Card.Base.ActionCard;
import logic.GameState;

public class DrawTwo extends ActionCard {

    public DrawTwo(Color color) {
        super(color);
    }

    @Override
    public void applyEffect(GameState state) {
        // next player draws 2 and loses their turn
        state.drawForPlayer(state.getNextPlayer(), 2);
        state.advanceTurn();
        state.advanceTurn();
    }
}