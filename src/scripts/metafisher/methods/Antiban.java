package scripts.metafisher.methods;

import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;

import static org.tribot.api.General.println;
import static org.tribot.api.General.random;
import static org.tribot.api.General.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 23.6.2013
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class Antiban {
    public Antiban() {
        int r = random(0,4);

        switch (r) {
            case 0:
                RSNPC n[] = NPCs.getAll();

                if (n.length > 0 && n != null) {
                    for (int i = 0; i < n.length; i++) {
                        if (n[i].isValid() && n[i].isOnScreen()) {
                           Camera.turnToTile(n[i].getPosition());
                            break;
                        }
                    }
                }

                break;
            case 1:
                Mouse.move(random(0,500), random(0,500));
                break;
            case 2:
                RSNPC n2[] = NPCs.getAll();

                if (n2.length > 0 && n2 != null) {
                    for (int i = 0; i < n2.length; i++) {
                        if (n2[i].isValid() && n2[i].isOnScreen()) {
                            n2[i].hover();
                            sleep(800,1700);
                            Mouse.move(random(0,500), random(0,500));
                            break;
                        }
                    }
                }

                break;
            case 3:
                Camera.setCameraRotation(random(0,300));
                break;
            case 4:
                Camera.setCameraAngle(random(0,600));
                break;
            case 5:
                break;


        }

    }
}
