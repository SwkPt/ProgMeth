package logic.Card.Base;

public abstract class BaseCard {

    public enum Color { RED, GREEN, BLUE, YELLOW, WILD, KSK }

    protected final Color color;

    public BaseCard(Color color) { this.color = color; }

    public Color getColor()     { return color; }
    public boolean isWild()     { return color == Color.WILD || color == Color.KSK; }
    public boolean isKSK()      { return color == Color.KSK; }
    public boolean isTimeBomb() { return false; }
}