package net.samongi.SamongiLib.Items.Comparators;

import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class BlockDataAgeableComparator implements BlockDataComparator {
    @Override
    public int compare(BlockData first, BlockData second) {
        if(!(first instanceof Ageable && second instanceof Ageable))
        {
           return 0;
        }
        Ageable firstAgeable = (Ageable) first;
        Ageable secondAgeable = (Ageable) second;

        if(firstAgeable.getAge() > secondAgeable.getAge())
        {
            return 1;
        } else if(firstAgeable.getAge() < secondAgeable.getAge())
        {
            return -1;
        } else {
            return 0;
        }
    }
}
