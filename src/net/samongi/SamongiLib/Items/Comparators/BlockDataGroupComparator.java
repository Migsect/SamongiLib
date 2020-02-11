package net.samongi.SamongiLib.Items.Comparators;

import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BlockDataGroupComparator implements BlockDataComparator {

    private List<BlockDataComparator> m_comparators = new ArrayList<BlockDataComparator>();

    public BlockDataGroupComparator(final List<BlockDataComparator> comparators)
    {
        m_comparators.addAll(comparators);
    }
    public BlockDataGroupComparator(BlockDataComparator[] comparators)
    {
        m_comparators.addAll(Arrays.asList(comparators));
    }

    @Override
    public int compare(BlockData first, BlockData second) {
        int negatives = 0;
        int positives = 0;
        int zeros = 0;
        for(BlockDataComparator comparator : m_comparators)
        {
            int result = comparator.compare(first, second);
            if(result > 0)
            {
                positives += 1;
            } else if(result < 0) {
                negatives += 1;
            }
            else
            {
                zeros += 1;
            }
        }

        if(negatives == 0 && positives == 0 && zeros > 0)
        {
            return 0;
        }

        if(negatives > positives)
        {
            return 1;
        } else if (negatives < positives)
        {
            return -1;
        } else if (zeros > 0)
        {
            return 0;
        }

        // We are deciding that we'll just return greater-than in the case that nothing is defined.
        return 1;
    }

    void addComparator(BlockDataComparator comparator)
    {
        m_comparators.add(comparator);
    }
    void addComparators(Collection<BlockDataComparator> comparators)
    {
        m_comparators.addAll(comparators);
    }
}
