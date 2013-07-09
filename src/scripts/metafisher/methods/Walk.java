package scripts.metafisher.methods;

import metapi.AStar;
import metapi.MWalking;
import metapi.enums.Banks;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.metafisher.enums.FishPools;

import static org.tribot.api.General.println;
import static org.tribot.api.General.random;


/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 7.6.2013
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class Walk {

    Banks bank;
    FishPools pool;


    RSTile[] Path;


    public Walk(Banks chosenBank, FishPools chosenPool) {
        this.bank = chosenBank;
        this.pool = chosenPool;

        switch (bank) {
           case CATHERBY:
                Path = new RSTile[]{new RSTile(2810, 3441, 0),new RSTile(2810, 3440, 0),new RSTile(2809, 3439, 0),new RSTile(2809, 3438, 0),new RSTile(2809, 3437, 0),new RSTile(2810, 3436, 0),
                        new RSTile(2811, 3435, 0),new RSTile(2812, 3435, 0),new RSTile(2812, 3434, 0),new RSTile(2813, 3434, 0),new RSTile(2814, 3435, 0),new RSTile(2815, 3435, 0),
                        new RSTile(2816, 3435, 0),new RSTile(2817, 3436, 0),new RSTile(2818, 3436, 0),new RSTile(2819, 3436, 0),new RSTile(2820, 3436, 0),new RSTile(2821, 3436, 0),
                        new RSTile(2822, 3436, 0),new RSTile(2823, 3436, 0),new RSTile(2824, 3436, 0),new RSTile(2825, 3436, 0),new RSTile(2826, 3436, 0),new RSTile(2827, 3436, 0),
                        new RSTile(2828, 3436, 0),new RSTile(2830, 3436, 0),new RSTile(2831, 3436, 0),new RSTile(2832, 3436, 0),new RSTile(2833, 3436, 0),new RSTile(2834, 3435, 0),
                        new RSTile(2834, 3434, 0),new RSTile(2835, 3433, 0),new RSTile(2836, 3433, 0),new RSTile(2836, 3432, 0),new RSTile(2837, 3432, 0),new RSTile(2838, 3432, 0),
                        new RSTile(2839, 3432, 0),new RSTile(2840, 3433, 0),new RSTile(2841, 3433, 0),new RSTile(2842, 3433, 0),new RSTile(2843, 3433, 0),new RSTile(2844, 3433, 0),
                        new RSTile(2845, 3433, 0),new RSTile(2846, 3433, 0),new RSTile(2847, 3433, 0),new RSTile(2848, 3433, 0),new RSTile(2849, 3433, 0),new RSTile(2850, 3432, 0),
                        new RSTile(2851, 3432, 0),new RSTile(2852, 3431, 0),new RSTile(2853, 3431, 0),new RSTile(2854, 3431, 0),new RSTile(2855, 3431, 0),new RSTile(2856, 3431, 0),
                        new RSTile(2857, 3430, 0),new RSTile(2858, 3429, 0),new RSTile(2859, 3428, 0),new RSTile(2860, 3427, 0)};
                break;
           case AL_KHARID:
                Path = new RSTile[]{new RSTile(3270, 3167, 0),new RSTile(3271, 3167, 0),new RSTile(3272, 3167, 0),new RSTile(3273, 3166, 0),new RSTile(3273, 3165, 0),new RSTile(3273, 3164, 0),
                        new RSTile(3273, 3163, 0),new RSTile(3273, 3162, 0),new RSTile(3274, 3161, 0),new RSTile(3273, 3160, 0),new RSTile(3272, 3159, 0),new RSTile(3271, 3158, 0),
                        new RSTile(3270, 3157, 0),new RSTile(3269, 3156, 0),new RSTile(3269, 3155, 0),new RSTile(3269, 3154, 0),new RSTile(3269, 3153, 0),new RSTile(3269, 3152, 0),
                        new RSTile(3269, 3151, 0),new RSTile(3269, 3150, 0),new RSTile(3269, 3149, 0),new RSTile(3269, 3148, 0),new RSTile(3270, 3147, 0),new RSTile(3270, 3146, 0),
                        new RSTile(3271, 3145, 0),new RSTile(3272, 3145, 0),new RSTile(3273, 3145, 0),new RSTile(3274, 3145, 0),new RSTile(3275, 3144, 0)};
                break;
            case DRAYNOR:
                Path = new RSTile[]{new RSTile(3093, 3243, 0),new RSTile(3093, 3244, 0),new RSTile(3093, 3245, 0),new RSTile(3093, 3246, 0),new RSTile(3092, 3247, 0),new RSTile(3091, 3247, 0),
                        new RSTile(3090, 3247, 0),new RSTile(3089, 3247, 0),new RSTile(3088, 3247, 0),new RSTile(3087, 3247, 0),new RSTile(3087, 3246, 0),new RSTile(3087, 3245, 0),
                        new RSTile(3087, 3244, 0),new RSTile(3087, 3243, 0),new RSTile(3087, 3242, 0),new RSTile(3087, 3241, 0),new RSTile(3087, 3240, 0),new RSTile(3087, 3239, 0),
                        new RSTile(3088, 3238, 0),new RSTile(3089, 3237, 0),new RSTile(3090, 3236, 0),new RSTile(3090, 3235, 0),new RSTile(3090, 3234, 0),new RSTile(3090, 3233, 0),
                        new RSTile(3090, 3232, 0),new RSTile(3090, 3231, 0),new RSTile(3090, 3230, 0),new RSTile(3090, 3229, 0),new RSTile(3090, 3228, 0),new RSTile(3090, 3227, 0)};
                break;
            case EDGEVILLE:
                Path = new RSTile[]{new RSTile(3094, 3490, 0),new RSTile(3093, 3490, 0),new RSTile(3092, 3490, 0),new RSTile(3091, 3490, 0),new RSTile(3090, 3490, 0),new RSTile(3089, 3490, 0),
                        new RSTile(3088, 3490, 0),new RSTile(3087, 3489, 0),new RSTile(3086, 3488, 0),new RSTile(3085, 3487, 0),new RSTile(3084, 3486, 0),new RSTile(3083, 3485, 0),
                        new RSTile(3082, 3484, 0),new RSTile(3081, 3483, 0),new RSTile(3081, 3482, 0),new RSTile(3081, 3481, 0),new RSTile(3081, 3480, 0),new RSTile(3081, 3479, 0),
                        new RSTile(3081, 3478, 0),new RSTile(3080, 3477, 0),new RSTile(3080, 3476, 0),new RSTile(3080, 3475, 0),new RSTile(3080, 3474, 0),new RSTile(3080, 3473, 0),
                        new RSTile(3080, 3472, 0),new RSTile(3080, 3471, 0),new RSTile(3080, 3470, 0),new RSTile(3080, 3469, 0),new RSTile(3080, 3468, 0),new RSTile(3080, 3467, 0),
                        new RSTile(3081, 3467, 0),new RSTile(3082, 3467, 0),new RSTile(3083, 3467, 0),new RSTile(3084, 3466, 0),new RSTile(3085, 3465, 0),new RSTile(3086, 3464, 0),
                        new RSTile(3086, 3463, 0),new RSTile(3087, 3462, 0),new RSTile(3088, 3461, 0),new RSTile(3089, 3460, 0),new RSTile(3090, 3460, 0),new RSTile(3091, 3459, 0),
                        new RSTile(3091, 3458, 0),new RSTile(3091, 3457, 0),new RSTile(3091, 3456, 0),new RSTile(3092, 3455, 0),new RSTile(3093, 3455, 0),new RSTile(3093, 3454, 0),
                        new RSTile(3093, 3453, 0),new RSTile(3093, 3452, 0),new RSTile(3093, 3451, 0),new RSTile(3093, 3450, 0),new RSTile(3092, 3449, 0),new RSTile(3091, 3448, 0),
                        new RSTile(3091, 3447, 0),new RSTile(3091, 3446, 0),new RSTile(3091, 3445, 0),new RSTile(3092, 3445, 0),new RSTile(3093, 3444, 0),new RSTile(3094, 3444, 0),
                        new RSTile(3094, 3443, 0),new RSTile(3094, 3442, 0),new RSTile(3094, 3441, 0),new RSTile(3094, 3440, 0),new RSTile(3094, 3439, 0),new RSTile(3095, 3439, 0),
                        new RSTile(3096, 3438, 0),new RSTile(3097, 3437, 0),new RSTile(3098, 3436, 0),new RSTile(3099, 3435, 0),new RSTile(3100, 3435, 0),new RSTile(3101, 3435, 0),
                        new RSTile(3102, 3435, 0),new RSTile(3103, 3434, 0),new RSTile(3104, 3433, 0),new RSTile(3104, 3432, 0)};
                break;
            case FISHING_GUILD:
                Path = new RSTile[]{new RSTile(2587, 3420, 0),new RSTile(2588, 3419, 0),new RSTile(2589, 3418, 0),new RSTile(2590, 3417, 0),new RSTile(2591, 3417, 0)
                        ,new RSTile(2591, 3416, 0),new RSTile(2592, 3415, 0),new RSTile(2593, 3415, 0),new RSTile(2593, 3414, 0),new RSTile(2593, 3413, 0),new RSTile(2594, 3412, 0),
                        new RSTile(2594, 3411, 0),new RSTile(2595, 3410, 0),new RSTile(2596, 3409, 0),new RSTile(2596, 3408, 0),new RSTile(2597, 3407, 0),new RSTile(2598, 3406, 0),
                        new RSTile(2599, 3405, 0),new RSTile(2600, 3405, 0),new RSTile(2600, 3404, 0),new RSTile(2601, 3405, 0),new RSTile(2602, 3405, 0),new RSTile(2603, 3405, 0),
                        new RSTile(2604, 3405, 0),new RSTile(2605, 3404, 0),new RSTile(2606, 3403, 0),new RSTile(2607, 3403, 0)};
                break;
           case SEER_VILLAGE:
               Path = new RSTile[]{new RSTile(2725, 3491, 0),new RSTile(2725, 3490, 0),new RSTile(2725, 3489, 0),new RSTile(2725, 3488, 0),new RSTile(2725, 3487, 0),new RSTile(2726, 3486, 0),
                       new RSTile(2727, 3486, 0),new RSTile(2728, 3486, 0),new RSTile(2729, 3486, 0),new RSTile(2730, 3486, 0),new RSTile(2731, 3486, 0),new RSTile(2732, 3486, 0),
                       new RSTile(2733, 3487, 0),new RSTile(2734, 3488, 0),new RSTile(2735, 3489, 0),new RSTile(2735, 3490, 0),new RSTile(2735, 3491, 0),new RSTile(2735, 3492, 0),
                       new RSTile(2736, 3493, 0),new RSTile(2737, 3494, 0),new RSTile(2737, 3495, 0),new RSTile(2738, 3496, 0),new RSTile(2739, 3497, 0),new RSTile(2740, 3498, 0),
                       new RSTile(2740, 3499, 0),new RSTile(2740, 3500, 0),new RSTile(2740, 3501, 0),new RSTile(2740, 3502, 0),new RSTile(2740, 3503, 0),new RSTile(2740, 3504, 0),
                       new RSTile(2740, 3505, 0),new RSTile(2740, 3506, 0),new RSTile(2740, 3507, 0),new RSTile(2740, 3508, 0),new RSTile(2740, 3509, 0),new RSTile(2740, 3510, 0),
                       new RSTile(2740, 3511, 0),new RSTile(2740, 3512, 0),new RSTile(2740, 3513, 0),new RSTile(2740, 3514, 0),new RSTile(2739, 3514, 0),new RSTile(2738, 3515, 0),
                       new RSTile(2737, 3516, 0),new RSTile(2737, 3517, 0),new RSTile(2737, 3518, 0),new RSTile(2737, 3519, 0),new RSTile(2736, 3520, 0),new RSTile(2735, 3521, 0),
                       new RSTile(2734, 3522, 0),new RSTile(2733, 3523, 0),new RSTile(2733, 3524, 0),new RSTile(2732, 3524, 0),new RSTile(2732, 3525, 0),new RSTile(2731, 3525, 0),new RSTile(2730, 3525, 0)};
                break;
        }



        Walking.walking_timeout = random(2000, 4000);

    }


    public boolean walkToBank() {
        return MWalking.walkPath(Path,true);
    }

    public boolean walkToFish() {
        return MWalking.walkPath(Path,false);
    }

    public boolean bankIsNear() {
        RSNPC banker[] = NPCs.find(bank.getBankerID());
        return banker.length > 0 && banker != null && Player.getPosition().distanceTo(banker[0].getPosition()) < bank.getMaxDistance();
    }

    public boolean boothIsNear() {
        RSObject booth[] = Objects.findNearest(bank.getMaxDistance(), bank.getBoothID());
        println("boothLength = " + booth.length);
        return booth.length > 0 && booth != null && Player.getPosition().distanceTo(booth[0].getPosition()) < bank.getMaxDistance();
    }

    public boolean fishIsNear() {
        RSNPC spot[] = NPCs.find(pool.getID());
        return spot.length > 0 && spot != null;
    }

}
