import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnglishNumberParserTask implements Runnable {

    private final List<Integer> data;
    private final String ONE_TO_9 = "one|two|three|four|five|six|seven|eight|nine";
    private final String TEN_TO_19 = "ten|eleven|twelve|thirteen|fourteen|fifteen|sixteen|seventeen|eighteen|nineteen";
    private final String TWO_DIGIT_PREFIX = "twenty|thirty|forty|fifty|sixty|seventy|eighty|ninety";
    private final String ONE_TO_99 = String.format("(%1$s)(?:[- ](%2$s))?|(%3$s)|(%2$s)",
            TWO_DIGIT_PREFIX,
            ONE_TO_9,
            TEN_TO_19);
    private final String ONE_TO_999 = String.format("(%1$s)[ ]hundred(?:[ ](?:and[ ])?(%2$s))?|(%2$s)",
            ONE_TO_9,
            ONE_TO_99);
    private final String ONE_TO_999_999 = String.format("(%1$s)[ ]thousand(?:[ ](%1$s))?|(%1$s)",
            ONE_TO_999);
    private final Map<String, Integer> NUMBERS_IN_ENGLISH = new HashMap<String, Integer>() {{
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
        put("ten", 10);
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
        put("twenty", 20);
        put("thirty", 30);
        put("forty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
    }};

    public EnglishNumberParserTask(List<Integer> data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            parseEnglishNumberAndAddToList();
        }
    }

    private void parseEnglishNumberAndAddToList() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().toLowerCase().trim();
        if (isEnglishNumber(line)) {
            int number = parseEnglishNumber(line);
            synchronized (data) {
                data.add(number);
            }
        }
    }

    private int parseEnglishNumber(String englishNumber) {
        int result = 0;
        int multiplier = 1;
        List<String> splitNumbers = Arrays.asList(englishNumber.split(" "));
        Collections.reverse(splitNumbers);
        for (String splitNumber : splitNumbers) {
            if (splitNumber.equals("hundred")) {
                multiplier = 100;
                continue;
            }
            if (splitNumber.equals("thousand")) {
                multiplier = 1000;
                continue;
            }
            result += NUMBERS_IN_ENGLISH.get(splitNumber) * multiplier;
        }
        return result;
    }

    private boolean isEnglishNumber(String line) {
        Pattern pattern = Pattern.compile("^" + ONE_TO_999_999 + "$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
