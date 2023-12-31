package OceanRuinGenerator;

import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mcfeature.loot.LootTable;
import com.seedfinding.mcfeature.loot.MCLootTables;

import java.util.HashMap;

public class OceanRuinStructureData {
    public static final HashMap<String, BPos> STRUCTURE_SIZE = new HashMap<>() {{
        this.put("underwater_ruin/big_brick_1", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_brick_2", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_brick_3", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_brick_8", new BPos(16, 16, 16));

        this.put("underwater_ruin/big_cracked_1", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_cracked_2", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_cracked_3", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_cracked_8", new BPos(16, 16, 16));

        this.put("underwater_ruin/big_mossy_1", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_mossy_2", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_mossy_3", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_mossy_8", new BPos(16, 16, 16));

        this.put("underwater_ruin/big_warm_4", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_warm_5", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_warm_6", new BPos(16, 16, 16));
        this.put("underwater_ruin/big_warm_7", new BPos(16, 16, 16));

        this.put("underwater_ruin/brick_1", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_2", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_3", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_4", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_5", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_6", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_7", new BPos(6, 7, 7));
        this.put("underwater_ruin/brick_8", new BPos(6, 7, 7));

        this.put("underwater_ruin/cracked_1", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_2", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_3", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_4", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_5", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_6", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_7", new BPos(6, 7, 7));
        this.put("underwater_ruin/cracked_8", new BPos(6, 7, 7));

        this.put("underwater_ruin/mossy_1", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_2", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_3", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_4", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_5", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_6", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_7", new BPos(6, 7, 7));
        this.put("underwater_ruin/mossy_8", new BPos(6, 7, 7));

        this.put("underwater_ruin/warm_1", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_2", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_3", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_4", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_5", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_6", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_7", new BPos(6, 7, 7));
        this.put("underwater_ruin/warm_8", new BPos(6, 7, 7));
    }};

    public static final HashMap<String, LootTable> STRUCTURE_LOOT = new HashMap<>() {{
        this.put("underwater_ruin/big_brick_1", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_brick_2", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_brick_3", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_brick_8", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());

        this.put("underwater_ruin/big_cracked_1", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_cracked_2", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_cracked_3", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_cracked_8", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());

        this.put("underwater_ruin/big_mossy_1", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_mossy_2", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_mossy_3", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_mossy_8", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());

        this.put("underwater_ruin/big_warm_4", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_warm_5", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_warm_6", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());
        this.put("underwater_ruin/big_warm_7", MCLootTables.UNDERWATER_RUIN_BIG_CHEST.get());

        this.put("underwater_ruin/brick_1", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_2", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_3", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_4", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_5", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_6", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_7", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/brick_8", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());

        this.put("underwater_ruin/cracked_1", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_2", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_3", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_4", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_5", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_6", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_7", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/cracked_8", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());

        this.put("underwater_ruin/mossy_1", null);
        this.put("underwater_ruin/mossy_2", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/mossy_3", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/mossy_4", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/mossy_5", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/mossy_6", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/mossy_7", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/mossy_8", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());

        this.put("underwater_ruin/warm_1", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_2", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_3", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_4", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_5", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_6", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_7", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
        this.put("underwater_ruin/warm_8", MCLootTables.UNDERWATER_RUIN_SMALL_CHEST.get());
    }};

    public static final HashMap<String, BPos> STRUCTURE_LOOT_OFFSETS = new HashMap<>() {{
        this.put("underwater_ruin/big_brick_1", new BPos(5, 0, 4));
        this.put("underwater_ruin/big_brick_2", new BPos(9, 1, 10));
        this.put("underwater_ruin/big_brick_3", new BPos(12, 2, 2));
        this.put("underwater_ruin/big_brick_8", new BPos(5, 0, 4));

        this.put("underwater_ruin/big_cracked_1", new BPos(5, 0, 4));
        this.put("underwater_ruin/big_cracked_2", new BPos(9, 1, 10));
        this.put("underwater_ruin/big_cracked_3", new BPos(12, 2, 2));
        this.put("underwater_ruin/big_cracked_8", new BPos(5, 0, 4));

        this.put("underwater_ruin/big_mossy_1", new BPos(5, 0, 4));
        this.put("underwater_ruin/big_mossy_2", new BPos(9, 1, 10));
        this.put("underwater_ruin/big_mossy_3", new BPos(12, 2, 2));
        this.put("underwater_ruin/big_mossy_8", new BPos(5, 0, 4));

        this.put("underwater_ruin/big_warm_4", new BPos(11, 0, 8));
        this.put("underwater_ruin/big_warm_5", new BPos(7, 0, 7));
        this.put("underwater_ruin/big_warm_6", new BPos(10, 0, 9));
        this.put("underwater_ruin/big_warm_7", new BPos(11, 0, 7));

        this.put("underwater_ruin/brick_1", new BPos(3, 1, 5));
        this.put("underwater_ruin/brick_2", new BPos(2, 0, 1));
        this.put("underwater_ruin/brick_3", new BPos(1, 0, 5));
        this.put("underwater_ruin/brick_4", new BPos(1, 1, 4));
        this.put("underwater_ruin/brick_5", new BPos(4, 0, 4));
        this.put("underwater_ruin/brick_6", new BPos(2, 0, 2));
        this.put("underwater_ruin/brick_7", new BPos(1, 0, 3));
        this.put("underwater_ruin/brick_8", new BPos(3, 1, 4));

        this.put("underwater_ruin/cracked_1", new BPos(3, 1, 5));
        this.put("underwater_ruin/cracked_2", new BPos(2, 0, 1));
        this.put("underwater_ruin/cracked_3", new BPos(1, 0, 5));
        this.put("underwater_ruin/cracked_4", new BPos(1, 1, 4));
        this.put("underwater_ruin/cracked_5", new BPos(4, 0, 4));
        this.put("underwater_ruin/cracked_6", new BPos(2, 0, 2));
        this.put("underwater_ruin/cracked_7", new BPos(1, 0, 3));
        this.put("underwater_ruin/cracked_8", new BPos(3, 1, 4));

        this.put("underwater_ruin/mossy_1", null);
        this.put("underwater_ruin/mossy_2", new BPos(2, 0, 1));
        this.put("underwater_ruin/mossy_3", new BPos(1, 0, 5));
        this.put("underwater_ruin/mossy_4", new BPos(1, 1, 4));
        this.put("underwater_ruin/mossy_5", new BPos(4, 0, 4));
        this.put("underwater_ruin/mossy_6", new BPos(2, 0, 2));
        this.put("underwater_ruin/mossy_7", new BPos(1, 0, 3));
        this.put("underwater_ruin/mossy_8", new BPos(3, 1, 4));

        this.put("underwater_ruin/warm_1", new BPos(3, 1, 1));
        this.put("underwater_ruin/warm_2", new BPos(3, 1, 4));
        this.put("underwater_ruin/warm_3", new BPos(3, 0, 4));
        this.put("underwater_ruin/warm_4", new BPos(1, 0, 2));
        this.put("underwater_ruin/warm_5", new BPos(3, 0, 4));
        this.put("underwater_ruin/warm_6", new BPos(4, 1, 4));
        this.put("underwater_ruin/warm_7", new BPos(3, 0, 3));
        this.put("underwater_ruin/warm_8", new BPos(3, 0, 3));
    }};
}
