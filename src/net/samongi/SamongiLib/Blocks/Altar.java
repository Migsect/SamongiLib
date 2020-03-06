package net.samongi.SamongiLib.Blocks;

import net.samongi.SamongiLib.Items.MaskedBlockData;
import net.samongi.SamongiLib.Vector.SamIntVector;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Altar {

    private static MaskedBlockData.Mask[] DEFAULT_BLOCK_DATA_COMPARATORS = new MaskedBlockData.Mask[]{MaskedBlockData.Mask.MATERIAL};

    //--------------------------------------------------------------------------------------------------------------------//
    private List<RelativeBlock> m_relativeBlocks = new ArrayList<>();
    private List<MaskedBlockData> m_possibleFocusBlocks = new ArrayList<>();

    private Map<Character, MaskedBlockData> m_blockDataTranslation = new HashMap<>();

    //--------------------------------------------------------------------------------------------------------------------//

    public Altar() {
        addTranslation('_', Material.AIR.createBlockData());
    }

    //--------------------------------------------------------------------------------------------------------------------//
    public void addPossibleFocusBlock(@Nonnull BlockData blockData) {
        addPossibleFocusBlock(new MaskedBlockData(blockData, DEFAULT_BLOCK_DATA_COMPARATORS));
    }
    public void addPossibleFocusBlock(@Nonnull MaskedBlockData blockData) {
        m_possibleFocusBlocks.add(blockData);
    }

    public void addRelativeBlock(@Nonnull BlockData blockData, @Nonnull SamIntVector displacement) {
        addRelativeBlock(new MaskedBlockData(blockData, DEFAULT_BLOCK_DATA_COMPARATORS), displacement);
    }
    public void addRelativeBlock(@Nonnull MaskedBlockData blockData, @Nonnull SamIntVector displacement) {
        RelativeBlock relativeBlock = new RelativeBlock(blockData, displacement);
        m_relativeBlocks.add(relativeBlock);
    }

    public void addTranslation(char character, @Nonnull BlockData blockData) {
        m_blockDataTranslation.put(character, new MaskedBlockData(blockData, DEFAULT_BLOCK_DATA_COMPARATORS));
    }
    public void addTranslation(char character, @Nonnull MaskedBlockData blockData) {
        m_blockDataTranslation.put(character, blockData);
    }

    public MaskedBlockData getTranslation(char character) {
        return m_blockDataTranslation.get(character);
    }

    private boolean checkFocusBlock(@Nonnull BlockData blockData) {
        for (MaskedBlockData maskedBlockData : m_possibleFocusBlocks) {
            if (maskedBlockData.equals(blockData)) {
                return true;
            }
        }
        return false;
    }
    public void addLayer(int verticalDisplacement, @Nonnull String[] pattern) {

        int colLength = pattern.length;
        if (pattern.length % 2 != 1) {
            throw new IllegalArgumentException();
        }

        // validating the rows to make sure they are uniform.
        int rowLength = -1;
        for (String row : pattern) {
            if (row.length() % 2 != 1) {
                throw new IllegalArgumentException();
            }
            if (row.length() != rowLength && rowLength > 0) {
                throw new IllegalArgumentException();
            }
            rowLength = row.length();
        }

        int colMidPoint = colLength / 2;
        int rowMidPoint = rowLength / 2;

        for (int col = 0; col < rowLength; col++) {
            for (int row = 0; row < colLength; row++) {
                int rowDisplacement = row - colMidPoint;
                int colDisplacement = col - rowMidPoint;
                char character = pattern[row].charAt(col);
                if (character == ' ') {
                    continue;
                }
                MaskedBlockData blockData = getTranslation(character);
                addRelativeBlock(blockData, new SamIntVector(rowDisplacement, verticalDisplacement, colDisplacement));
            }
        }
    }
    //--------------------------------------------------------------------------------------------------------------------//
    public boolean checkPattern(@Nonnull Block centerBlock) {
        if (centerBlock == null) {
            return false;
        }
        BlockData centerBlockData = centerBlock.getBlockData();
        if (!checkFocusBlock(centerBlockData)) {
            return false;
        }

        for (RelativeBlock relativeBlock : m_relativeBlocks) {
            if (!relativeBlock.matchRelativeToBlock(centerBlock)) {
                return false;
            }
        }

        return true;
    }

}
