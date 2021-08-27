public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;
    protected boolean insideInterval;

    public Frog() {
        position = 5;
        insideInterval = true;
    }

    public int getPosition() {
        return position;
    }

    public boolean jump(int steps) {
        int positionProbable = position + steps;

        if (positionProbable >= MIN_POSITION && positionProbable <= MAX_POSITION) {
            position = positionProbable;
            insideInterval = true;
            return true;
        } else {
            insideInterval = false;
            System.out.println("Вышли за границу!");
            return false;
        }
    }
}
