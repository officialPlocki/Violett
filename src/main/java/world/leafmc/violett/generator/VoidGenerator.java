package world.leafmc.violett.generator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    private final boolean singleBlock;

    public VoidGenerator(boolean singleBlock) {
        this.singleBlock = singleBlock;
    }

    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
        ChunkData data = this.createChunkData(world);
        if(singleBlock) {
            if(x == 0 && z == 0) {
                data.setBlock(0, 64, 0, Material.STONE);
            }
        }
        return data;
    }

    public final Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return new Location(world, 0.0D, 65, 0.0D);
    }
}
