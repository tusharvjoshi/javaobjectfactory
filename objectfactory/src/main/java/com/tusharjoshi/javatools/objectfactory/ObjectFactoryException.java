package com.tusharjoshi.javatools.objectfactory;

public class ObjectFactoryException extends RuntimeException {

  private static final String AND_USING_VALUES = " and using values ";

  private static final String USING_CONSTRUCTOR_WITH_TYPES = " using constructor with types ";

  private static final String NO_DEF_CONST_MESSAGE = "Object could not be instantiated for class %s using default constructor, ensure default constructor is present.";

  private static final String GENERIC_MESSAGE = "Object could not be instantiated for class %s";
  
  private static final String NULL_SOURCE_MESSAGE = "No class defination";

  /**
  * 
  */
  private static final long serialVersionUID = 1L;

  public ObjectFactoryException(String message) {
    super(message);
  }

  public ObjectFactoryException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public static ObjectFactoryException defaultConstructorException(Class<?> sourceClass, Throwable throwable) {
    return new ObjectFactoryException(String.format(NO_DEF_CONST_MESSAGE, sourceClass.getName()), throwable);
  }

  public static ObjectFactoryException nullSourceClassException() {
    return new ObjectFactoryException(NULL_SOURCE_MESSAGE);
  }

  public static ObjectFactoryException genericException(Class<?> sourceClass, Throwable throwable) {
    return new ObjectFactoryException(String.format(GENERIC_MESSAGE, sourceClass.getName()), throwable);
  }

  public static ObjectFactoryException typesAndArgsException(Class<?> sourceClass, Class<?>[] types, Object[] args,
      Throwable throwable) {
    return new ObjectFactoryException(generateMessage(sourceClass.getName(), types, args), throwable);
  }

  private static String generateMessage(String name, Class<?>[] types, Object[] args) {
    StringBuilder buffer = new StringBuilder();
    buffer.append(String.format(GENERIC_MESSAGE, name));
    buffer.append(USING_CONSTRUCTOR_WITH_TYPES);
    buffer.append(ParameterJoiner.join(types, ClassMapper::mapToClassName));
    buffer.append(AND_USING_VALUES);
    buffer.append(ParameterJoiner.join(args, Object::toString));
    return buffer.toString();
  }

}
