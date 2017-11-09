package com.geek.rpg.game.effects;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EffectSystem {
   private  List<Effect>effectsList;
   public String name;
    public EffectSystem(String name) {
      effectsList =  new ArrayList<Effect>();
      this.name=name;
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








