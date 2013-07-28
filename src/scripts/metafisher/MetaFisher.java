package scripts.metafisher;

import metapi.MWalking;
import metapi.chatbot.ChatterBot;
import metapi.chatbot.ChatterBotFactory;
import metapi.chatbot.ChatterBotSession;
import metapi.chatbot.ChatterBotType;
import metapi.enums.Banks;
import metapi.util.*;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.EnumScript;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.RandomEvents;
import scripts.metafisher.enums.FishPools;
import scripts.metafisher.enums.FishTools;
import scripts.metafisher.enums.States;
import scripts.metafisher.methods.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


import static metapi.util.Timing.CSleep;
import static org.tribot.api.General.random;
import static org.tribot.api2007.Login.logout;


/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 14.3.2013
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */

@ScriptManifest(authors = {"Merphz"}, category = "Fishing", name = "MetaFisher", version = 1.27)
public class MetaFisher extends EnumScript<States> implements Painting, RandomEvents, Ending, MessageListening07 {

    private GraphicalInterface GUI;

    private Bank bank;
    private Walk walk;
    private Fish fish;
    private Drop drop;

    private FishTools toolEnum;
    private int[] fishIDs = {331, 335, 363, 341, 353, 359, 371, 377, 317, 321, 345, 327, 349, 383};

    private HashMap dropMap;

    private long startTime;
    private int startXP;

    private int oldCount;
    private int newCount;

    public long time;
    public int gainedXP;

    public static int FISHES_CAUGHT = 0;

    private Timer logoutTimer = null;
    private Timer antiBanTimer = null;
    private Timer runTimer = null;

    private NotifyRunnable notifyRunnable;
    Thread notifyThread = null;

    ChatterBotSession bot1session;


    public static boolean guiDone = false;


    private Networking networking;




    public boolean pickupTool() {
        RSGroundItem[] tool = GroundItems.findNearest(toolEnum.getID());

        if (tool[0].isOnScreen()) {
            if (Walking.walkTo(tool[0].getPosition()) && tool[0].click("Take")) {
                CSleep(new Timing.Condition() {
                    @Override
                    public boolean validate() {
                        return Inventory.getCount(toolEnum.getID()) > 0;
                    }
                }, random(2000, 4000));
            }
        } else {
            Walking.walkTo(tool[0].getPosition());
            sleep(1000, 2000);
        }
        return false;
    }

    @Override
    public States handleState(States states) {


        States scriptState = states;


        switch (scriptState) {
            case BANK:
                println("DEPOSIT");
                bank.deposit(true);
                break;
            case WALK_TO_BANK:
                println("WALK_TO_BANK");
                MWalking.toggleRun(true, runTimer);
                walk.walkToBank();
                break;
            case FISH:
                fish.startFishing();
                break;
            case WALK_TO_FISH:
                println("WALK_TO_FISH");
                MWalking.toggleRun(true, runTimer);
                walk.walkToFish();
                break;
            case DROP:

                drop.dropAll();
                break;
            case INV_CHANGE:
                FISHES_CAUGHT += (oldCount - newCount);
                newCount = Inventory.getCount(fishIDs);
                break;
            case WITHDRAW_TOOL:
                println("WITHDRAW_TOOL");
                bank.withdrawTool();
                break;
            case PICKUP_TOOL:
                println("PICKUP_TOOL");
                pickupTool();
                break;
            case LOGOUT:
                if (logout()) {
                    super.setLoginBotState(false);
                }
                break;
            case INCOMBAT:

                RSNPC n[] = NPCs.find(96);

                boolean safe = false;

                if (n != null && n.length > 0) {
                    for (int i = 0 ; i < n.length; i++) {
                        if (n[i].isInteractingWithMe()) {
                            safe = true;
                            break;
                        }
                    }
                }
                if (!safe) {
                    MWalking.toggleRun(true, runTimer);
                    walk.walkToBank();
                }

                break;
            case CLOSE_BANK:
                Banking.close();
                println("CLOSE_BANK");
                break;
            case GUI:
                break;
        }
        sleep(30, 50);

        return getState();
    }


    public States getState() {




        oldCount = Inventory.getCount(fishIDs);


        if (!guiDone) return States.GUI;




        if (Player.getRSPlayer().isInCombat()) return States.INCOMBAT;

        if (logoutTimer != null && !logoutTimer.isRunning() && !Banking.isBankScreenOpen()) return States.LOGOUT;

        if (antiBanTimer == null && GUI.getAntiban() != -1) {
            antiBanTimer = new Timer(GUI.getAntiban());
        }


        if (antiBanTimer != null && !antiBanTimer.isRunning()) {
            Antiban antiban = new Antiban();
            antiban = null;
            antiBanTimer = null;
        }


        if (Inventory.isFull()) {
            if (!dropMap.isEmpty() || GUI.powerfish) {
                if (drop.invContainsDropItem()) {
                    return States.DROP;
                }
            }
            if (walk.boothIsNear()) return States.BANK;
            else return States.WALK_TO_BANK;
        } else {
            if (Inventory.getCount(toolEnum.getID()) == 0 || (toolEnum.getIngredientID() != -1 && Inventory.getCount(toolEnum.getIngredientID()) == 0)) {
                RSGroundItem[] tool = GroundItems.findNearest(toolEnum.getID());
                if (tool.length > 0 && tool != null && PathFinding.canReach(tool[0].getPosition(),false)) {
                    return States.PICKUP_TOOL;
                } else {
                    if (!walk.boothIsNear()) return States.WALK_TO_BANK;
                    else return States.WITHDRAW_TOOL;
                }



            }

            if (Banking.isBankScreenOpen()) {
                return States.CLOSE_BANK;
            }


            if (oldCount > newCount) return States.INV_CHANGE;
            newCount = Inventory.getCount(fishIDs);


            RSNPC swirlpool[] = NPCs.findNearest(406);

            if (swirlpool.length > 0 && swirlpool != null && swirlpool[0].isInteractingWithMe()) return States.FISH;

            if (walk.fishIsNear() && Player.getAnimation() == -1 && !Banking.isBankScreenOpen()) return States.FISH;
            if (!walk.fishIsNear() && Player.getAnimation() == -1 && !Player.getRSPlayer().isInCombat())
                return States.WALK_TO_FISH;
        }



        return States.IDLE;
    }


    @Override
    public States getInitialState() {

        String settings = null;

        networking = new Networking(script_name);

        try {
            settings = networking.fetchSettings();
        } catch (Exception e) {
            println("Networking error: " + e.toString());
            e.printStackTrace();
        }


        final String finalSettings = settings;
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI = new GraphicalInterface(finalSettings);
            }
        });


        startTime = System.currentTimeMillis();



        while (true) {

            if (guiDone) {
                break;
            }
            sleep(40, 80);
        }

        runTimer = new Timer(random(180000, 300000));

        if (GUI.getLogout()) {
            logoutTimer = new Timer(GUI.getLogoutMS());
        }

        startXP = Skills.getXP("FISHING");
        Banks bankEnum = GUI.getBank();
        FishPools poolEnum = GUI.getPool();
        toolEnum = GUI.getTool();
        dropMap = GUI.getDropList();


        bank = new Bank(bankEnum, toolEnum);
        walk = new Walk(bankEnum, poolEnum);
        fish = new Fish(poolEnum, toolEnum);


        while (Login.getLoginState() != Login.STATE.INGAME) {
            sleep(50);
        }

        newCount = Inventory.getCount(fishIDs);


        if (!dropMap.isEmpty() || GUI.powerfish) {
            drop = new Drop(dropMap, GUI.powerfish, toolEnum);
        }



        if (GUI.getNMAToken() != null) {
            notifyRunnable = new NotifyRunnable(networking, this);
            notifyThread = new Thread(notifyRunnable);

            notifyThread.setDaemon(true);
            notifyThread.start();
        }



        if (GUI.getCleverbot()) {
            ChatterBotFactory factory = new ChatterBotFactory();

            ChatterBot bot1 = null;
            try {
                bot1 = factory.create(ChatterBotType.CLEVERBOT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bot1session = bot1.createSession();
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




    @Override
    public void onPaint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.drawImage(img1, 331, 206, null);

        gainedXP = Skills.getXP("FISHING") - startXP;

        time = System.currentTimeMillis() - startTime;




        g.setFont(font1);
        g.setColor(color1);
        g.drawString("Time Running: " + Utils.format(time), 333, 224);
        g.drawString("XP Gained: " + gainedXP, 334, 247);
        g.drawString("XP Till Level: " + Skills.getXPToLevel("FISHING", (Skills.getCurrentLevel("FISHING") + 1)), 332, 271);
        g.drawString("Current Level: " + Skills.getCurrentLevel("FISHING"), 332, 296);
        g.drawString("Fishes caught: " + FISHES_CAUGHT, 332, 321);
        g.drawString("XP/H: " + (long) (gainedXP / (time / 3600000D)), 425, 321);
        if (antiBanTimer != null) {
            g.drawString("antiban in: " + antiBanTimer.toRemainingString(), 5, 321);
        }


    }

    @Override
    public void onRandom(RANDOM_SOLVERS random_solvers) {

        networking.sendNotification("In_random", "In_random");
    }

    @Override
    public boolean randomFailed(RANDOM_SOLVERS random_solvers) {

       networking.sendNotification("Random_event_failed", "Random_event_failed");

        return false;
    }

    @Override
    public void randomSolved(RANDOM_SOLVERS random_solvers) {

    }

    @Override
    public void onEnd() {
        notifyThread.interrupt();
        println("Shutdown");
    }

    @Override
    public void clanMessageRecieved(String s, String s2) {

    }

    @Override
    public void playerMessageRecieved(String s, String s2) {

        if (GUI.getCleverbot()) {
            if (!s.equals(Player.getRSPlayer().getName())) {
                String string = s2;

                println("Player: "+s);
                try {
                    string = bot1session.think(string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    Keyboard.typeSend(string);
                    println("Cleverbot: "+string);

            }
        }








    }

    @Override
    public void serverMessageRecieved(String s) {

    }
}
