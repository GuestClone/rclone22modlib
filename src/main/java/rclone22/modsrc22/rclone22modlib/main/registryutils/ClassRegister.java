package rclone22.modsrc22.rclone22modlib.main.registryutils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.rclone22modlib.api.ModChecker;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

///  Rules to register classes with @SubscribeEvent
///  Do not have constructors
///  Must atleast follow how you make an event in Forge (one paremeter in method, no multiples)
///  And must not be private or inaccessible
///  If class is registered and its events doesnt work ingame
///  Either its because of the modifiers (Must be public by default)
public class ClassRegister
{

    /// Classes should not have constructor and Classes should be public (same with its methods)
    /// Usually use this for classes (with no constructors) that hold @SubscribeEvent methods
    public static void registEventClassByString(String[] modid, String... classNames) {
        for (String modids : modid) {
            for (String className : classNames) {
                try {
                    Class<?> clazz = Class.forName(className);

                    registEventClassByManual(new String[]{modids}, clazz);

                } catch (Exception e) {
                    System.err.println("Failed to register event class: " + className);
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registEventClassByManual(String[] modid, Class<?>... clazzes) {
        for (String modids : modid) {
            for (Class<?> clazz : clazzes) {
                    try {
                        boolean hasStatic = false;
                        boolean hasNonStatic = false;

                        for (Method method : clazz.getDeclaredMethods()) {
                            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                                if (Modifier.isStatic(method.getModifiers())) {
                                    hasStatic = true;
                                } else {
                                    hasNonStatic = true;
                                }
                            }
                        }

                        if (hasStatic) {
                            registerObjectIfModIdPresent(modids, clazz);
                        }
                        if (hasNonStatic) {
                            Object instance = clazz.getDeclaredConstructor().newInstance();
                            registerObjectIfModIdPresent(modids, instance);
                        }
                    } catch (Exception e) {
                        logError(clazz, e);
                    }
            }
        }
    }

    private static void logError(Object target, Exception e) {
        System.err.println("Failed to register event class: " + target);
        e.printStackTrace();
    }

    public static void registerObjectIfModIdPresent(String modid, Object handler)
    {
        if (ModChecker.isModPresent(modid)) {
            ClassRegister.registerObjectsis(handler);
        }
    }

    public static void registerObjectsis(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
    }



}
