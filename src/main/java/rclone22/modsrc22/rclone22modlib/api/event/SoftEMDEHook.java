package rclone22.modsrc22.rclone22modlib.api.event;

import java.lang.reflect.Method;

///  Basically like the EMDEHook class and its methods but in a softer getter
/// Not used for generic method getters because this is for conditions using my custom event
public class SoftEMDEHook {

    ///  Different kinds of method for different method types incase one of my events contains methods
    ///  (e.g float method or boolean methods) they have their own perspective method
    /// Has a null NPE checker but still recommended to use the perspective method helpers
    @SuppressWarnings("unchecked")
    public static <T> T softEMDEHookGetterObject(Object entity, String hookMethoded, String methodOfHook, Class<T> returnType) {
        if (entity == null || hookMethoded == null || methodOfHook == null
                || hookMethoded.trim().isEmpty() || methodOfHook.trim().isEmpty()) {
            return getDefault(returnType);
        }

        try {

            Class<?> hookClass = Class.forName("rclone22.modsrc22.rclone22modlib.api.event.EMDEHook");


            Method hook = null;
            for (Method m : hookClass.getMethods()) {
                if (m.getName().equals(hookMethoded)
                        && m.getParameterCount() == 1
                        && m.getParameterTypes()[0].isAssignableFrom(entity.getClass())) {
                    hook = m;
                    break;
                }
            }
            if (hook == null) return getDefault(returnType);


            Object eventObject = hook.invoke(null, entity);
            if (eventObject == null) return getDefault(returnType);


            Method target = eventObject.getClass().getMethod(methodOfHook);
            Object result = target.invoke(eventObject);

            if (result == null) {
                return getDefault(returnType);
            }

            if (returnType == Boolean.class && result instanceof Boolean) {
                return (T) result;
            }
            if (returnType == Float.class && result instanceof Number) {
                return (T) Float.valueOf(((Number) result).floatValue());
            }
            if (returnType == Integer.class && result instanceof Number) {
                return (T) Integer.valueOf(((Number) result).intValue());
            }
            if (returnType == Double.class && result instanceof Number) {
                return (T) Double.valueOf(((Number) result).doubleValue());
            }
            if (returnType == String.class && result instanceof String) {
                return (T) result;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return getDefault(returnType);
        }

        return getDefault(returnType);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getDefault(Class<T> clazz) {
        if (clazz == Boolean.class) return (T) Boolean.FALSE;
        if (clazz == Float.class) return (T) Float.valueOf(0f);
        if (clazz == Integer.class) return (T) Integer.valueOf(0);
        if (clazz == Double.class) return (T) Double.valueOf(0.0);
        if (clazz == String.class) return (T) "";
        return null;
    }

    ///  Setters
    public static boolean softEMDEHookSetterBoolean(Object entity, String hookMethod, String setterMethod, boolean value) {
        return softEMDEHookSetterObject(entity, hookMethod, setterMethod, value, boolean.class);
    }

    public static boolean softEMDEHookSetterFloat(Object entity, String hookMethod, String setterMethod, float value) {
        return softEMDEHookSetterObject(entity, hookMethod, setterMethod, value, float.class);
    }


    public static boolean softEMDEHookSetterInteger(Object entity, String hookMethod, String setterMethod, int value) {
        return softEMDEHookSetterObject(entity, hookMethod, setterMethod, value, int.class);
    }


    public static boolean softEMDEHookSetterDouble(Object entity, String hookMethod, String setterMethod, double value) {
        return softEMDEHookSetterObject(entity, hookMethod, setterMethod, value, double.class);
    }


    public static boolean softEMDEHookSetterString(Object entity, String hookMethod, String setterMethod, String value) {
        return softEMDEHookSetterObject(entity, hookMethod, setterMethod, value, String.class);
    }

    public static boolean softEMDEHookSetterObject(Object entity, String hookMethod, String setterMethod, Object value, Class<?> paramType) {
        if (entity == null || hookMethod == null || setterMethod == null || hookMethod.trim().isEmpty() || setterMethod.trim().isEmpty()) {
            return false;
        }

        try {
            Class<?> hookClass = Class.forName("rclone22.modsrc22.rclone22modlib.api.event.EMDEHook");

            Method hook = null;
            for (Method m : hookClass.getMethods()) {
                if (m.getName().equals(hookMethod)
                        && m.getParameterCount() == 1
                        && m.getParameterTypes()[0].isAssignableFrom(entity.getClass())) {
                    hook = m;
                    break;
                }
            }

            if (hook == null) return false;

            Object eventObject = hook.invoke(null, entity);
            if (eventObject == null) return false;

            Method setter = eventObject.getClass().getMethod(setterMethod, paramType);
            setter.invoke(eventObject, value);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
