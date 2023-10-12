import java.util.*;

public class Main {
    public static Map<Character, Integer> map = new HashMap<>(); //мап для перевода из римских в арабские
    static
    {
        map.put('I', 1);
        map.put('V',  5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] s = str.split(" ");
        try {
            if(s.length != 3) {
                throw new Exception();
            }
            if (main.isInt(s)) {
                System.out.println(main.arabicRes(s));
            } else {
                System.out.println(main.romanRes(s));
            }
        }
        catch (Exception e) {
            System.out.println("oops");
        }
    }
    public int arabicRes(String[] s) throws Exception {
        int res = 0;
        int s1 = Integer.parseInt(s[0]);
        int s2 = Integer.parseInt(s[2]);

        res = switch (s[1]) {
            case "+" -> s1 + s2;
            case "*" -> s1 * s2;
            case "/" -> s1 / s2;
            case "-" -> s1 - s2;
            default -> throw new Exception();
        };
        return res;
    }
    public String romanRes(String[] s) throws Exception {
        int res = 0;
        int s1 = romanToInt(s[0]);
        int s2 = romanToInt(s[2]);

        res = switch (s[1]) {
            case "+" -> s1 + s2;
            case "*" -> s1 * s2;
            case "/" -> s1 / s2;
            default -> throw new Exception();
        };
        return intToRoman(res);
    }

    public boolean isInt(String[] s) {

        try {
            Integer.parseInt(s[0]);
            Integer.parseInt(s[2]);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static int romanToInt(String s) //функци для перевода из римских в арабские
    {
        int res = 0; //результат
        int prev = 0; //предыдущее число
        for (int i = s.length() - 1; i >= 0; i--) //обратный цикл
        {
            int cur = map.get(s.charAt(i)); //нынешнее число
            if (cur < prev) //если оно меньше предыдущего, то из результата вычитаем текущее число
            {
                res -= cur;
            } else res += cur; //если больше или равно приблавляем
            prev = cur;
        }
        return res;
    }

    public static String intToRoman(int s) //функция для перевода из арабских в римские
    {
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100}; //беру 100 потому что макс результат Х * Х
        String[] rom = {"I", "IV", "V", "IX", "X", "XL", "L", "LM", "M"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = values.length - 1; i >= 0 && s > 0; i--) {
            while (s >= values[i]) {
                s -= values[i];
                stringBuilder.append(rom[i]);
            }
        }
        return stringBuilder.toString();
    }
}