package g1008.Model;

public class PointCounter {
    int points;
    int level;
    int clearedLines;
    boolean inCombo;

    public PointCounter(){
        level = 1;
        points = 0;
        clearedLines = 0;
        inCombo = false;
    }

    public int getPoints() { return points; }

    public int getLevel() {return level;}

    public void setPoints(int points) { this.points = points; }

    public void processLines(int n){
        if(n == 0){
            inCombo = false;
            return;
        }
        float multiplier = level;
        if(inCombo)
            multiplier *= 1.5;
        else
            inCombo = true;
        switch (n){
            case 1-> points += 40 * multiplier;
            case 2-> points += 100 * multiplier;
            case 3-> points += 300 * multiplier;
            case 4-> points += 1200 * multiplier;
        }
        clearedLines += n;
    }

    public void processLevel(){
        if(clearedLines <= 50)
            level = clearedLines / 10 + 1;
        else if (clearedLines <= 70)
            level = 7;
        else if (clearedLines <= 100)
            level = 8;
        else if (clearedLines <= 140)
            level = 9;
        else if (clearedLines <= 190)
            level = 10;
        else
            level = 11 + (clearedLines-190)/50;
    }

    public long getLevelTimer(){
        return (1600/level);
    }
}