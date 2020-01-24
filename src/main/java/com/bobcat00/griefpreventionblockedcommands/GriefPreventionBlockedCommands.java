package com.bobcat00.griefpreventionblockedcommands;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class GriefPreventionBlockedCommands extends JavaPlugin implements Listener {
    
    //Listener listeners;

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
    }
 
    @Override
    public void onDisable()
    {
        //HandlerList.unregisterAll(listeners);
    }
    
    // Commands to process - Must have a trailing space to help avoid conflicts
    private String[] commands = { "trust ",                           "tr ",
                                  "accesstrust ",                     "at ",
                                  "containertrust ",                  "ct ",
                                  "permissiontrust ",                 "pt ",
                                  "griefprevention:trust ",           "griefprevention:tr ",
                                  "griefprevention:accesstrust ",     "griefprevention:at ",
                                  "griefprevention:containertrust ",  "griefprevention:ct ",
                                  "griefprevention:permissiontrust ", "griefprevention:pt "};
    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
    {
        Player p = e.getPlayer();
        
        for (String command : this.commands)
        {
            if (e.getMessage().toLowerCase().startsWith("/" + command.toLowerCase()))
            {
                // Make sure the player is standing in a claim
                Claim claim = GriefPrevention.instance.dataStore.getClaimAt(p.getLocation(), true, null);
                if (claim == null)
                {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You must be standing in a claim to trust someone.");
                    p.sendMessage(ChatColor.RED + "To add someone to your creative plot, use " + ChatColor.WHITE + "/plot trust <player>");
                }
                else
                {
                    this.getLogger().info(p.getName() + " issued trust command for claim " + claim.getID() + ".");
                
                } // end if claim == null
                
                break;
            
            } // end if command matched
        
        } // end for
    
    } // end onPlayerCommandPreprocess

}
