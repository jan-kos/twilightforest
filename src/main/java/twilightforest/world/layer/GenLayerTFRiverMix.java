package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFRiverMix extends GenLayer {

    private GenLayer biomeLayer;
    private GenLayer riverLayer;

    public GenLayerTFRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
        super(par1);
        this.biomeLayer = par3GenLayer;
        this.riverLayer = par4GenLayer;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed
     * (passed in as an argument).
     */
    public void initWorldGenSeed(long par1) {
        this.biomeLayer.initWorldGenSeed(par1);
        this.riverLayer.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as
     * temperatures, rainfall amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] biomeInputs = this.biomeLayer.getInts(par1, par2, par3, par4);
        int[] riverInputs = this.riverLayer.getInts(par1, par2, par3, par4);
        int[] outputs = IntCache.getIntCache(par3 * par4);

        for (int i = 0; i < par3 * par4; ++i) {
            if (riverInputs[i] == TFBiomeBase.stream.biomeID) {
                outputs[i] = riverInputs[i] & 255;
            } else {
                outputs[i] = biomeInputs[i];
            }
        }

        return outputs;
    }

}
