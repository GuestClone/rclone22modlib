package rclone22.modsrc22.rclone22modlib.api.attributes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import rclone22.modsrc22.rclone22modlib.main.Constant;

public class AbsResEntityAttributes
{

    /**
     * @SuppressWarnings("ConstantConditions") to shut up the always true or always false warning
     */

    public static final Double maxDoubleVAL = Double.MAX_VALUE;



    public static final IAttribute ABS_RES_ATTR = (new RangedAttribute(null,
            Constant.MODID+".abs_res_attr",
            0.0D,
            0.0D,
            maxDoubleVAL)) // Max value of this attribute
            .setDescription("Absolute Resistance Attribute")
            .setShouldWatch(true);



    @SuppressWarnings("ConstantConditions")
    public static void registerAttributes(EntityLivingBase entity) {
        if (entity.getAttributeMap().getAttributeInstance(ABS_RES_ATTR) == null) {
            entity.getAttributeMap().registerAttribute(ABS_RES_ATTR);
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static IAttributeInstance getOrRegisterAbsResAttr(EntityLivingBase entity) {
        IAttributeInstance attr = entity.getAttributeMap().getAttributeInstance(ABS_RES_ATTR);
        if (attr == null) {
            try {
                entity.getAttributeMap().registerAttribute(ABS_RES_ATTR);
                attr = entity.getEntityAttribute(ABS_RES_ATTR);
            } catch (IllegalArgumentException ignored) {
                attr = entity.getEntityAttribute(ABS_RES_ATTR);
            }
        }
        return attr;
    }

    public static double getAbsResAttrResistance(EntityLivingBase entity) {
        IAttributeInstance attr = getOrRegisterAbsResAttr(entity);
        return attr != null ? attr.getAttributeValue() : 0.0D;
    }

    /// If you are lazy to do AbsResEntityAttributes.getAbsResAttrResistance(entity) >= whateverDoubleValue higher than 0.0D ///
    public static boolean hasAbsResAttrResistanceEffect(EntityLivingBase entity) {
        return getAbsResAttrResistance(entity) > 0.0D;
    }

    public static void setAbsResAttrResistance(EntityLivingBase entity, double value) {
        IAttributeInstance attr = getOrRegisterAbsResAttr(entity);
        if (attr != null) {
            attr.setBaseValue(value + attr.getAttributeValue());
        }
    }

}
