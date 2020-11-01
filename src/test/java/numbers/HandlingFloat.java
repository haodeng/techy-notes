package numbers;

import org.junit.Assert;
import org.junit.Test;

public class HandlingFloat {

    static final float EPSILON = 0.00001f;

    /**
     * Sonar-1244: Floating point numbers should not be tested for equality
     * https://rules.sonarsource.com/java/RSPEC-1244
     */
    @Test
    public void floatCompare()
    {
        float f1 = 0.15f;
        float f2 = 0.45f/3; //实际等于0.14999999

        Assert.assertFalse(f1 == f2);
        Assert.assertFalse(Double.compare(f1,f2) == 0);


        Assert.assertTrue(Math.abs(f1-f2)<EPSILON);
    }

    @Test
    public void divide()
    {
        double d = 24/7;  //结果是3.0
        double d2 = (double)24/7; //结果是正确的3.42857

        System.out.println("d: " + d + ", d2: " + d2);
        Assert.assertFalse(d == d2);
    }

    /**
     * Sonar-2184: Math operands should be cast before assignment
     */
    @Test
    public void overflow()
    {
        long l = Integer.MAX_VALUE * 2; // 结果是溢出的－2
        long l2 = Integer.MAX_VALUE * 2L; //结果是正确的4294967294

        System.out.println("l: " + l + ", l2: " + l2);
        Assert.assertFalse(l == l2);
    }

    /**
     * Sonar-2164: Math should not be performed on floats
     * 尽量用double而不用float，但如果是金融货币的计算，则必须使用如下选择：
     *
     * 选项1， 使用性能较差的BigDecimal。BigDecimal还能精确控制四舍五入或是其他取舍的方式。
     *
     * 选项2， 在预知小数精度的情况下，将浮点运算放大为整数计数，比如货币以"分"而不是以"元"计算。
     */
    @Test
    public void precision()
    {
        float f = 0.45f/3;    //结果是0.14999999
        Assert.assertFalse(f == 0.15f);

        double d1 = 0.45d/3;  //结果是正确的0.15
        Assert.assertTrue(d1 == 0.15d);

        double d2 = 1.03d - 0.42d; //结果是0.6100000000000001
        Assert.assertFalse(d2 == 0.62d);
    }
}
