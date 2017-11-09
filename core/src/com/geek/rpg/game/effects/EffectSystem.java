package com.geek.rpg.game.effects;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EffectSystem {
   private  List<Effect>effectsList;

    public EffectSystem() {
      effectsList =  new ArrayList<Effect>();    // класс система эффектов создан для вынесения логики управления эффектами
                                                 //

    }
    public void addEffect(Effect effect) {
               effectsList.add(effect);
        }
        public  void getTurn() {
            for (int i = 0; i < effectsList.size(); i++) {
                effectsList.get(i).tick();
                if (effectsList.get(i).isEnded()) {
                    effectsList.get(i).end();
                     effectsList.remove(i);

                }
            }
        }
    }








