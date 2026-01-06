package SystemDesign.LLD.Problems.Connect4;

public class Player {
    private Disc disc;  

    public Player(Disc disc) {
        this.disc = disc;
    }

    public String getColor() {
        return this.disc.getColor();
    }
}
