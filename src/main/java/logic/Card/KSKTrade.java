package logic.Card;

import logic.Card.Base.BaseCard;
import logic.Card.Base.KSKCard;
import logic.GameState;
import logic.Player;
import java.util.ArrayList;
import java.util.List;

public class KSKTrade extends KSKCard {

    public KSKTrade() {
        super();
    }

    @Override
    public void applyEffect(GameState state) {
        // force swap hands with next player
        Player actor  = state.getCurrentPlayer();
        Player target = state.getNextPlayer();
        List<BaseCard> aHand = new ArrayList<>(actor.getHand());
        List<BaseCard> bHand = new ArrayList<>(target.getHand());
        actor.getHand().clear();  actor.getHand().addAll(bHand);
        target.getHand().clear(); target.getHand().addAll(aHand);
        state.advanceTurn();
    }

    @Override
    public void applyEffect(BaseCard.Color color, GameState state) {
        // only set color — turn already advanced by EffectCard
        state.setActiveColor(color);
    }
}