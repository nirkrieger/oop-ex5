package fileprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class FiltersFactory {


//    FiltersFactory (String type,int param1, int param2){
//        chooseFilter(type,param1,param2);
//    }

//    static FileFilter chooseFilter (String filtertype){
//        Pattern pattern1 = Pattern.compile("([a-z])+_+\1");
//        Matcher name = pattern1.matcher(filtertype);
//        if(name.find()){
//            String type = filtertype.substring(name.start(),name.end());
//        }
//        if (name.find()){
//
//        }
//        return new BiggerThanFilter(1);
//    }

    static FileFilter chooseFilter(String filtertype) {/*TODO didn't put \\w becuse _ _ is an error*/
        Pattern patternSize = Pattern.compile("(\\w)+#(\\d)+(NOT)?");
        Pattern patternBetween = Pattern.compile("(\\w+)#(\\d)+#(\\d)+(NOT)?");
        Pattern patternYesNo = Pattern.compile("(\\w+)#(Yes|No)(NOT)?");
        Pattern patternText = Pattern.compile("(\\w+)#\\w+(NOT)?");
        Matcher sizeF = patternSize.matcher(filtertype);
        Matcher betweenF = patternBetween.matcher(filtertype);
        Matcher booleanF = patternYesNo.matcher(filtertype);
        Matcher textF = patternText.matcher(filtertype);
        String name;

        if (sizeF.matches()) {
            if (sizeF.find()) {
                name = sizeF.group(1);
                int threshold = (int)(sizeF.group(2));
            }


        }
        return new BiggerThanFilter(1);

    }
}
