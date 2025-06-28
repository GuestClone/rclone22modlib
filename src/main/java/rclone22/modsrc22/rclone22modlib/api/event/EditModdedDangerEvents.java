package rclone22.modsrc22.rclone22modlib.api.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;

@Cancelable
public class EditModdedDangerEvents extends Event {

    ///  All is Entity so it is broad
    public final Entity e;

    public EditModdedDangerEvents(Entity e) {
       this.e = e;
     }

    ///  This is used if you dont want to specifically or manually set values of different events nested from EditModdedDangerEvents
    /// Cancels all the events that are in the nested class below:
    public boolean isEventCancelled() {

        if (this.isCanceled())
        {
            return true;
        }

        if (e instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) e;
           if (AbsResEntityAttributes.hasAbsResAttrResistanceEffect(entityLiving))
           {
               return true;
           }
        }

            return false;
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
             return isEventCancelled() || this.isCanceled();
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
            return isEventCancelled() || this.isCanceled();
        }

    }

    ///  Cancelling checkShield will make player not need a shield in its slot, and will stop item from corroding if in planets like venus
    @Cancelable
    public static class EditCheckShieldEvent extends EditModdedDangerEvents {


        public EditCheckShieldEvent(Entity player) {
            super(player);
        }

        public boolean isCheckShieldCancelled() {
            return isEventCancelled() || this.isCanceled();
        }



    }


    /// HBM
    @Cancelable
    public static class EditDigamma extends EditModdedDangerEvents {


        public EditDigamma(Entity player) {
            super(player);
        }

        public boolean isDigammaImmune() {
            return isEventCancelled() || this.isCanceled();
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
            return isEventCancelled() || this.isCanceled();
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
            return isEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditCoal extends EditModdedDangerEvents {

        public EditCoal(Entity e) {
            super(e);
        }

        public boolean isCoalImmune() {
            return isEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditFaraday extends EditModdedDangerEvents {


        public EditFaraday(Entity e) {
            super(e);
        }

        public boolean isFaradayImmune() {
            return isEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditHazmat extends EditModdedDangerEvents {

        public EditHazmat(Entity e) {
            super(e);
        }

        public boolean isHazmat() {
            return isEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditContagion extends EditModdedDangerEvents {



        public EditContagion(Entity e) {
            super(e);
        }

        public boolean isContagionImmune() {
            return isEventCancelled() || this.isCanceled();
        }



    }

    @Cancelable
    public static class EditTANTemp extends EditModdedDangerEvents {

        public EditTANTemp(Entity e) {
            super(e);
        }

        ///  Completely makes player not get hypo or hyperthermia
        public boolean isShouldTemp() {
            return isEventCancelled() || this.isCanceled();
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
            return isEventCancelled() || this.isCanceled();
        }

    }


}
