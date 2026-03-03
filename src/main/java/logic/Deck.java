package logic;

import logic.Card.Base.BaseCard;
import logic.Card.NumberCard;

import java.util.ArrayList;

public class Deck {
    private ArrayList<BaseCard>  drawPile = new ArrayList<>(); //กองคว่ำ
    private  ArrayList<BaseCard> centerPile = new ArrayList<>(); //ตรงกลาง

    public void buildDeck(){
        BaseCard.Color[] colors = {
                BaseCard.Color.RED, BaseCard.Color.BLUE,BaseCard.Color.GREEN,BaseCard.Color.YELLOW
        };

        for (BaseCard.Color c : colors){
            drawPile.add(new NumberCard())
        }
    }
}
