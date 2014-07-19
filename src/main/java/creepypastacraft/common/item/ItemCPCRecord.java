package creepypastacraft.common.item;

import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import creepypastacraft.CPC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ItemCPCRecord extends ItemRecord {
    private static final Map records = new HashMap();
    public final String recordName;

    public ItemCPCRecord(String recordName) {
        super(recordName);
        this.recordName = recordName;
        this.maxStackSize = 1;
        this.setCreativeTab(CPC.tabCPC);
        records.put(recordName, this);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("cpc:" + "record_" + recordName);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player,
                             World world, int x, int y, int z,
                             int par7, float par8, float par9, float par10) {
        if (world.getBlock(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0) {
            if (world.isRemote) {
                return true;
            } else {
                //TODO:						  .insertRecord()
                ((BlockJukebox) Blocks.jukebox).func_149926_b(world, x, y, z, stack);
                world.playAuxSFXAtEntity(null, 1005, x, y, z, Item.getIdFromItem(this));
                --stack.stackSize;
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
                               boolean par4) {
        list.add(this.getRecordNameLocal());
    }

    @Override
    public String getRecordNameLocal() {
        return I18n.format(this.getUnlocalizedName() + ".desc");
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.rare;
    }

    public static ItemCPCRecord getRecord(String name) {
        return (ItemCPCRecord) records.get(name);
    }

    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation("cpc:" + name);
    }
}
