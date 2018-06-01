package fileprocessing.filters;

import java.io.FileFilter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class FiltersFactory {


//    private enum MATCHERS  {
//
//        SIZE_FILTERS()
//
//        Matcher matcher;
//
//        MATCHERS(Matcher matcher) {
//            this.matcher = matcher;
//        }
//
//    }



    public static FileFilter chooseFilter(String filtertype) {/*TODO didn't put \\w becuse _ _ is an error*/
        Pattern patternSize = Pattern.compile("(\\w)+#(\\d)+(NOT)?");
        Pattern patternBetween = Pattern.compile("(\\w+)#(\\d)+#(\\d)+(NOT)?");
        Pattern patternYesNo = Pattern.compile("(\\w+)#(Yes|No)(NOT)?");
        Pattern patternText = Pattern.compile("(\\w+)#\\w+(NOT)?");
        Matcher sizeF = patternSize.matcher(filtertype);
        Matcher betweenF = patternBetween.matcher(filtertype);
        Matcher booleanF = patternYesNo.matcher(filtertype);
        Matcher textF = patternText.matcher(filtertype);
        String name;

        if (sizeF.matches()) { /*TODO: if name doesn't fit any throw exeption*/
            name = sizeF.group(1);
            double threshold = Double.parseDouble(sizeF.group(2));
            switch (name) {
                case "greater_than": {
                    return new BiggerThanFilter(threshold);
                }
                case "smaller_Than": {
                    return new SmallerThanFilter(threshold);
                }
            }
        }
        else if (betweenF.matches()) {
            if (sizeF.group(1) == "between") {
                double minimun = Double.parseDouble(sizeF.group(2));/*TODO: if min >max throw EXP*/
                double max = Double.parseDouble(sizeF.group(2));
                return new BetweenFilter(max, minimun);
            }
        }
        else if (booleanF.matches()) {
            name = betweenF.group(1);
            switch (name) {
                case "writable": {
                    return new WritableFilter();
                }
                case "executable": {
                    return new ExecutableFilter();
                }
                case "hidden": {
                    return new HiddenFilter();
                }
            }
        }
        else if (textF.matches()){
            name = textF.group(1);
            String seq = textF.group(2);
            switch (name){
                case "preffix":{
                    return new PrefixFilter(seq);
                }
                case "suffix":{
                    return new SuffixFilter(seq);
                }
                case "contains":{
                    return new ContainsFilter(seq);
                }
            }

        }
        return new BiggerThanFilter(1);

    }
}
