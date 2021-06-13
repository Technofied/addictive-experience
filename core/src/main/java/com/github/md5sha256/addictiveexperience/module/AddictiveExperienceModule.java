package com.github.md5sha256.addictiveexperience.module;

import com.github.md5sha256.addictiveexperience.api.AddictiveExperience;
import com.github.md5sha256.addictiveexperience.api.drugs.DrugHandler;
import com.github.md5sha256.addictiveexperience.api.drugs.IPlantHandler;
import com.github.md5sha256.addictiveexperience.api.forms.IDrugForms;
import com.github.md5sha256.addictiveexperience.api.slur.SlurEffectState;
import com.github.md5sha256.addictiveexperience.implementation.DrugHandlerImpl;
import com.github.md5sha256.addictiveexperience.implementation.SlurEffectStateImpl;
import com.github.md5sha256.addictiveexperience.implementation.forms.DrugForms;
import com.github.md5sha256.addictiveexperience.implementation.plant.PlantHandlerImpl;
import com.github.md5sha256.spigotutils.module.BukkitPlatformModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class AddictiveExperienceModule extends AbstractModule {

    private final AddictiveExperience api;
    private final JavaPlugin plugin;

    public AddictiveExperienceModule(@NotNull AddictiveExperience api, @NotNull JavaPlugin plugin) {
        this.api = api;
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        // Drugs Implementation
        install(new DrugsModule());

        // Platform stuff
        install(new BukkitPlatformModule());
        bind(Plugin.class).toInstance(this.plugin);
        bind(JavaPlugin.class).toInstance(this.plugin);

        // Implementation
        bind(DrugHandler.class).to(DrugHandlerImpl.class).asEagerSingleton();
        bind(IPlantHandler.class).to(PlantHandlerImpl.class).asEagerSingleton();
        bind(SlurEffectState.class).to(SlurEffectStateImpl.class).asEagerSingleton();

        // API bindings
        bind(IDrugForms.class).to(DrugForms.class).in(Singleton.class);
        bind(AddictiveExperience.class).toInstance(this.api);
    }
}
