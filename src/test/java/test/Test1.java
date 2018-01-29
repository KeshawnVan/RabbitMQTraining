package test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author keshawn
 * @date 2018/1/29
 */
public class Test1 {

    @Test
    public void testSorted(){
        ArrayList<String> strings = Lists.newArrayList("ps123213", "ps123211", "cs123212");
        List<String> sortedOkFileName = strings.stream().sorted(Comparator.comparing(fileName -> fileName.substring(2))).collect(toList());
        System.out.println(sortedOkFileName);
        System.out.println("ps123213".substring(2));
    }
}
