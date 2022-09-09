package org.khasanof.jpaspecification.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Class that parses generic object data in csv file.
 *
 * @author Khasanov Nurislom
 * @since 1.0
 */
public class CSVParser {

    /**
     *
     * @param fileUrl
     * @param aClass
     * @return
     * @param <T>
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> parseObjectList(String fileUrl, Class<T> aClass) throws IOException, InstantiationException, IllegalAccessException {
        String readLine;
        String classFields;
        String values;

        notNull(fileUrl, "fileUrl is required is must be not null!");
        notNull(aClass, "aClass is required is must be not null!");
        checkFileCSV(fileUrl);

        int count = 1;
        BufferedReader reader = new BufferedReader(new FileReader(fileUrl));
        classFields = reader.readLine();

        List<T> newInstances = new ArrayList<>();
        Map<String, Integer> presentFields = isPresentFields(classFields, aClass);
        while ((readLine = reader.readLine()) != null) {
            values = readLine;
            List<String> tokens = getTokens(values, ",");
            newInstances.add(newInstance(tokens, presentFields, aClass.newInstance()));
        }
        System.out.println("newInstances = " + newInstances);
        return newInstances;
    }

    /**
     *
     * @param file
     * @param aClass
     * @return
     * @param <T>
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> parseObjectList(File file, Class<T> aClass) throws IOException, InstantiationException, IllegalAccessException {
        String readLine;
        String classFields;
        String values;

        notNull(file, "fileUrl is required is must be not null!");
        notNull(aClass, "aClass is required is must be not null!");

        int count = 1;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        classFields = reader.readLine();

        List<T> newInstances = new ArrayList<>();
        Map<String, Integer> presentFields = isPresentFields(classFields, aClass);
        while ((readLine = reader.readLine()) != null) {
            values = readLine;
            List<String> tokens = getTokens(values, ",");
            newInstances.add(newInstance(tokens, presentFields, aClass.newInstance()));
        }

        return newInstances;
    }

    private static <T> T newInstance(List<String> values, Map<String, Integer> keyAndIndexes, T entity) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            for (Map.Entry<String, Integer> stringIntegerEntry : keyAndIndexes.entrySet()) {
                if (declaredField.getName().equals(stringIntegerEntry.getKey())) {
                    declaredField.setAccessible(true);
                    declaredField.set(entity, get(stringIntegerEntry.getValue() - 1, declaredField.getGenericType(), values));
                }
            }
        }
        return entity;
    }

    private static Map<String, Integer> isPresentFields(String classFields, Class<?> aClass) {
        Field[] declaredFields = aClass.getDeclaredFields();
        List<String> tokens = getTokens(classFields, ",");
        Map<String, Integer> keyAndIndexes = new HashMap<>();
        for (Field declaredField : declaredFields) {
            int count = 1;
            for (String token : tokens) {
                System.out.println("declaredField.getName() = " + declaredField.getName());
                System.out.println("token = " + token);
                if (declaredField.getName().equalsIgnoreCase(token)) {
                    keyAndIndexes.put(token, count);
                } else {
                    count++;
                }
            }
        }
        if (keyAndIndexes.isEmpty()) {
            throw new IllegalArgumentException("Class fields not found");
        }
        return keyAndIndexes;
    }

    private static Object get(Integer index, Type type, List<String> values) {
        if (type.equals(String.class)) {
            return values.get(index);
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.valueOf(values.get(index));
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Long.valueOf(values.get(index));
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Double.valueOf(values.get(index));
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return Boolean.valueOf(values.get(index));
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return Short.valueOf(values.get(index));
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return Float.valueOf(values.get(index));
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return Byte.valueOf(values.get(index));
        }
        return values.get(index);
    }

    private static List<String> getTokens(String str, String delim) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, delim);
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

    private static void checkFileCSV(String fileUrl) {
        if (!fileUrl.contains(".csv")) throw new RuntimeException("file type is not csv!");
        File file = new File(fileUrl);
        if (!file.exists() || file.isDirectory()) throw new RuntimeException("fileUrl is doesn't exist");
    }

    private static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Object is null!");
        }
    }
}
