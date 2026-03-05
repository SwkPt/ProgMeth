package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.GameState;
import logic.Player;

public class KSKUltimate extends KSKCard {

    public KSKUltimate() {
        super();
    }

    @Override
    public void applyEffect(GameState state) {
        // next player draws 8, hand recycled, redraws original + 8
        Player target       = state.getNextPlayer();
        int    originalSize = target.handSize();
        state.drawForPlayer(target, 8);
        state.recyclePlayerHand(target);
        state.drawForPlayer(target, originalSize + 8);
        state.advanceTurn();
        state.advanceTurn();
    }

    @Override
    public void applyEffect(BaseCard.Color color, GameState state) {
        // only set color — turn already advanced by EffectCard
        state.setActiveColor(color);
    }
}