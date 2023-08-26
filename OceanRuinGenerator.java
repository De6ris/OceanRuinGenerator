package RuinsGenerator;

import com.google.common.collect.ImmutableSet;
import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mcbiome.source.OverworldBiomeSource;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockMirror;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.LootTable;
import com.seedfinding.mcfeature.loot.item.ItemStack;
import com.seedfinding.mcmath.util.Mth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OceanRuinGenerator {
    private static final List<String> WARM_RUINS = Arrays.asList("underwater_ruin/warm_1", "underwater_ruin/warm_2", "underwater_ruin/warm_3", "underwater_ruin/warm_4", "underwater_ruin/warm_5", "underwater_ruin/warm_6", "underwater_ruin/warm_7", "underwater_ruin/warm_8");
    private static final List<String> BRICK_RUINS = Arrays.asList("underwater_ruin/brick_1", "underwater_ruin/brick_2", "underwater_ruin/brick_3", "underwater_ruin/brick_4", "underwater_ruin/brick_5", "underwater_ruin/brick_6", "underwater_ruin/brick_7", "underwater_ruin/brick_8");
    private static final List<String> CRACKED_RUINS = Arrays.asList("underwater_ruin/cracked_1", "underwater_ruin/cracked_2", "underwater_ruin/cracked_3", "underwater_ruin/cracked_4", "underwater_ruin/cracked_5", "underwater_ruin/cracked_6", "underwater_ruin/cracked_7", "underwater_ruin/cracked_8");
    private static final List<String> MOSSY_RUINS = Arrays.asList("underwater_ruin/mossy_1", "underwater_ruin/mossy_2", "underwater_ruin/mossy_3", "underwater_ruin/mossy_4", "underwater_ruin/mossy_5", "underwater_ruin/mossy_6", "underwater_ruin/mossy_7", "underwater_ruin/mossy_8");
    private static final List<String> BIG_BRICK_RUINS = Arrays.asList("underwater_ruin/big_brick_1", "underwater_ruin/big_brick_2", "underwater_ruin/big_brick_3", "underwater_ruin/big_brick_8");
    private static final List<String> BIG_MOSSY_RUINS = Arrays.asList("underwater_ruin/big_mossy_1", "underwater_ruin/big_mossy_2", "underwater_ruin/big_mossy_3", "underwater_ruin/big_mossy_8");
    private static final List<String> BIG_CRACKED_RUINS = Arrays.asList("underwater_ruin/big_cracked_1", "underwater_ruin/big_cracked_2", "underwater_ruin/big_cracked_3", "underwater_ruin/big_cracked_8");
    private static final List<String> BIG_WARM_RUINS = Arrays.asList("underwater_ruin/big_warm_4", "underwater_ruin/big_warm_5", "underwater_ruin/big_warm_6", "underwater_ruin/big_warm_7");

    private static final ImmutableSet<Integer> WARM_BIOMES = ImmutableSet.of
            (Biomes.WARM_OCEAN.getId(), Biomes.LUKEWARM_OCEAN.getId(), Biomes.DEEP_WARM_OCEAN.getId(), Biomes.DEEP_LUKEWARM_OCEAN.getId());
    private MCVersion version;
    private long structureSeed;
    private List<Piece> pieces;
    private boolean isLarge;
    private boolean isCluster;

    public OceanRuinGenerator(MCVersion version) {
        this.version = version;
        this.pieces = new ArrayList<>();
    }

    public boolean generate(long worldSeed, CPos ruinPos) {
        this.structureSeed = worldSeed & Mth.MASK_48;
        return generate(worldSeed, ruinPos, new ChunkRand());
    }

    private boolean generate(long worldSeed, CPos pos, ChunkRand rand) {
        rand.setCarverSeed(worldSeed, pos.getX(), pos.getZ(), version);
        BPos blockPos = new BPos(pos.getX() << 4, 90, pos.getZ() << 4);
        BlockRotation rotation1 = BlockRotation.getRandom(rand);
        OverworldBiomeSource obs = new OverworldBiomeSource(version, worldSeed);
        Biome biome = obs.getBiomeForNoiseGen((pos.getX() << 2) + 2, 0, (pos.getZ() << 2) + 2);
        boolean isWarm = WARM_BIOMES.contains(biome.getId());
        this.isLarge = rand.nextFloat() <= 0.3F;
        float integrity = isLarge ? 0.9F : 0.8F;
        addPiece(isWarm, isLarge, rand, blockPos, rotation1, integrity);
        this.isCluster = isLarge && rand.nextFloat() <= 0.9F;
        if (isCluster) {
            int i1 = blockPos.getX();
            int j1 = blockPos.getZ();
            BPos bPos1 = getTransformedPos(new BPos(15, 0, 15), rotation1).add(i1, 0, j1);
            BlockBox box1 = new BlockBox(blockPos, bPos1);
            BPos bPos2 = new BPos(Math.min(i1, bPos1.getX()), 0, Math.min(j1, bPos1.getZ()));
            List<BPos> list = getRoomPositions(rand, bPos2.getX(), bPos2.getZ());
            int k = rand.nextInt(5) + 4;
            for (int l = 0; l < k; l++) {
                if (!list.isEmpty()) {
                    int i2 = rand.nextInt(list.size());
                    BPos bPos3 = list.remove(i2);
                    int j2 = bPos3.getX();
                    int k2 = bPos3.getZ();
                    BlockRotation rotation2 = BlockRotation.getRandom(rand);
                    BPos bPos4 = getTransformedPos(new BPos(5, 0, 6), rotation2).add(j2, 0, k2);
                    BlockBox box2 = new BlockBox(bPos3, bPos4);
                    if (!box2.intersects(box1)) {
                        addPiece(isWarm, false, rand, bPos3, rotation2, 0.8F);
                    }
                }
            }
        }
        return true;
    }

    public List<Pair<BPos, List<ItemStack>>> generateLoot() {
        ChunkRand rand = new ChunkRand();
        List<Pair<BPos, List<ItemStack>>> result = new ArrayList<>();
        List<Pair<BPos, LootTable>> chestsPos = new ArrayList<>();
        for (OceanRuinGenerator.Piece p : pieces) {
            List<LootTable> tables = OceanRuinStructureData.STRUCTURE_LOOT.get(p.name);
            int size = tables.size();
            if (size != 0) {
                List<BPos> pos = new ArrayList<>();
                for (BPos offset : OceanRuinStructureData.STRUCTURE_LOOT_OFFSETS.get(p.name)) {
                    pos.add(p.pos.add(p.getTransformedPos(offset, p.rotation)));
                }
                for (int i = 0; i < size; i++) {
                    chestsPos.add(new Pair<>(pos.get(i), tables.get(i)));
                }
            }
        }
        List<CPos> chunkPos = new ArrayList<>();
        for (Pair<BPos, LootTable> chest : chestsPos) {
            CPos chunk = chest.getFirst().toChunkPos();
            rand.setDecoratorSeed(structureSeed, chunk.getX() * 16, chunk.getZ() * 16, 40009, version);
            if (chunkPos.contains(chunk)) {
                int num = Collections.frequency(chunkPos, chunk);
                for (int i = 0; i < num; i++) {
                    rand.nextLong();
                }
                LootContext context = new LootContext(rand.nextLong(), version);
                List<ItemStack> items = chest.getSecond().generate(context);
                result.add(new Pair<>(chest.getFirst(), items));
                chunkPos.add(chunk);
                continue;
            }
            LootContext context = new LootContext(rand.nextLong(), version);
            List<ItemStack> items = chest.getSecond().generate(context);
            result.add(new Pair<>(chest.getFirst(), items));
            chunkPos.add(chunk);
        }
        return result;
    }


    private static List<BPos> getRoomPositions(ChunkRand rand, int x, int z) {
        List<BPos> list = new ArrayList<>();
        list.add(new BPos(x - 15 + rand.nextInt(8), 90, z + 17 + rand.nextInt(7)));
        list.add(new BPos(x - 15 + rand.nextInt(8), 90, z + 1 + rand.nextInt(7)));
        list.add(new BPos(x - 15 + rand.nextInt(8), 90, z - 12 + rand.nextInt(5)));
        list.add(new BPos(x + 1 + rand.nextInt(7), 90, z + 17 + rand.nextInt(7)));
        list.add(new BPos(x + 1 + rand.nextInt(7), 90, z - 12 + rand.nextInt(3)));
        list.add(new BPos(x + 17 + rand.nextInt(7), 90, z + 19 + rand.nextInt(6)));
        list.add(new BPos(x + 17 + rand.nextInt(7), 90, z + 1 + rand.nextInt(7)));
        list.add(new BPos(x + 17 + rand.nextInt(7), 90, z - 12 + rand.nextInt(5)));
        return list;
    }

    public BPos getTransformedPos(BPos targetPos, BlockRotation rotationIn) {
        int i = targetPos.getX();
        int j = targetPos.getY();
        int k = targetPos.getZ();
        switch (rotationIn) {
            case COUNTERCLOCKWISE_90:
                return new BPos(k, j, -i);
            case CLOCKWISE_90:
                return new BPos(-k, j, +i);
            case CLOCKWISE_180:
                return new BPos(-i, j, -k);
            default:
                return targetPos;
        }
    }

    static Piece getPieceFromName(String template, BPos bPos, BlockRotation rotation, float integrity) {
        BPos size = OceanRuinStructureData.STRUCTURE_SIZE.get(template);
        BlockBox box = BlockBox.getBoundingBox(bPos, rotation, BPos.ORIGIN, BlockMirror.NONE, size);
        int centerX = (box.minX + box.maxX) / 2;
        int centerZ = (box.minZ + box.maxZ) / 2;
        int centerY = box.minY + 1;
        Piece piece = new Piece(template, bPos, box, rotation, integrity);
        piece.move(0, bPos.getY() - centerY, 0);
        return piece;
    }

    static String getType(List<String> list, ChunkRand rand) {
        return list.get(rand.nextInt(list.size()));
    }

    void addPiece(boolean isWarm, boolean isLarge, ChunkRand rand, BPos blockPos, BlockRotation rotation, float integrity) {
        if (isWarm) {
            String template = isLarge ? getType(BIG_WARM_RUINS, rand) : getType(WARM_RUINS, rand);
            Piece piece = getPieceFromName(template, blockPos, rotation, integrity);
            pieces.add(piece);
        } else {
            List<String> templates1 = isLarge ? BIG_BRICK_RUINS : BRICK_RUINS;
            List<String> templates2 = isLarge ? BIG_CRACKED_RUINS : CRACKED_RUINS;
            List<String> templates3 = isLarge ? BIG_MOSSY_RUINS : MOSSY_RUINS;
            int i = rand.nextInt(templates1.size());
            String template1 = templates1.get(i);
            Piece piece1 = getPieceFromName(template1, blockPos, rotation, integrity);
            String template2 = templates2.get(i);
            Piece piece2 = getPieceFromName(template2, blockPos, rotation, 0.7F);
            String template3 = templates3.get(i);
            Piece piece3 = getPieceFromName(template3, blockPos, rotation, 0.5F);
            pieces.add(piece1);
            pieces.add(piece2);
            pieces.add(piece3);
        }
    }

    static public class Piece {
        String name;
        public BPos pos;
        BlockBox box;
        public BlockRotation rotation;
        float integrity;

        public String getName() {
            return this.name;
        }

        Piece(String name, BPos pos, BlockBox box, BlockRotation rotation, float integrity) {
            this.name = name;
            this.pos = pos;
            this.box = box;
            this.rotation = rotation;
            this.integrity = integrity;
        }

        public void move(int x, int y, int z) {
            box.move(x, y, z);
            pos = pos.add(x, y, z);
        }

        public BPos getTransformedPos(BPos targetPos, BlockRotation rotationIn) {
            int i = targetPos.getX();
            int j = targetPos.getY();
            int k = targetPos.getZ();
            switch (rotationIn) {
                case COUNTERCLOCKWISE_90:
                    return new BPos(k, j, -i);
                case CLOCKWISE_90:
                    return new BPos(-k, j, +i);
                case CLOCKWISE_180:
                    return new BPos(-i, j, -k);
                default:
                    return targetPos;
            }
        }
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public boolean isLarge() {
        return this.isLarge;
    }

    public boolean isCluster() {
        return this.isCluster;
    }
}