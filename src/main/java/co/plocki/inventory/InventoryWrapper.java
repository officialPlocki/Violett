package co.plocki.inventory;

import co.plocki.inventory.item.InteractableItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class InventoryWrapper {

    public static final Map<Player, InventoryWrapper> INVENTORIES = new HashMap<>();
    private final HashMap<Integer, InteractableItem> items;
    private final Inventory inventory;
    private Player player;
    private final String inventoryTitle;
    private final boolean preventClick;

    private ItemStack placeholderItem;

    /**
     * The InventoryWrapper function is a constructor that creates an inventory with the given title and size.
     * It also sets the preventClick variable to false, which means that clicking on items in this inventory will not be prevented.
     * The placeholderItem variable is set to a gray stained glass pane item with no lore or name, but it has been given a display name of &quot;$f&quot;.

     *
     * @param inventoryTitle Set the title of the inventory
     * @param size Determine the size of the inventory
     * @param preventClick Prevent the player from clicking on items in the inventory
     *
     * @return An inventorywrapper object
     *
     * @docauthor Trelent
     */
    public InventoryWrapper(String inventoryTitle, int size, boolean preventClick) {
        this.inventoryTitle = inventoryTitle;
        this.inventory = Bukkit.createInventory(null, size, inventoryTitle);
        this.preventClick = preventClick;
        this.items = new HashMap<>();

        placeholderItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta itemMeta = placeholderItem.getItemMeta();
        itemMeta.setDisplayName("§f");
        placeholderItem.setItemMeta(itemMeta);
    }

    /**
     * The InventoryWrapper function is a constructor that creates an InventoryWrapper object.
     *
     *
     * @param inventoryTitle Set the title of the inventory
     * @param size Set the size of the inventory
     *
     * @return The inventorywrapper class
     *
     * @docauthor Trelent
     */
    public InventoryWrapper(String inventoryTitle, int size) {
        this(inventoryTitle, size, true);
    }

    /**
     * The addBedrockCompatibility function is used to add bedrock compatibility to the inventory.
     * This function will only work if you have a player object in your class, and it will not work if you are using this wrapper on an offline player.
     *
     *
     * @param player Get the player's inventory
     *
     * @return The inventorywrapper object
     *
     * @docauthor Trelent
     */
    public InventoryWrapper addBedrockCompatibility(Player player) {
        this.player = player;
        return this;
    }

    /**
     * The setPlaceholderItem function sets the placeholder item for this inventory.
     *
     *
     * @param placeholderItem Set the placeholder item
     *
     * @return The inventorywrapper itself, so that you can chain methods
     *
     * @docauthor Trelent
     */
    public InventoryWrapper setPlaceholderItem(ItemStack placeholderItem) {
        this.placeholderItem = placeholderItem;
        return this;
    }

    public InventoryWrapper setPlaceholderItem(Material material) {
        this.placeholderItem = new ItemStack(material);
        ItemMeta itemMeta = placeholderItem.getItemMeta();
        itemMeta.setDisplayName("§f");
        placeholderItem.setItemMeta(itemMeta);
        return this;
    }

    public void buildInventory() {
        INVENTORIES.put(player, this);
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, placeholderItem);
        }

        if(player != null) {
            if(player.getUniqueId().toString().startsWith("00000000-")) {
                for (Map.Entry<Integer, InteractableItem> entry : items.entrySet()) {
                    inventory.setItem(entry.getKey(), entry.getValue().getBedrockItem());
                }
            } else {
                for (Map.Entry<Integer, InteractableItem> entry : items.entrySet()) {
                    inventory.setItem(entry.getKey(), entry.getValue().getJavaItem());
                }
            }
        } else {
            for (Map.Entry<Integer, InteractableItem> entry : items.entrySet()) {
                inventory.setItem(entry.getKey(), entry.getValue().getJavaItem());
            }
        }

        player.openInventory(inventory);
    }

    public void closeInventory() {
        player.closeInventory();
    }

    public static class ClickEventListener implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            if(INVENTORIES.containsKey((Player) event.getWhoClicked())) {
                InventoryWrapper inventoryWrapper = INVENTORIES.get((Player) event.getWhoClicked());
                if(event.getView().getTitle().equalsIgnoreCase(inventoryWrapper.inventoryTitle)) {
                    if(inventoryWrapper.preventClick) {
                        event.setCancelled(true);
                    }
                    if(event.getCurrentItem() != null) {
                        if(event.getCurrentItem().getItemMeta() != null) {
                            if(event.getCurrentItem().getItemMeta().getDisplayName() != null) {
                                if(event.getCurrentItem().getItemMeta().hasDisplayName()) {
                                    if(event.getCurrentItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_PLACED_ON)) {
                                        if(inventoryWrapper.items.containsKey(event.getSlot())) {
                                            inventoryWrapper.items.get(event.getSlot()).onClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getClickedInventory(), event);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
