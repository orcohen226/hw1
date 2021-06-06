import java.io.FileNotFoundException;

public class Card {
    final int num;
    final Shape shapeNumber;

    public Card (int number, Shape shape) {
        this.num = number;
        this.shapeNumber = shape;
    }

    public int getNumber() {
        return this.num;
    }
    public Shape getShapeNumber() {
        return this.shapeNumber;
    }

    public int compare(Card other){
        if (this.num < other.num){
            return -1;
        }else if (this.num > other.num){
            return 1;
        }
        return 0;
    }

    public String toString(){
        if (num == 13) {return "King of " + shapeNumber;}
        else if (num == 12) {return "Queen of " + shapeNumber;}
        else if (num == 11) {return "Prince of " + shapeNumber;}
        else if (num == 1) {return "Ace of " + shapeNumber;}
        return num + " of " + shapeNumber ;

    }
}