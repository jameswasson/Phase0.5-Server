package Service;

import java.lang.reflect.Method;

/**
 * Created by James on 1/20/2018.
 */


public class GenericCommand implements Command {
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;

    public GenericCommand(String className, String methodName,
                          Class<?>[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }

    @Override
    public void execute() {
        try {
            Class<?> receiver = Class.forName(_className);
            //receiver = StringProcessor.class;
            Method method = receiver.getMethod(_methodName, _paramTypes);
            method.invoke(null, _paramValues);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}