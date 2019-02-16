package aip.aboutabicycle.coota;

import java.util.Locale;

public class UirlisíTeanga {

    public static String bainSíntíGoCeart(String inchur){
        String aschur = inchur.toLowerCase(Locale.getDefault())
                .replace('á', 'a')
                .replace('é', 'e')
                .replace('í', 'i')
                .replace('ó', 'o')
                .replace('ú', 'u')
                .replace("ḃ", "bh")
                .replace("ċ", "ch")
                .replace("ḋ", "dh")
                .replace("ḟ", "fh")
                .replace("ġ", "gh")
                .replace('ɼ', 'r')
                .replace("ṁ", "mh")
                .replace("ṗ", "ph")
                .replace("ṡ", "sh")
                .replace('ſ', 's')
                .replace("ẛ", "sh")
                .replace("ṫ", "th")
                .replace("n̄", "nn")
                .replace('⁊', '&')
                ;

        return aschur;
    }

    public static String bainSíntíGoLom(String inchur){
        String aschur = inchur.toLowerCase(Locale.getDefault())
                .replace('á', 'a')
                .replace('é', 'e')
                .replace('í', 'i')
                .replace('ó', 'o')
                .replace('ú', 'u')
                .replace('ḃ', 'b')
                .replace('ċ', 'c')
                .replace('ḋ', 'd')
                .replace('ḟ', 'f')
                .replace('ġ', 'g')
                .replace('ɼ', 'r')
                .replace('ṁ', 'm')
                .replace('ṗ', 'p')
                .replace('ṡ', 's')
                .replace('ſ', 's')
                .replace('ẛ', 's')
                .replace('ṫ', 't')
                .replace("n̄", "n")
                .replace('⁊', '&')
                ;

        return aschur;
    }
}
