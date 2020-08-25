package com.geldbeutel.rebalancer;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/*
 * Diese Klasse stellt einige einfache Methoden zur Ein- und Ausgabe auf einem
 * Terminal zur Verfügung.
 *
 * Warum diese Klasse VIEL besser ist als Terminal v1.x : - Unmengen von
 * Duplikationen wurden entfernt - Scanner ist "State of the Art" (JDK 1.5),
 * BufferedReader veraltet (JDK 1.1) - Verwendet die Java "Arrays" Bibliothek -
 * Über 200 Zeilen weniger bei selbem Funktionsumfang - Funktioniert mit
 * mehreren Inputs bei Moodle-CodeRunner-Tests
 *
 * @author Tobias Westphal, basierend auf der Arbeit von P. Pepper und Gruppe
 * @version 2.0
 */
public class Terminal {

    public static PrintStream OUT = System.out;
    private static InputStream IN = System.in;
    private static Scanner SCANNER = new Scanner(IN);
    private static final char ARRAY_OPEN = '[', ARRAY_CLOSE = ']', ARRAY_DELIMITER = ',';

    /**
     * Gibt eine Zeichenkette aus.
     *
     * @param <T> der verwendete Datentyp.666
     * @param t das auszugebende Objekt.
     */
    public static <T> void print(T t) {
        if (t.getClass().isArray()) {
            OUT.print(fromArray(t));
        } else {
            OUT.print(norm(t));
        }
    }

    /**
     * Gibt eine Zeichenkette gefolgt von einem Zeilenumbruch aus.
     *
     * @param <T> der verwendete Datentyp.
     * @param t das auszugebende Objekt.
     */
    public static <T> void println(T t) {
        if (t.getClass().isArray()) {
            OUT.println(fromArray(t));
        } else {
            OUT.println(norm(t));
        }
    }

    /**
     * Liest eine Zeichenkette vom Terminal.
     *
     * @return das gelesene Zeichen.
     */
    public static String readString() {
        return read(String.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer Zeichenkette.
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Zeichenkette.
     */
    public static String askString(String prompt) {
        return ask(String.class, prompt);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer Zeichenkette.
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Zeichenkette.
     */
    public static String ask(String prompt) {
        return askString(prompt);
    }

    /**
     * Liest einen Wahrheitswert vom Terminal. Die Zeichenkette "true"
     * (unabh&auml;ngig von Gro&szlig;-/Kleinschreibung) liefert true, alles
     * andere false.
     *
     * @return der eingegebene Wert.
     */
    public static boolean readBoolean() {
        return read(Boolean.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe eines Wahrheitswerts.
     * Die Zeichenkette "true" (unabh&auml;ngig von Gro&szlig;-/Kleinschreibung)
     * liefert true, alles andere false.
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return der eingegebene Wert.
     */
    public static boolean askBoolean(String prompt) {
        return ask(Boolean.class, prompt);
    }

    /**
     * Liest ein Zeichen vom Terminal.
     *
     * @return das gelesene Zeichen.
     */
    public static char readChar() {
        return read(Character.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe eines Zeichens.
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return das eingegebene Zeichen.
     */
    public static char askChar(String prompt) {
        return ask(Character.class, prompt);
    }

    /**
     * Liest eine ganze Zahl (8 Bit) vom Terminal.
     *
     * @return die gelesene Zahl.
     */
    public static byte readByte() {
        return read(Byte.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer ganzen Zahl (8
     * Bit).
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Zahl.
     */
    public static byte askByte(String prompt) {
        return ask(Byte.class, prompt);
    }

    /**
     * Liest eine ganze Zahl (16 Bit) vom Terminal.
     *
     * @return die gelesene Zahl.
     */
    public static short readShort() {
        return read(Short.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer ganzen Zahl (16
     * Bit).
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Zahl.
     */
    public static short askShort(String prompt) {
        return ask(Short.class, prompt);
    }

    /**
     * Liest eine ganze Zahl (32 Bit) vom Terminal.
     *
     * @return die gelesene Zahl.
     */
    public static int readInt() {
        return read(Integer.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer ganzen Zahl (32
     * Bit).
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Zahl.
     */
    public static int askInt(String prompt) {
        return ask(Integer.class, prompt);
    }

    /**
     * Liest eine ganze Zahl (64 Bit) vom Terminal.
     *
     * @return die gelesene Zahl.
     */
    public static long readLong() {
        return read(Long.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer ganzen Zahl (64
     * Bit).
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Zahl.
     */
    public static long askLong(String prompt) {
        return ask(Long.class, prompt);
    }

    /**
     * Liest eine Gleitpunktzahl(32 Bit) vom Terminal.
     *
     * @return die gelesene Zahl.
     */
    public static float readFloat() {
        return read(Float.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer Gleitpunktzahl
     * einfacher Genauigkeit (32 Bit).
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Gleitpunktzahl.
     */
    public static float askFloat(String prompt) {
        return ask(Float.class, prompt);
    }

    /**
     * Liest eine Gleitpunktzahl(64 Bit) vom Terminal.
     *
     * @return die gelesene Zahl.
     */
    public static double readDouble() {
        return read(Double.class);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer Gleitpunktzahl
     * doppelter Genauigkeit (64 Bit).
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die eingegebene Gleitpunktzahl.
     */
    public static double askDouble(String prompt) {
        return ask(Double.class, prompt);
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe eines Integer Arrays
     * im Format: "[1,2,3,...]".
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return das gelesene Array.
     */
    public static int[] askIntArray(String prompt) {
        while (true) {
            try {
                return toIntArray((String[]) parseArray(ask(prompt)));
            } catch (NumberFormatException e) {
                println("Ungueltige Integer-Zahl! (Nochmal eingeben)");
            } catch (ClassCastException e) {
                int[] example = {1, 2, 3, 4, 5, 6};
                println("Ungueltiges Format! Beispiel: \""
                        + Arrays.toString(example)
                        + "\". (Nochmal eingeben) ");
            }
        }
    }

    /**
     * Gibt eine Zeichenkette aus und erwartet die Eingabe einer Integer Matrix
     * im Format: "[[1,2,3],[4,5,6],...]".
     *
     * @param prompt die auszugebende Zeichenkette.
     * @return die gelesene Matrix.
     */
    public static int[][] askIntMatrix(String prompt) {
        while (true) {
            try {
                return toIntMatrix(parseArray(ask(prompt)));
            } catch (NumberFormatException e) {
                println("Ungueltige Integer-Zahl! (Nochmal eingeben)");
            } catch (ClassCastException e) {
                int[][] example = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
                println("Ungueltiges Format! Beispiel: \""
                        + Arrays.deepToString(example)
                        + "\". (Nochmal eingeben) ");
            }
        }
    }

    /**
     * Sets another input stream.
     *
     * @param input the new input stream
     */
    public static void setIn(InputStream input) {
        IN = input;
        SCANNER = new Scanner(IN);
    }

    /**
     * Prints the String and calls read.
     *
     * @param <T> datatype of the return
     * @param c class of desired type (e.g. Integer.class, String.class...)
     * @param prompt the String to print
     * @return user's input
     */
    private static <T> T ask(Class<T> c, String prompt) {
        print(prompt);
        return read(c);
    }

    /**
     * This method deligates to the correct Scanner methode to obtain an input
     * of the desired type. Repeated while it gets wrong inputs. Supresses the
     * "unchecked" compiler warning, because type safety IS guaranteed. (Objects
     * will be auto-unboxed to their primitive counterparts!)
     *
     * @param <T> datatype of the return
     * @param c class of desired type (e.g. Integer.class, String.class...)
     * @return user's input
     */
    @SuppressWarnings("unchecked")
    private static <T> T read(Class<T> c) {
        while (true) {
            T t = null;
            try {
                if (c.equals(Boolean.class)) {
                    Boolean out = SCANNER.nextBoolean();
                    t = (T) out;
                } else if (c.equals(Character.class)) {
                    Character out = SCANNER.next().charAt(0);
                    t = (T) out;
                } else if (c.equals(Byte.class)) {
                    Byte out = SCANNER.nextByte();
                    t = (T) out;
                } else if (c.equals(Short.class)) {
                    Short out = SCANNER.nextShort();
                    t = (T) out;
                } else if (c.equals(Integer.class)) {
                    Integer out = SCANNER.nextInt();
                    t = (T) out;
                } else if (c.equals(Long.class)) {
                    Long out = SCANNER.nextLong();
                    t = (T) out;
                } else if (c.equals(Float.class)) {
                    Float out = SCANNER.nextFloat();
                    t = (T) out;
                } else if (c.equals(Double.class)) {
                    Double out = SCANNER.nextDouble();
                    t = (T) out;
                }
                if (t != null) {
                    SCANNER.nextLine();
                    return t;
                }
                if (c.equals(String.class)) {
                    String out = SCANNER.nextLine();
                    return (T) out;
                }
            } catch (InputMismatchException e) {
                print("Ungueltige " + c.getName() + "-Zahl! (Nochmal eingeben) ");
                SCANNER.nextLine();
                continue;
            } catch (NoSuchElementException e) {
                print("Eine leere Zeile ist nicht erlaubt! (Nochmal eingeben) ");
                SCANNER.nextLine();
                continue;
            } catch (IllegalStateException e) {
                print("Fehler! Der Scanner wurde bereits geschlossen!");
                return null;
            }
            throw new IllegalArgumentException(c.getName() + " is not supported!");
        }
    }

    private static <T> String fromArray(T t) {
        return Arrays.deepToString(Arrays.asList(t).toArray()).replaceAll("^.|.$", "");
    }

    private static int[] fromString(String str, String regex) {
        String[] split = str.split(regex);
        int[] array = new int[split.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(split[i]);
        }
        return array;
    }

    private static <T> String norm(T t) {
        if (t instanceof Float) {
            if (Math.abs((Float) t) < 1e-8F) {
                return Double.toString(0.0);
            }
        }
        if (t instanceof Double) {
            if (Math.abs((Double) t) < 1e-8F) {
                return Double.toString(0.0);
            }
        }
        return t.toString();
    }

    /**
     * Parses a string-array to an int-array.
     *
     * @param s the string array
     * @return the int array
     */
    private static int[] toIntArray(String[] s) {
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i].trim());
        }
        return a;
    }

    /**
     * Parses an object-array that contains string-arrays to an int-matrix.
     *
     //* @param s the string array
     * @return the int amatrix
     */
    private static int[][] toIntMatrix(Object[] o) {
        int[][] a = new int[o.length][];
        for (int i = 0; i < o.length; i++) {
            a[i] = toIntArray((String[]) o[i]);
        }
        return a;
    }

    /**
     * Parses a string to an array.
     *
     * @see #parseArrayRec(String str)
     * @param str the string to parse
     * @return the array
     */
    private static Object[] parseArray(String str) {
        return (Object[]) parseArrayRec(str);
    }

    /**
     * Recursively parses a string to an array. The dimension is arbitrary and
     * defined by the given String. For example: - "[1,2,3]" results in a
     * String[] - "[[1,2,3],[4,5,6]]" results in an Object[][] -
     * "[[[1,2,3],[4,5,6]],[[7,8,9],[10,11,12]]]" results in an Object[][][] -
     * and so on The inner dimension of the array always is of type String[].
     *
     * @param str the string to parse
     * @return the array-object
     */
    private static Object parseArrayRec(String str) {
        str = str.trim();
        if (!str.contains(ARRAY_OPEN + "")) {
            return str.split(ARRAY_DELIMITER + "");
        } else {
            int open = 0;
            int openIndex = -1;
            ArrayList<Object> blocks = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ARRAY_OPEN) {
                    if (openIndex < 0) {
                        openIndex = i;
                    }
                    open++;
                } else if (str.charAt(i) == ARRAY_CLOSE) {
                    open--;
                }
                if (openIndex >= 0 && open == 0) {
                    blocks.add(parseArray(str.substring(openIndex + 1, i)));
                    openIndex = -1;
                }
            }
            Object[] tmp = blocks.toArray();
            if (tmp.length == 1) {
                return tmp[0];
            }
            return tmp;
        }
    }

    //Testing...
    /*public static void main(String[] args) {
        char c = askChar("char: ");
        println(c);
        int i = askInt("int: ");
        println(i);
        double d = askDouble("double: ");
        println(d);
        String s = ask("String: ");
        println(s);
        int[] a = askIntArray("Array: ");
        println(a);
        int[][] aa = askIntMatrix("Matrix: ");
        println(aa);
    }*/
}
