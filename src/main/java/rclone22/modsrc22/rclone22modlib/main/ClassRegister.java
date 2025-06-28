package rclone22.modsrc22.rclone22modlib.main;

import net.minecraftforge.common.MinecraftForge;

public class ClassRegister
{

    /// Classes should not have constructor
    /// Usually use this for classes (with no constructors) that hold @SubscribeEvent
    /// No not making this be able to handle multiple strings in one parameter
    public static void registEventClassString(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.newInstance();
            ClassRegister.registerHandlers(instance);
            System.out.println("Registered event handler: " + className);
        } catch (ClassNotFoundException e) {
            System.err.println("Event handler class not found: " + className);
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Failed to instantiate event handler: " + className);
            e.printStackTrace();
        }
    }

    public static void registerHandlers(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
        MinecraftForge.EVENT_BUS.register(handlers);
    }

}
