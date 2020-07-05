import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hw8 {

    //Task1
    public static String longerFive(String str){
        return Pattern.compile("\\s+").splitAsStream(str).filter(s->s.length()>5).map(String::toString).collect(Collectors.joining(" "));
    }

    //Task2
    public static Set<String> uniqueWords(String[][] strs){
        return Arrays.stream(strs).flatMap(Arrays::stream).collect(Collectors.toSet());
    }

    //Task3
    public static int sum100200(){
        return IntStream.range(100,201).filter(x->x%2==0).sum();
    }

    //Task4
    public static int sumLength(String[] strs){
        return Arrays.stream(strs).mapToInt(String::length).sum();
    }

    //Task5
    public static List<String> firstAlphabet(String[] strs){
        return Arrays.stream(strs).sorted().limit(3).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        //Task1
        String str = "Некоторые специалисты Google приняли участие в событиях Арабской весны, выступая за «демократические ценности и защиту прав человека». Так, директор по маркетингу Google на Ближнем Востоке и в Северной Африке Ваиль Гоним выступал за свержение режима Хосни Мубарака в Египте во время беспорядков в стране в 2011 году. Он создал страницу в социальной сети Facebook, где осуждалось насилие над гражданами Египта со стороны правящей власти и происходила координация действий демонстрантов. Гоним охарактеризовал египетское протестное движение как «Facebook revolution» и отметил, что интернет играл в ней чрезвычайно важную роль[33]."+"Кроме того, вскоре после событий Арабской весны представители Google заявили, что они работают над облегчением доступа к информации и продуктам компании на Ближнем Востоке и в Северной Африке. В этой связи целью американской компании является обеспечение доступа к ещё большему числу своих продуктов на арабском языке. За 2011 год компания запустила версию Google Voice и Google+ на арабском и представила в Art Project два музея, расположенных в Катаре (Музей исламского искусства и Арабский музей современного искусства Матхаф), а в 2012 году пригласила президента Туниса выступить посредством сервиса видеовещания Google+ Hangout On Air[34]. У компании Google есть также серьёзные претензии к руководству Китая в связи с попытками ограничить доступ китайских граждан к информации в интернете.";
        String str2 = "AAAAAAAAAAAA BBBBBBBBBBBBB CC TTT AA@ ERF @@@@@@@@@@@@@";
        System.out.println(longerFive(str));
        System.out.println(longerFive(str2));


        //Task2
        String[][] str3 = new String[][]{
            {"A", "B", "C", "D", "E" },
            {"A", "B", "C", "D", "E" },
            {"A", "B", "C", "D", "E" },
            {"A", "B", "C", "F", "E" },
            {"A", "T", "C", "D", "H" }
        };

        System.out.println(uniqueWords(str3));

        //Task3
        System.out.println(sum100200());

        //Task4
        String[] str5 = {"A","BB","CCC","FFFF"};

        System.out.println(sumLength(str5));

        //Task5
        String[] str6 = {"DDDD","F","GGG","AAAA","YYYYY","BBBBB","DL:ASD:"};

        System.out.println(firstAlphabet(str6));

    }
}
