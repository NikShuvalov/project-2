package shuvalov.nikita.mobilecommerceapp;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testApplyTaxes(){


        assertEquals(18.82, Product.applyTaxes(17.43), 0);
        assertEquals(69.48, Product.applyTaxes(64.34), 0);
        assertEquals(10.80, Product.applyTaxes(10.00),0);
    }
}