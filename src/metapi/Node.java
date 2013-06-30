package metapi;

import org.tribot.api2007.types.RSTile;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 29.6.2013
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
public class Node {

    public int x, y;
    public Node prev;
    public double g, f;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        g = f = 0;
    }

    @Override
    public int hashCode() {
        return (x << 4) | y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node n = (Node) o;
            return x == n.x && y == n.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public RSTile toRSTile(int baseX, int baseY) {
        return new RSTile(x + baseX, y + baseY);
    }

}
