package co.plocki.heads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class MinecraftHead {

    /**
     * The getHeadFromURL function takes a URL as an argument and returns a player head with the skin of the player
     * whose skin is at that URL. This function uses reflection to access private fields in Bukkit's SkullMeta class,
     * which allows us to set the GameProfile of a skull item. The GameProfile contains information about what type of
     * entity it is (in this case, we want PLAYER), as well as properties such as its name and textures. We can use this
     * function to get any Minecraft player's head by using their username or UUID: https://minotar.net/helm/{username}
     *
     * @param String url Get the url of the head
     *
     * @return A player head with the url as skin
     *
     * @docauthor Trelent
     */
    public ItemStack getHeadFromURL(String url) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(url.isEmpty()) return head;

        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", url));

        try {
            java.lang.reflect.Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);
        return head;
    }

    /**
     * The getHeadFromURL function takes a URL and a display name, and returns an ItemStack of type PLAYER_HEAD with the given display name.
     * The function uses reflection to set the texture of the head to be that of the image at url.
     *
     *
     * @param String url Get the url of the head
     * @param String displayName Set the name of the item
     *
     * @return A player head with the given url as its skin
     *
     * @docauthor Trelent
     */
    public ItemStack getHeadFromURL(String url, String displayName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(url.isEmpty()) return head;

        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);

        ItemMeta im = head.getItemMeta();
        im.setDisplayName(displayName);
        head.setItemMeta(im);
        return head;
    }

    /**
     * The getHeadFromURL function takes in a URL, display name, and lore list.
     * It then creates an ItemStack of type PLAYER_HEAD (a skull).
     * If the url is empty it returns the head. Otherwise it sets up a GameProfile with the given UUID and texture URL.
     * Then we use reflection to set that profile on our SkullMeta object so that when we return our head itemstack, it has the correct skin applied to it!

     *
     * @param String url Get the url of the skin
     * @param String displayName Set the name of the item
     * @param List&lt;String&gt; lore Set the lore of the item
     *
     * @return A head with a custom skin
     *
     * @docauthor Trelent
     */
    public ItemStack getHeadFromURL(String url, String displayName, List<String> lore) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(url.isEmpty()) return head;

        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);

        ItemMeta im = head.getItemMeta();
        assert im != null;
        im.setDisplayName(displayName);
        im.setLore(lore);
        head.setItemMeta(im);
        return head;
    }

}
