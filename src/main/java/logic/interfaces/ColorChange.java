package logic.interfaces;

import logic.Card.Base.BaseCard;
import logic.GameState;

public interface ColorChange {
    void chooseColor(BaseCard.Color color, GameState state);
}