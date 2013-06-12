package scripts.aiofisher;


import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.script.EnumScript;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.aiofisher.enums.Banks;
import scripts.aiofisher.enums.FishPools;
import scripts.aiofisher.enums.FishTools;
import scripts.aiofisher.enums.States;
import scripts.aiofisher.methods.Bank;
import scripts.aiofisher.methods.Drop;
import scripts.aiofisher.methods.Fish;
import scripts.aiofisher.methods.Walk;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 14.3.2013
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */

@ScriptManifest(authors = {"Merphz"}, category = "Fishing", name = "AIOFisher", version = 1.0)
public class AIOFisher extends EnumScript<States> implements Painting {

    private GraphicalInterface GUI;
    private States scriptState = States.GUI;

    private Bank bank;
    private Walk walk;
    private Fish fish;
    private Drop drop;

    private Banks bankEnum;
    private FishPools poolEnum;
    private FishTools toolEnum;
    private int[] fishIDs = {331, 335, 363, 341, 353};

    private HashMap dropMap;

    private long startTime;
    private int startXP;

    private int oldCount;
    private int newCount;

    private int FISHES_CAUGHT = 0;


    public static boolean guiDone = false;

    @Override
    public States handleState(States states) {


        scriptState = states;


        switch (scriptState) {
            case BANK:
                bank.deposit();
                break;
            case WALK_TO_BANK:
                walk.toggleRun(true);
                walk.walkToBank();
                break;
            case FISH:
                fish.startFishing();
                break;
            case WALK_TO_FISH:

                walk.toggleRun(true);
                walk.walkToFish();
                break;
            case DROP:
                drop.dropAll();
                break;
            case INV_CHANGE:
                FISHES_CAUGHT += (oldCount - newCount);
                newCount = Inventory.getCount(fishIDs);
                break;
            case WITHDRAW_TOOLS:
                bank.withdrawTool();
                break;
            case GUI:
                break;
        }
        sleep(30, 50);

        return getState();
    }

    public States getState() {

        //println("uptext = "+Game.getUptext());

        /*if (Game.getUptext().contains("Use")) {
            println("failsafe");
            Mouse.click(1);
            sleep(300,600);
        }                */

        oldCount = Inventory.getCount(fishIDs);


        if (!guiDone) return States.GUI;
        if (Inventory.isFull()) {
            if (!dropMap.isEmpty() || GUI.powerfish) {
                if (drop.invContainsDropItem()) {
                    return States.DROP;
                }
            }
            if (walk.bankIsNear()) return States.BANK;
            else return States.WALK_TO_BANK;
        } else {

            if (Inventory.getCount(toolEnum.getID()) == 0 || toolEnum.getIngredientID() != -1 && Inventory.getCount(toolEnum.getIngredientID()) == 0) {
                if (!walk.bankIsNear()) return States.WALK_TO_BANK;
                else return States.WITHDRAW_TOOLS;


            }

            if (oldCount > newCount) return States.INV_CHANGE;
            newCount = Inventory.getCount(fishIDs);

            if ((walk.fishIsNear()) && Player.getAnimation() == -1) return States.FISH;
            else if (!walk.fishIsNear() && Player.getAnimation() == -1) return States.WALK_TO_FISH;
        }


        return States.IDLE;
    }


    @Override
    public States getInitialState() {


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI = new GraphicalInterface();


                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                GUI.setLocation((screenSize.width / 2) - (GUI.getSize().width / 2), screenSize.height / 2 - (GUI.getSize().height / 2));


                GUI.setVisible(true);
            }
        });


        startTime = System.currentTimeMillis();

        while (true) {

            if (guiDone) {
                break;
            }
            sleep(40, 80);
        }


        startXP = Skills.getXP("FISHING");
        bankEnum = GUI.getBank();
        poolEnum = GUI.getPool();
        toolEnum = GUI.getTool();
        dropMap = GUI.getDropList();


        bank = new Bank(bankEnum, toolEnum);
        walk = new Walk(bankEnum, poolEnum);

        newCount = Inventory.getCount(fishIDs);

        fish = new Fish(poolEnum, toolEnum);

        if (!dropMap.isEmpty() || GUI.powerfish) {
            drop = new Drop(dropMap, GUI.powerfish, toolEnum);
        }


        return getState();
    }

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);

    private final Font font1 = new Font("Arial", 0, 9);


    private final Image img1 = getImage("http://i.imgur.com/Exwhyeo.png");


    String formatMillis(long millis) {
        return String.format("%d h, %d min, %d sec", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    @Override
    public void onPaint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.drawImage(img1, 331, 206, null);

        int gainedXP = Skills.getXP("FISHING") - startXP;

        long time = System.currentTimeMillis() - startTime;


        g.setFont(font1);
        g.setColor(color1);
        g.drawString("Time Running: " + formatMillis(time), 333, 224);
        g.drawString("XP Gained: " + gainedXP, 334, 247);
        g.drawString("XP Till Level: " + Skills.getXPToLevel("FISHING", (Skills.getCurrentLevel("FISHING") + 1)), 332, 271);
        g.drawString("Current Level: " + Skills.getCurrentLevel("FISHING"), 332, 296);
        g.drawString("Fishes caught: " + FISHES_CAUGHT, 332, 321);
        g.drawString("XP/H: " + (long) (gainedXP / (time / 3600000D)), 420, 321);

    }
}