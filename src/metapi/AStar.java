package metapi;

import org.tribot.api2007.Game;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.tribot.api.General.println;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 28.6.2013
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class AStar {

    public static final int WALL_NORTH_WEST = 0x1;
    public static final int WALL_NORTH = 0x2;
    public static final int WALL_NORTH_EAST = 0x4;
    public static final int WALL_EAST = 0x8;
    public static final int WALL_SOUTH_EAST = 0x10;
    public static final int WALL_SOUTH = 0x20;
    public static final int WALL_SOUTH_WEST = 0x40;
    public static final int WALL_WEST = 0x80;
    public static final int BLOCKED = 0x100;

//	public static final int BLOCK_1 = 128;
//	public static final int BLOCK_2 = 8;
//	public static final int BLOCK_3 = 2;
//	public static final int BLOCK_4 = 32;
//	public static final int BLOCK_5 = 1;
//	public static final int BLOCK_6 = 16;
//	public static final int BLOCK_7 = 64;
//	public static final int BLOCK_8 = 4;
//	public static final int BLOCK_9 = 130;
//
//	public static final int BLOCK_10 = 0x10000;
//	public static final int BLOCK_11 = 4096;
//	public static final int BLOCK_12 = 1024;
//	public static final int BLOCK_13 = 16384;
//	public static final int BLOCK_14 = 512;
//	public static final int BLOCK_15 = 2048;
//	public static final int BLOCK_16 = 8192;
//	public static final int BLOCK_17 = 32768;
//	public static final int BLOCK_18 = 0x10400;
//	public static final int BLOCK_19 = 5120;
//	public static final int BLOCK_20 = 20480;
//	public static final int BLOCK_21 = 0x14000;
//
//	public static final int BLOCK_22 = 0x400000;
//	public static final int BLOCK_23 = 0x4000000;
//	public static final int BLOCK_24 = 0x1000000;
//	public static final int BLOCK_25 = 0x10000000;
//	public static final int BLOCK_26 = 0x20800000;
//	public static final int BLOCK_27 = 0x2000000;
//	public static final int BLOCK_28 = 0x8000000;
//	public static final int BLOCK_29 = 0x2800000;
//	public static final int BLOCK_30 = 0x20000000;
//	public static final int BLOCK_31 = 0xA000000;
//	public static final int BLOCK_32 = 0x800000;
//	public static final int BLOCK_33 = 0x28000000;

    public static final RSTile[] EMPTY_PATH = new RSTile[0];

    //private final AStar pf = new AStar();

    private volatile RSTile current = new RSTile(0, 0);
    private volatile RSTile[] path = EMPTY_PATH;

    RSTile[] walk_path;

    private Game game;
    private Walking walking;


    private byte[][][] flags;
    private int off_x, off_y;


    public AStar() {
        println("start");


    }

    public RSTile[] findPath(RSTile start, RSTile end) {

        int base_x = Game.getBaseX(), base_y = Game.getBaseY();
        int curr_x = start.getX() - base_x, curr_y = start.getY() - base_y;
        int dest_x = end.getX() - base_x, dest_y = end.getY() - base_y;

        // load client data

        //flags = Game.getSceneFlags();
        int[][][] offset = Game.getTileOffsets();
        /*off_x = offset[0][0][Game.getPlane()];
        off_y = offset[0][0][Game.getPlane()];*/

        // loaded region only
        /*if (flags == null || curr_x < 0 || curr_y < 0 ||
                curr_x >= flags.length || curr_y >= flags.length ||
                dest_x < 0 || dest_y < 0 ||
                dest_x >= flags.length || dest_y >= flags.length) {
            return null;
        }*/


        // structs
        HashSet<Node> open = new HashSet<Node>();
        HashSet<Node> closed = new HashSet<Node>();
        Node curr = new Node(curr_x, curr_y);
        Node dest = new Node(dest_x, dest_y);

        curr.f = heuristic(curr, dest);
        open.add(curr);

        // search
        while (!open.isEmpty()) {
            curr = lowest_f(open);
            if (curr.equals(dest)) {
                // reconstruct from pred tree
                return path(curr, base_x, base_y);
            }
            open.remove(curr);
            closed.add(curr);
            for (Node next : successors(curr)) {
                if (!closed.contains(next)) {
                    double t = curr.g + dist(curr, next);
                    boolean use_t = false;
                    if (!open.contains(next)) {
                        open.add(next);
                        println("next = " + next.toRSTile(base_x, base_y));
                        use_t = true;
                    } else if (t < next.g) {
                        use_t = true;
                    }
                    if (use_t) {
                        next.prev = curr;
                        next.g = t;
                        next.f = t + heuristic(next, dest);
                    }
                }
            }
        }

        // no path
        return null;
    }

    private double heuristic(Node start, Node end) {
        double dx = start.x - end.x;
        double dy = start.y - end.y;
        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;
        return dx < dy ? dy : dx;
        //double diagonal = dx > dy ? dy : dx;
        //double manhattan = dx + dy;
        //return 1.41421356 * diagonal + (manhattan - 2 * diagonal);
    }

    private double dist(Node start, Node end) {
        if (start.x != end.x && start.y != end.y) {
            return 1.41421356;
        } else {
            return 1.0;
        }
    }

    private Node lowest_f(Set<Node> open) {
        Node best = null;
        for (Node t : open) {
            if (best == null || t.f < best.f) {
                best = t;
            }
        }
        return best;
    }

    private RSTile[] path(Node end, int base_x, int base_y) {
        LinkedList<RSTile> path = new LinkedList<RSTile>();
        Node p = end;
        while (p != null) {
            path.addFirst(p.toRSTile(base_x, base_y));
            p = p.prev;
        }
        return path.toArray(new RSTile[path.size()]);
    }

    private java.util.List<Node> successors(Node t) {

        LinkedList<Node> tiles = new LinkedList<Node>();
        int x = t.x, y = t.y;
        //int f_x = x - off_x, f_y = y - off_y;
        //int here = flags[f_x][f_y][Game.getPlane()];

        //println("here = " + here);

        /*if (f_y > 0 && (here & WALL_SOUTH) == 0 && (flags[f_x][f_y - 1][Game.getPlane()] & BLOCKED) == 0) {
            tiles.add(new Node(x, y - 1));
        }
        if (f_x > 0 && (here & WALL_WEST) == 0 && (flags[f_x - 1][f_y][Game.getPlane()] & BLOCKED) == 0) {
            tiles.add(new Node(x - 1, y));
        }
        if (f_y < 103 && (here & WALL_NORTH) == 0 && (flags[f_x][f_y + 1][Game.getPlane()] & BLOCKED) == 0) {
            tiles.add(new Node(x, y + 1));
        }
        if (f_x < 103 && (here & WALL_EAST) == 0 && (flags[f_x + 1][f_y][Game.getPlane()] & BLOCKED) == 0) {
            tiles.add(new Node(x + 1, y));
        }
        if (f_x > 0 && f_y > 0 && (here & (WALL_SOUTH_WEST | WALL_SOUTH | WALL_WEST)) == 0
                && (flags[f_x - 1][f_y - 1][Game.getPlane()] & BLOCKED) == 0
                && (flags[f_x][f_y - 1][Game.getPlane()] & (BLOCKED | WALL_WEST)) == 0
                && (flags[f_x - 1][f_y][Game.getPlane()] & (BLOCKED | WALL_SOUTH)) == 0) {
            tiles.add(new Node(x - 1, y - 1));
        }
        if (f_x > 0 && f_y < 103 && (here & (WALL_NORTH_WEST | WALL_NORTH | WALL_WEST)) == 0
                && (flags[f_x - 1][f_y + 1][Game.getPlane()] & BLOCKED) == 0
                && (flags[f_x][f_y + 1][Game.getPlane()] & (BLOCKED | WALL_WEST)) == 0
                && (flags[f_x - 1][f_y][Game.getPlane()] & (BLOCKED | WALL_NORTH)) == 0) {
            tiles.add(new Node(x - 1, y + 1));
        }
        if (f_x < 103 && f_y > 0 && (here & (WALL_SOUTH_EAST | WALL_SOUTH | WALL_EAST)) == 0
                && (flags[f_x + 1][f_y - 1][Game.getPlane()] & BLOCKED) == 0
                && (flags[f_x][f_y - 1][Game.getPlane()] & (BLOCKED | WALL_EAST)) == 0
                && (flags[f_x + 1][f_y][Game.getPlane()] & (BLOCKED | WALL_SOUTH)) == 0) {
            tiles.add(new Node(x + 1, y - 1));
        }
        if (f_x > 0 && f_y < 103 && (here & (WALL_NORTH_EAST | WALL_NORTH | WALL_EAST)) == 0
                && (flags[f_x + 1][f_y + 1][Game.getPlane()] & BLOCKED) == 0
                && (flags[f_x][f_y + 1][Game.getPlane()] & (BLOCKED | WALL_EAST)) == 0
                && (flags[f_x + 1][f_y][Game.getPlane()] & (BLOCKED | WALL_NORTH)) == 0) {
            tiles.add(new Node(x + 1, y + 1));
        }*/
        tiles.add(new Node(x, y));
        return tiles;
    }


}
