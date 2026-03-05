package logic.controller;

import logic.Card.*;
import logic.Card.Base.BaseCard;
import logic.GameState;
import logic.Player;
import logic.interfaces.ColorChange;
import logic.interfaces.EffectCard;

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

        if (!state.canPlay(card)) return false;

        p.removeCard(index);
        state.getDeck().placeToCenterPile(card);//วางกลาง

        //
        if (!card.isWild()) state.setActiveColor(card.getColor());

        state.checkWinner();
        if (state.getPhase() == GameState.Phase.GAME_OVER) return true;


        if (card instanceof EffectCard) {
            ((EffectCard) card).applyEffect(state);
        } else {
            // NumberCard case
            state.advanceTurn();
        }
        if (card instanceof ColorChange) {
            state.setPhase(GameState.Phase.CHOOSE_COLOR);
            return true;
        }

        return true;

    }
    public void colorChange(BaseCard.Color color) {
        if (state.getPhase() != GameState.Phase.CHOOSE_COLOR) return;
        // polymorphism — actual card type determines which applyEffect() runs
        ColorChange card = (ColorChange) state.getDeck().peekCenterPile();
        card.applyEffect(color, state);
        state.checkWinner();
    }

    // เรียกตอนกดปุ่ม ready (gui)-
    public void confirmReady() {
        if (state.getPhase() != GameState.Phase.WAITING) return;
        state.confirmReady();
    }

    // ให้ gui time bomb เรียก
    public void timeBombExploded() {
        // victim draws 3 and loses their turn
        Player victim = state.getCurrentPlayer();
        state.drawForPlayer(victim, 3);
        state.advanceTurn();
    }

}
