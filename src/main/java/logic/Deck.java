package logic;

import logic.Card.*;
import logic.Card.Base.BaseCard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private ArrayList<BaseCard> drawPile    = new ArrayList<>();
    private ArrayList<BaseCard> centerPile  = new ArrayList<>();

    public Deck() { buildDeck(); shuffle(); }

    private void buildDeck() {
        BaseCard.Color[] colors = {
                BaseCard.Color.RED, BaseCard.Color.GREEN,
                BaseCard.Color.BLUE, BaseCard.Color.YELLOW
        };

        for (BaseCard.Color c : colors) {
            drawPile.add(new NumberCard(c, 0));
            drawPile.add(new NumberCard(c, 0));
            for (int n = 1; n <= 9; n++) {
                drawPile.add(new NumberCard(c, n));
                drawPile.add(new NumberCard(c, n));
            }
            for (int i = 0; i < 2; i++) {
                drawPile.add(new SkipCard(c));
                drawPile.add(new ReverseCard(c));
                drawPile.add(new DrawTwo(c));
            }
        }

        for (int i = 0; i < 4; i++) {
            drawPile.add(new WildNormal());
            drawPile.add(new WildFour());
            drawPile.add(new KSKTrade());
        }

        drawPile.add(new KSKStrike());
        drawPile.add(new KSKStrike());
        drawPile.add(new KSKUltimate());
        drawPile.add(new TimeBomb());
    }

    public void shuffle() { Collections.shuffle(drawPile); }

    public BaseCard draw() {
        if (drawPile.isEmpty()) recycleCenterPile();
        return drawPile.isEmpty() ? null : drawPile.remove(0);
    }

    public void placeToCenterPile(BaseCard card) {
        centerPile.add(0, card);
    }

    public BaseCard peekCenterPile() {
        return centerPile.isEmpty() ? null : centerPile.get(0);
    }

    public void recycleCards(List<BaseCard> cards) {
        drawPile.addAll(cards);
        shuffle();
    }

    private void recycleCenterPile() {
        if (centerPile.size() <= 1) return;
        BaseCard top = centerPile.remove(0);
        drawPile.addAll(centerPile);
        centerPile.clear();
        centerPile.add(0, top);
        shuffle();
    }

    public BaseCard drawStartingCard() {
        for (int i = 0; i < drawPile.size(); i++) {
            if (drawPile.get(i) instanceof NumberCard) {
                return drawPile.remove(i);
            }
        }
        return draw();
    }

    public int drawPileSize()   { return drawPile.size(); }
    public int centerPileSize() { return centerPile.size(); }
}