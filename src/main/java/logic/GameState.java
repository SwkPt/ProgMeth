package logic;

import logic.Card.Base.BaseCard;
import logic.Card.NumberCard;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameState {
    public enum Phase{
        WAITING, PLAYING, CHOOSE_COLOR, BOMB_COUNTDOWN, GAME_OVER }

    private final ArrayList<Player> players = new ArrayList<>();
    private final Deck deck = new Deck();

    private int currentPlayerIndex =0;
    private int direction = 1; // 1 = normal reversed = -1
    private Phase phase = Phase.WAITING;
    private BaseCard.Color activeColor;
    private Player winner;
    private String lastActionMessage = "";
    public GameState(ArrayList<String> playerNames){
        for (String name : playerNames) players.add(new Player(name));
        for (int i = 0; i < 7; i++){
            for (Player p : players) p.addCard(deck.draw());
        }
        BaseCard start = deck.drawStartingCard();
        deck.placeToCenterPile(start);
        activeColor = start.getColor();
    }

    //getter
    public ArrayList<Player> getPlayers()  { return players; }
    public Player getCurrentPlayer()       { return players.get(currentPlayerIndex); }
    public int getCurrentPlayerIndex()     { return currentPlayerIndex; }
    public Deck getDeck()                  { return deck; }
    public Phase getPhase()                { return phase; }
    public BaseCard.Color getActiveColor() { return activeColor; }
    public Player getWinner()              { return winner; }
    public String getLastActionMessage()   { return lastActionMessage; }
    public int getDirection()              { return direction; }

    //setter
    public void setPhase(Phase phase)                { this.phase = phase; }
    public void setActiveColor(BaseCard.Color color) { this.activeColor = color; }
    public void setLastActionMessage(String msg)     { this.lastActionMessage = msg; }// log

    public void advanceTurn() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
        phase = Phase.WAITING;
    }
    public Player getNextPlayer() {
        int next = (currentPlayerIndex + direction + players.size()) % players.size();
        return players.get(next);
    }

    public void reverse() { direction *= -1; }

    public void confirmReady() { phase = Phase.PLAYING; }

    public boolean canPlay(BaseCard card) {
        if (card.isWild()) return true;
        BaseCard top = deck.peekCenterPile();
        if (card.getColor() == activeColor) return true;
        if (top != null && card.getClass() == top.getClass()) return true;
        if (top instanceof NumberCard && card instanceof NumberCard) {
            if (((NumberCard) card).getNumber() == ((NumberCard) top).getNumber()) return true;
        }
        return false;
    }

    public void drawForPlayer(Player p, int count) {//โดนบวก
        for (int i = 0; i < count; i++) {
            BaseCard c = deck.draw();
            if (c != null) p.addCard(c);
        }
    }

    public void recyclePlayerHand(Player p) {//เอามือเข้ากอง for strike + ultimate
        ArrayList<BaseCard> hand = new ArrayList<>(p.getHand());
        p.getHand().clear();
        deck.recycleCards(hand);
    }

    public void checkWinner() {
        for (Player p : players) {
            if (p.hasWon()) {
                winner = p;
                phase = Phase.GAME_OVER;
                return;
            }
        }
    }
}


