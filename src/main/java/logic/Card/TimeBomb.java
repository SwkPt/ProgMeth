package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.GameState;

public class TimeBomb extends KSKCard {

    public static final int FUSE_SECONDS = 7;

    public TimeBomb() {
        super();
    }

    @Override
    public boolean isTimeBomb() { return true; }

    @Override
    public void applyEffect(GameState state) {
        // start countdown for next player
        state.setPhase(GameState.Phase.BOMB_COUNTDOWN);
        state.advanceTurn();
    }

    @Override
    public void applyEffect(BaseCard.Color color, GameState state) {
        // only set color — turn already advanced by EffectCard
        state.setActiveColor(color);
    }
}