package rclone22.modsrc22.rclone22modlib.api.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;


///  Main event, if cancelled, will cancel anything (modded and vanilla) that damages any Entity that is under EntityLivingBase (e.g Player)
///  Also take note this event only CANCELS events but not add custom functions (You have to manually add them in that specific event)
///  All this those is if you want a simple event that wont make an entity get harmed
@Cancelable
public class EditModdedDangerEvents extends Event {

    ///  All is Entity so it is broad
    private final Entity e;

    private boolean isDamageCancelled;

    private boolean isModDamageCancelled;

    private boolean isDeathCancelled;

    public EditModdedDangerEvents(Entity e) {
       this.e = e;
     }

    public Entity getEntity() {
        return e;
    }

    /// Cancels all (Broadest)
    public boolean isAllEventCancelled() {


        if (this.isCanceled())
        {
            return true;
        }
            return false;
    }

    ///  Cancels only modded execpt for Vanilla Minecraft Damages
    public boolean isModdedEventCancelled() {

        if (this.isModDamageCancelled || this.isAllEventCancelled())
        {
            return true;
        }

        return false;
    }

    public void setModDamageCancelled(boolean value) {
        this.isModDamageCancelled = value;
    }

    ///  Cancels Damage only
    /// And makes entity invulnerable
    public boolean isDamageCancelled() {

        if (this.isDamageCancelled || this.isAllEventCancelled())
        {
            return true;
        }

        return false;
    }

    public void setDamageCancelled(boolean value) {
        this.isDamageCancelled = value;
    }

    ///  Cancels Death only
    public boolean isDeathCancelled() {

        if (this.isDeathCancelled || this.isAllEventCancelled())
        {
            return true;
        }

        return false;
    }

    public void setDeathCancelled(boolean value) {
        this.isDeathCancelled = value;
    }


    ///  Hunger Minecraft
    @Cancelable
    public static class EditHungerEvent extends EditModdedDangerEvents {

        public final EntityPlayer e;

        public EditHungerEvent(EntityPlayer e) {
            super(e);
            this.e = e;
        }

        public void doFoodStats(int foodLevel, float saturationLevel)
        {
            e.getFoodStats().setFoodLevel(foodLevel);
            e.getFoodStats().setFoodSaturationLevel(saturationLevel);
        }

        public void doFoodFullStats()
        {
           this.doFoodStats(20, 5.0F);
        }

        public void ifCancelledDoFullStats()
        {
            if (this.isHungerEventCancel())
            {
                this.doFoodFullStats();
            }
        }

        ///  Cancels hunger
        public boolean isHungerEventCancel() {
            return isModdedEventCancelled() || this.isCanceled();
        }

    }


     ///  Galactricraft
     @Cancelable
    public static class EditThermalStatusEvent extends EditModdedDangerEvents {

        int normalThermalLevel = 0;


        public EditThermalStatusEvent(Entity e) {
            super(e);
        }

         public int getNormalThermalLevel() {
             return normalThermalLevel;
         }

         public void setNormalThermalLevel(int normalThermalLevel) {
             this.normalThermalLevel = normalThermalLevel;
         }

         ///  Cancel if you want to not use the setNormalizeValue
         /// Will set thermal value to 3 = safe
         public boolean isNormalThermalEventCancel() {
             return isModdedEventCancelled() || this.isCanceled();
         }


    }

    ///  If you want to cancel Oxygen Event in Galacticraft/GC (make entity not need oxygen but still survive in space)
    /// And if you dont want to also make Oxygen event if your mod doesnt want GC to be a dependant mod
    /// Use this event
    @Cancelable
    public static class EditGCOxygenEvent extends EditModdedDangerEvents {

        public EditGCOxygenEvent(Entity e) {
            super(e);
        }

        public boolean isGCSuffocationCancelled() {
            return isModdedEventCancelled() || this.isCanceled();
        }

    }

    /// HBM
    @Cancelable
    public static class EditDigamma extends EditModdedDangerEvents {


        public EditDigamma(Entity player) {
            super(player);
        }

        public boolean isDigammaImmune() {
            return isModdedEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditRadiation extends EditModdedDangerEvents {

        private float radiationResistance = 0.0F;

        public EditRadiation(Entity player) {
            super(player);
        }

        ///  Powerful
        /// Setting to true makes Entity immune to radiation
        /// Without setting a resistance value for it
        public boolean isRadiationImmune() {
            return isModdedEventCancelled() || this.isCanceled();
        }

        public float getRadiationResistance()
        {
            return radiationResistance;
        }

        public void setRadiationResistance(float value) {
            this.radiationResistance = value;
        }

        ///  Use this if you want to add more resistance over an existing resistance value
        /// I Recommend this
        public void setRadiationResistanceBetterVers(float value) {
            setRadiationResistance(getRadiationResistance() + value);
        }

    }

    @Cancelable
    public static class EditAsbestos extends EditModdedDangerEvents {



        public EditAsbestos(Entity e) {
            super(e);
        }

        public boolean isAsbestosImmune() {
            return isModdedEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditCoal extends EditModdedDangerEvents {

        public EditCoal(Entity e) {
            super(e);
        }

        public boolean isCoalImmune() {
            return isModdedEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditFaraday extends EditModdedDangerEvents {


        public EditFaraday(Entity e) {
            super(e);
        }

        public boolean isFaradayImmune() {
            return isModdedEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditHazmat extends EditModdedDangerEvents {

        public EditHazmat(Entity e) {
            super(e);
        }

        public boolean isHazmat() {
            return isModdedEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditContagion extends EditModdedDangerEvents {



        public EditContagion(Entity e) {
            super(e);
        }

        public boolean isContagionImmune() {
            return isModdedEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditTANTemp extends EditModdedDangerEvents {

        public EditTANTemp(Entity e) {
            super(e);
        }

        ///  Completely makes player not get hypo or hyperthermia
        public boolean isShouldTemp() {
            return isModdedEventCancelled() || this.isCanceled();
        }


    }

    @Cancelable
    public static class EditTANThirst extends EditModdedDangerEvents {

        private int thirst = 0;

        private float hydration = 0F;

        private int ticks = 0;

        public EditTANThirst(Entity e) {
            super(e);
        }

        public float getHydration()
        {
            return hydration;
        }

        public void setHydration(float value) {
            this.hydration = value;
        }

        public int getTicks()
        {
            return ticks;
        }

        public void setTicks(int value) {
            this.ticks = value;
        }

        public int getThirst()
        {
            return thirst;
        }

        public void setThirst(int value) {
            this.thirst = value;
        }


        ///  Completely makes player not get dehydrated
        public boolean isShouldThirst() {
            return isModdedEventCancelled() || this.isCanceled();
        }

    }


}
