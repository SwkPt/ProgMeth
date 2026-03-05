package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.GameState;
import logic.Player;

public class KSKStrike extends KSKCard {

    public KSKStrike() {
        super();
    }

    @Override
    public void applyEffect(GameState state) {
        // next player draws 2, hand recycled, redraws original + 2
        Player target       = state.getNextPlayer();
        int    originalSize = target.handSize();
        state.drawForPlayer(target, 2);
        state.recyclePlayerHand(target);
        state.drawForPlayer(target, originalSize + 2);
        state.advanceTurn();
        state.advanceTurn();
    }

    @Override
    public void applyEffect(BaseCard.Color color, GameState state) {
        // only set color — turn already advanced by EffectCard
        state.setActiveColor(color);
    }
}