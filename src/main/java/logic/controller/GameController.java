package logic.controller;

import logic.Card.*;
import logic.Card.Base.BaseCard;
import logic.GameState;
import logic.Player;
import logic.interfaces.ColorChange;

import java.util.ArrayList;

public class GameController {
    private GameState state;

    public GameController(ArrayList<String> playerNames) {
        this.state = new GameState(playerNames);
    }

    public GameState getState() {
        return state;
    }

    public void drawCard() {//ไม่มีการ์ดเล่นบังคับจั่ว 1

        if (state.getPhase() != GameState.Phase.PLAYING) return;
        Player p = state.getCurrentPlayer();
        BaseCard drawn = state.getDeck().draw();
        if (drawn != null) p.addCard(drawn);
        state.advanceTurn();
    }

    public boolean playCard(int index) {
        GameState.Phase phase = state.getPhase();
        Player p = state.getCurrentPlayer();

        // Validate index
        if (index < 0 || index >= p.handSize()) return false;

        BaseCard card = p.getHand().get(index);

        // bomb
        if (phase == GameState.Phase.BOMB_COUNTDOWN) {
            if (!state.canPlay(card)) return false;
            state.setPhase(GameState.Phase.PLAYING);
        } else if (phase != GameState.Phase.PLAYING) {
            return false;
        }

        p.removeCard(index);
        state.getDeck().placeToCenterPile(card);//วางกลาง

        //
        if (!card.isWild()) state.setActiveColor(card.getColor());

        state.checkWinner();
        if (state.getPhase() == GameState.Phase.GAME_OVER) return true;

        if (card instanceof ColorChange) {
            state.setPhase(GameState.Phase.CHOOSE_COLOR);
            return true;
        }


        //skill
        if (card instanceof NumberCard) {
            state.advanceTurn();
        } else if (card instanceof SkipCard) {
            state.advanceTurn();//normal
            state.advanceTurn();
        }//skip
        else if (card instanceof ReverseCard) {
            state.reverse();
            if(state.getPlayers().size()==2) state.advanceTurn();//ถ้า1v1 เป็นskip
            state.advanceTurn();
        } else if (card instanceof DrawTwo){
            state.drawForPlayer(state.getNextPlayer(), 2);
            state.advanceTurn();
            state.advanceTurn();
        } else if (card instanceof KSKTrade){
            swapHands(state.getCurrentPlayer(), state.getNextPlayer());
            state.advanceTurn();
        }else if(card instanceof KSKStrike){
            Player target = state.getNextPlayer();
            int orginalSize = target.handSize();
            state.drawForPlayer(target,2);
            state.recyclePlayerHand(target);
            state.drawForPlayer(target,orginalSize +2);
            state.advanceTurn();
            state.advanceTurn();
        }else if(card instanceof KSKUltimate){
            Player target = state.getNextPlayer();
            int orginalSize = target.handSize();
            state.drawForPlayer(target,8);
            state.recyclePlayerHand(target);
            state.drawForPlayer(target,orginalSize +8);
            state.advanceTurn();
            state.advanceTurn();
        } else if (card instanceof WildFour) {
            state.drawForPlayer(state.getNextPlayer(), 4);
            state.advanceTurn();
            state.advanceTurn();
        }else if (card instanceof TimeBomb) {
            // start countdown for next player
            state.setPhase(GameState.Phase.BOMB_COUNTDOWN);
            state.advanceTurn();
        }
        return true;
    }

    public void chooseColor(BaseCard.Color color){
        if(state.getPhase() != GameState.Phase.CHOOSE_COLOR) return;
        ColorChange card = (ColorChange) state.getDeck().peekCenterPile();
        card.chooseColor(color, state);

        state.checkWinner();
    }

    public void confirmReady() {
        if (state.getPhase() != GameState.Phase.WAITING) return;
        state.confirmReady();
    }

    public void timeBombExploded() {
        // victim draws 3 and loses their turn
        Player victim = state.getCurrentPlayer();
        state.drawForPlayer(victim, 3);
        state.advanceTurn();
    }


    private void swapHands(Player a, Player b) {
        ArrayList<BaseCard> aHand = new ArrayList<>(a.getHand());
        ArrayList<BaseCard> bHand = new ArrayList<>(b.getHand());
        a.getHand().clear(); a.getHand().addAll(bHand);
        b.getHand().clear(); b.getHand().addAll(aHand);
    }
}
