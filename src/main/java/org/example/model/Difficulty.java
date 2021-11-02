package org.example.model;

public class Difficulty {
    private Level difficultyLevel;
    public Difficulty(Level difficultyLevel){
        this.difficultyLevel = difficultyLevel;
    }

    public Level getDifficultyLevel() {
        return difficultyLevel;
    }

    public enum Level{
        Easy(1),
        Medium(2),
        Hard(3);

        private int multiplier;

        Level(int multiplier){
            this.multiplier = multiplier;
        }

        public int value(){
            return this.multiplier;
        }
    }
}

