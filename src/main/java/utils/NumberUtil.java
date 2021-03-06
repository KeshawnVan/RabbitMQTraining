package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * @author keshawn
 * @date 2017/11/8
 */

public final class NumberUtil {

    private static final int SCALE = 2;

    private static final Random RANDOM = new Random();

    private NumberUtil() {

    }

    /**
     * 四舍五入，保留两位小数
     * @param value
     * @return
     */
    public static Double scale2(Double value) {
        return new BigDecimal(value).setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    public static Integer getRandomNum(Integer bound){
        return RANDOM.nextInt(bound);
    }
}
