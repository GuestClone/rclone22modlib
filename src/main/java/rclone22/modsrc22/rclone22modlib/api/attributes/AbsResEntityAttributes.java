package rclone22.modsrc22.rclone22modlib.api.attributes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import rclone22.modsrc22.rclone22modlib.main.Constant;

import java.util.UUID;

public class AbsResEntityAttributes
{

    /**
     * @SuppressWarnings("ConstantConditions") to shut up the always true or always false warning
     */

    public static final Double maxDoubleVAL = Double.MAX_VALUE;

    public static final UUID ABS_RES_UUID = UUID.fromString("c2b63198-745b-11ee-b962-0242ac120002");

    public static final IAttribute ABS_RES_ATTR = (new RangedAttribute(null,
            Constant.MODID+".abs_res_attr",
            0.0D,
            0.0D,
            maxDoubleVAL)) // Max value of this attribute
            .setDescription("Absolute Resistance Attribute")
            .setShouldWatch(true);


    AttributeModifier mod = new AttributeModifier(
            ABS_RES_UUID,
            "abs_res_modifier",
            1.0D,
            0 // 0 = ADD_NUMBER, 1 = MULTIPLY_BASE, 2 = MULTIPLY_TOTAL
    );

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
        if (attr != null) {
            AttributeModifier mod = attr.getModifier(ABS_RES_UUID);
            return mod != null ? mod.getAmount() : 0.0D;
        }
        return 0.0D;
    }

    /// If you are lazy to do AbsResEntityAttributes.getAbsResAttrResistance(entity) >= whateverDoubleValue higher than 0.0D ///
    public static boolean hasAbsResAttrResistanceEffect(EntityLivingBase entity) {
        return getAbsResAttrResistance(entity) > 0.0D;
    }

    public static void setAbsResAttrResistance(EntityLivingBase entity, double value) {
        IAttributeInstance attr = getOrRegisterAbsResAttr(entity);
        if (attr != null) {

            AttributeModifier existing = attr.getModifier(ABS_RES_UUID);
            if (existing != null) {
                attr.removeModifier(existing);
            }

            if (value > 0.0D) {

                AttributeModifier modifier = new AttributeModifier(
                        ABS_RES_UUID,
                        "Absolute Resistance Modifier",
                        value,
                        0 // Operations: 0 = Add, 1 = Multiply Base, 2 = Multiply Total
                );

                attr.applyModifier(modifier);
            }
        }
    }


    public static void clearAbsResAttrResistance(EntityLivingBase entity) {
        IAttributeInstance attr = getOrRegisterAbsResAttr(entity);
        if (attr != null) {
            AttributeModifier mod = attr.getModifier(ABS_RES_UUID);
            if (mod != null) {
                attr.removeModifier(mod);
            }
        }
    }

}
