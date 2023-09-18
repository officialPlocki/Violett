package co.plocki.inventory.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class InteractableItem {

    private final ItemStack javaItem;
    private final ItemStack bedrockItem;
    private boolean glowing;
    private String displayName;
    private List<String> lore;

    /**
     * The InteractableItem function is a constructor for the InteractableItem class.
     * It takes in an ItemStack object and sets it as the javaItem variable of this class.
     * The bedrockItem variable is set to null, since we don't know what it will be yet.

     *
     * @param javaItem Create the interactableitem object
     *
     * @return An itemstack
     *
     * @docauthor Trelent
     */
    public InteractableItem(ItemStack javaItem) {
        this.javaItem = javaItem;
        this.bedrockItem = null;
    }

    /**
     * The setBedrockItem function is used to set the bedrock item of an InteractableItem.
     *
     *
     * @param bedrockItem Set the bedrock item
     *
     * @return The interactableitem object itself
     *
     * @docauthor Trelent
     */
    public InteractableItem setBedrockItem(ItemStack bedrockItem) {
        return this;
    }

    /**
     * The setGlowing function sets the glowing variable to true or false.
     *
     *
     * @param glowing Determine whether the item is glowing or not
     *
     * @return This, which is the interactableitem
     *
     * @docauthor Trelent
     */
    public InteractableItem setGlowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    /**
     * The setDisplayName function sets the display name of an InteractableItem.
     *
     *
     * @param displayName Set the displayname of the item
     *
     * @return This
     *
     * @docauthor Trelent
     */
    public InteractableItem setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * The setLore function sets the lore of an item.
     *
     *
     * @param lore Set the lore of the item
     *
     * @return An interactableitem
     *
     * @docauthor Trelent
     */
    public InteractableItem setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * The setLore function sets the lore of an item.
     *
     *
     * @param lore Create a list of strings
     *
     * @return The interactableitem object
     *
     * @docauthor Trelent
     */
    public InteractableItem setLore(String... lore) {
        this.lore = List.of(lore);
        return this;
    }

    /**
     * The getBedrockItem function is used to get the bedrock item.
     * <p>
     *
     *
     * @return An itemstack
     *
     * @docauthor Trelent
     */
    public ItemStack getBedrockItem() {
        ItemStack itemStack;
        if(bedrockItem != null) {
            itemStack = bedrockItem;
        } else {
            itemStack = javaItem;
        }
        assert itemStack != null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        if (glowing) {
            itemMeta.addEnchant(Enchantment.KNOCKBACK, 0, true);
        }
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * The getJavaItem function is used to get the ItemStack object that represents this item.
     * It will return a new ItemStack every time it is called, so if you want to store the item in a variable,
     * make sure you use clone() on it. This function also sets all of the metadata for this item (display name, lore, etc.)

     * <p>
     *
     * @return The itemstack variable
     *
     * @docauthor Trelent
     */
    public ItemStack getJavaItem() {
        ItemStack itemStack = javaItem;
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        if (glowing) {
            itemMeta.addEnchant(Enchantment.KNOCKBACK, 0, true);
        }
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public abstract ClickResult onClick(Player player, ItemStack itemStack, Inventory inventory, InventoryClickEvent event);
}
