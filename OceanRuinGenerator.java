package OceanRuinGenerator;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Block;
import com.seedfinding.mccore.block.Blocks;
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
import com.seedfinding.mcterrain.terrain.OverworldTerrainGenerator;

import java.util.*;

public class OceanRuinGenerator {
    private static final List<String> WARM_RUINS = Arrays.asList("underwater_ruin/warm_1", "underwater_ruin/warm_2", "underwater_ruin/warm_3", "underwater_ruin/warm_4", "underwater_ruin/warm_5", "underwater_ruin/warm_6", "underwater_ruin/warm_7", "underwater_ruin/warm_8");
    private static final List<String> BRICK_RUINS = Arrays.asList("underwater_ruin/brick_1", "underwater_ruin/brick_2", "underwater_ruin/brick_3", "underwater_ruin/brick_4", "underwater_ruin/brick_5", "underwater_ruin/brick_6", "underwater_ruin/brick_7", "underwater_ruin/brick_8");
    private static final List<String> CRACKED_RUINS = Arrays.asList("underwater_ruin/cracked_1", "underwater_ruin/cracked_2", "underwater_ruin/cracked_3", "underwater_ruin/cracked_4", "underwater_ruin/cracked_5", "underwater_ruin/cracked_6", "underwater_ruin/cracked_7", "underwater_ruin/cracked_8");
    private static final List<String> MOSSY_RUINS = Arrays.asList("underwater_ruin/mossy_1", "underwater_ruin/mossy_2", "underwater_ruin/mossy_3", "underwater_ruin/mossy_4", "underwater_ruin/mossy_5", "underwater_ruin/mossy_6", "underwater_ruin/mossy_7", "underwater_ruin/mossy_8");
    private static final List<String> BIG_BRICK_RUINS = Arrays.asList("underwater_ruin/big_brick_1", "underwater_ruin/big_brick_2", "underwater_ruin/big_brick_3", "underwater_ruin/big_brick_8");
    private static final List<String> BIG_MOSSY_RUINS = Arrays.asList("underwater_ruin/big_mossy_1", "underwater_ruin/big_mossy_2", "underwater_ruin/big_mossy_3", "underwater_ruin/big_mossy_8");
    private static final List<String> BIG_CRACKED_RUINS = Arrays.asList("underwater_ruin/big_cracked_1", "underwater_ruin/big_cracked_2", "underwater_ruin/big_cracked_3", "underwater_ruin/big_cracked_8");
    private static final List<String> BIG_WARM_RUINS = Arrays.asList("underwater_ruin/big_warm_4", "underwater_ruin/big_warm_5", "underwater_ruin/big_warm_6", "underwater_ruin/big_warm_7");

    private static final List<Integer> WARM_BIOMES = Arrays.asList(Biomes.WARM_OCEAN.getId(), Biomes.LUKEWARM_OCEAN.getId(), Biomes.DEEP_WARM_OCEAN.getId(), Biomes.DEEP_LUKEWARM_OCEAN.getId());

    private MCVersion version;
    private long worldSeed;
    private List<Piece> pieces;
    private boolean isLarge;
    private boolean isCluster;

    public OceanRuinGenerator(MCVersion version) {
        this.version = version;
        this.pieces = new ArrayList<>();
    }
    public boolean generate(OverworldTerrainGenerator otg, CPos pos, ChunkRand rand) {
        long seed = otg.getWorldSeed();
        this.worldSeed = seed;
        rand.setCarverSeed(seed, pos.getX(), pos.getZ(), version);
        BPos blockPos = new BPos(pos.getX() << 4, 90, pos.getZ() << 4);
        BlockRotation startRotation = BlockRotation.getRandom(rand);
        Biome biome = otg.getBiomeSource().getBiomeForNoiseGen((pos.getX() << 2) + 2, 0, (pos.getZ() << 2) + 2);
        boolean isWarm = WARM_BIOMES.contains(biome.getId());
        this.isLarge = rand.nextFloat() <= 0.3F;
        float integrity = isLarge ? 0.9F : 0.8F;
        addPiece(otg,isWarm,isLarge,rand,blockPos,startRotation,integrity);
        this.isCluster = isLarge && rand.nextFloat() <= 0.9F;
        if (isCluster) {
            int i1 = blockPos.getX();
            int j1 = blockPos.getZ();
            BPos bPos1 = getTransformedPos(new BPos(15,0,15), startRotation).add(i1,0,j1);
            BlockBox box1 = new BlockBox(blockPos,bPos1);
            BPos bPos2 = new BPos(Math.min(i1,bPos1.getX()),0,Math.min(j1,bPos1.getZ()));
            List<BPos> list = getRoomPositions(rand, bPos2.getX(), bPos2.getZ());
            int k = rand.nextInt(5) + 4;
            for(int l = 0; l < k; l++) {
                if (!list.isEmpty()) {
                    int i2 = rand.nextInt(list.size());
                    BPos bPos3 = list.remove(i2);
                    int j2 = bPos3.getX();
                    int k2 = bPos3.getZ();
                    BlockRotation rotation = BlockRotation.getRandom(rand);
                    BPos bPos4 = getTransformedPos(new BPos(5,0,6), rotation).add(j2,0,k2);
                    BlockBox box2 = new BlockBox(bPos3,bPos4);
                    if (!box2.intersects(box1)) {
                        addPiece(otg,isWarm,false,rand,bPos3,rotation,0.8F);
                    }
                }
            }
        }
        return true;
    }

    public List<Pair<BPos, List<ItemStack>>> generateLoot() {
        List<BPos> allChestPositions = new ArrayList<>();
        List<Pair<BPos, LootTable>> chestInformations = new ArrayList<>();
        for (Piece piece : pieces) {
            LootTable lootTable = OceanRuinStructureData.STRUCTURE_LOOT.get(piece.name);
            if (lootTable != null) {
                BPos chestPos = getChestPos(piece);
                allChestPositions.add(chestPos);
                chestInformations.add(new Pair<>(chestPos, lootTable));
            }
        }
        List<BPos> chestPositions = new ArrayList<>();
        for (Pair<BPos, LootTable> chestInformation : chestInformations) {
            BPos bPos = chestInformation.getFirst();
            if (chestPositions.contains(bPos)) {
                chestPositions.remove(bPos);
                chestPositions.add(bPos);
                continue;
            }
            chestPositions.add(bPos);
        }
        ChunkRand rand = new ChunkRand();
        List<Pair<BPos,List<ItemStack>>> result = new ArrayList<>();
        for (BPos chestPosition : chestPositions) {
            int num = Collections.frequency(allChestPositions, chestPosition);
            CPos chestCPos = chestPosition.toChunkPos();
            rand.setDecoratorSeed(worldSeed,chestCPos.getX()<<4,chestCPos.getZ()<<4,40009,version);
            if (num > 1) {
                for (int i = 0 ; i < num-1 ; i++) {
                    rand.nextLong();
                }
            }
            LootContext lootContext = new LootContext(rand.nextLong(),version);
            int index = allChestPositions.lastIndexOf(chestPosition);
            List<ItemStack> items = chestInformations.get(index).getSecond().generate(lootContext);
            result.add(new Pair<>(chestPosition,items));
        }
        return result;
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

    public static class Piece {
        String name;
        BPos pos;
        BlockBox box;
        BlockRotation rotation;
        float integrity;

        public String getName() {
            return this.name;
        }

        public BPos getPos() {
            return this.pos;
        }

        public BlockBox getBox() {
            return this.box;
        }

        public BlockRotation getRotation() {
            return this.rotation;
        }

        public float getIntegrity() {
            return this.integrity;
        }

        Piece(String name, BPos pos, BlockBox box, BlockRotation rotation, float integrity) {
            this.name = name;
            this.pos = pos;
            this.box = box;
            this.rotation = rotation;
            this.integrity = integrity;
        }
    }

    void addPiece(OverworldTerrainGenerator otg, boolean isWarm, boolean isLarge, ChunkRand rand, BPos blockPos, BlockRotation rotation, float integrity) {
        if (isWarm) {
            String template = isLarge ? getType(BIG_WARM_RUINS, rand) : getType(WARM_RUINS, rand);
            Piece piece = getPiece(otg,template,blockPos,rotation,integrity);
            pieces.add(piece);
        }
        else {
            List<String> templates1 = isLarge ? BIG_BRICK_RUINS : BRICK_RUINS;
            List<String> templates2 = isLarge ? BIG_CRACKED_RUINS : CRACKED_RUINS;
            List<String> templates3 = isLarge ? BIG_MOSSY_RUINS : MOSSY_RUINS;
            int i = rand.nextInt(templates1.size());
            String template1 = templates1.get(i);
            Piece piece1 = getPiece(otg,template1,blockPos,rotation,integrity);
            String template2 = templates2.get(i);
            Piece piece2 = getPiece(otg,template2,blockPos,rotation,0.7F);
            String template3 = templates3.get(i);
            Piece piece3 = getPiece(otg,template3,blockPos,rotation,0.5F);
            pieces.add(piece1);
            pieces.add(piece2);
            pieces.add(piece3);
        }
    }

    Piece getPiece(OverworldTerrainGenerator otg, String template, BPos templatePos, BlockRotation rotation, float integrity) {
        BPos size = OceanRuinStructureData.STRUCTURE_SIZE.get(template);
        BlockBox box = BlockBox.getBoundingBox(templatePos, rotation, BPos.ORIGIN, BlockMirror.NONE, size);
        templatePos = new BPos(templatePos.getX(),otg.getHeightOnGround(templatePos.getX(), templatePos.getZ()), templatePos.getZ());
        BPos bPos = getTransformedPos(new BPos(size.getX()-1,0,size.getZ()-1),rotation).add(templatePos);
        templatePos = new BPos(templatePos.getX(),getYforPiece(otg,templatePos,bPos),templatePos.getZ());
        return new Piece(template,bPos,box,rotation,integrity);
    }

    List<BPos> getRoomPositions(ChunkRand rand, int x, int z) {
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

    BPos getTransformedPos(BPos targetPos, BlockRotation rotationIn) {
        int i = targetPos.getX();
        int j = targetPos.getY();
        int k = targetPos.getZ();
        switch(rotationIn) {
            case COUNTERCLOCKWISE_90:
                return new BPos(k,j, -i);
            case CLOCKWISE_90:
                return new BPos(-k,j, +i);
            case CLOCKWISE_180:
                return new BPos(-i, j, -k);
            default:
                return targetPos;
        }
    }

    int getYforPiece(OverworldTerrainGenerator otg,BPos templatePos,BPos templateTransformedPos) {
        int i1 = templatePos.getY();
        int j1 = 512;
        int k1 = i1 - 1;
        int l1 = 0;
        for (BPos bPos : getAllInBoxMutable(templatePos,templateTransformedPos)) {
            int i2 = bPos.getX();
            int j2 = bPos.getZ();
            int k2 = templatePos.getY() - 1;
            BPos mutable = new BPos(i2,j2,k2);
            Block block = otg.getBlockAt(mutable).get();
            for(Block b = otg.getBlockAt(mutable).get(); (block.equals(Blocks.AIR) || b.equals(Blocks.WATER)) && k2 > 1; b = otg.getBlockAt(mutable).get()) {
                --k2;
                mutable = new BPos(i2,j2,k2);
                block = otg.getBlockAt(mutable).get();
            }
            j1 = Math.min(j1, k2);
            if (k2 < k1 - 2) {
                ++l1;
            }
        }
        int l2 = Math.abs(templatePos.getX() - templateTransformedPos.getX());
        if (k1 - j1 > 2 && l1 > l2 - 2) {
            i1 = j1 + 1;
        }
        return i1;
    }

    List<BPos> getAllInBoxMutable(BPos firstPos, BPos secondPos) {
        int x1 = Math.min(firstPos.getX(),secondPos.getX());
        int y1 = Math.min(firstPos.getY(),secondPos.getY());
        int z1 = Math.min(firstPos.getZ(),secondPos.getZ());
        int x2 = Math.max(firstPos.getX(),secondPos.getX());
        int y2 = Math.max(firstPos.getY(),secondPos.getY());
        int z2 = Math.max(firstPos.getZ(),secondPos.getZ());
        int i = x2 - x1 + 1;
        int j = y2 - y1 + 1;
        int k = z2 - z1 + 1;
        int l = i * j * k;
        List<BPos> bPosList = new ArrayList<>();
        for (int h = 0; h < l; h++) {
            int i1 = h % i;
            int j1 = h / i;
            int k1 = j1 % j;
            int l1 = j1 / j;
            bPosList.add(new BPos(x1 + i1, y1 + k1, z1 + l1));
        }
        return bPosList;
    }

    BPos getChestPos(Piece piece) {
        CPos cPos = piece.pos.toChunkPos();
        switch (piece.rotation) {
            case COUNTERCLOCKWISE_90:
                cPos = new CPos(cPos.getX(),cPos.getZ()+1);
            case CLOCKWISE_90:
                cPos = new CPos(cPos.getX()+1,cPos.getZ());
            case CLOCKWISE_180:
                cPos = new CPos(cPos.getX()+1,cPos.getZ()+1);
        }
        BPos offset = getTransformedPos(OceanRuinStructureData.STRUCTURE_LOOT_OFFSETS.get(piece.name),piece.rotation);
        return cPos.toBlockPos(piece.pos.getY()).add(offset);
    }

    String getType(List<String> list, ChunkRand rand) {
        return list.get(rand.nextInt(list.size()));
    }
}
