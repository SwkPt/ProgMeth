package logic.Card;

import logic.Card.Base.KSKCard;
import logic.interfaces.Targetable;

public class TimeBomb extends KSKCard implements Targetable {

    public static final int FUSE_SECONDS = 7;

    public TimeBomb() {
        super();
    }

    @Override
    public boolean isTimeBomb() { return true; }
}