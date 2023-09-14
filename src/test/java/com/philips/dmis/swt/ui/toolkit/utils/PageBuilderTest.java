package com.philips.dmis.swt.ui.toolkit.utils;

import com.philips.dmis.swt.ui.toolkit.widgets.WidgetAppearance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageBuilderTest {
    @Test
    public void test() throws Exception {
        PageBuilderTestPage p = new PageBuilderTestPage();
        PageBuilder.getInstance().loadFromXml(p);

        Assertions.assertEquals(2, p.getWidgets().size());

        Assertions.assertNotNull(p.label);
        Assertions.assertNotNull(p.panel);
        Assertions.assertNotNull(p.buttonInsidePanel);

        Assertions.assertTrue(p.panel.getAppearance().contains(WidgetAppearance.ROUNDED_CORNERS));
    }

    @Test
    public void testReflection() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        class A {
            List<WidgetAppearance> list = new ArrayList<>();

            public void setArray(WidgetAppearance... values) {
                list.addAll(Arrays.asList(values));
            }
        }

        A a = new A();
        Method m = a.getClass().getMethod("setArray", WidgetAppearance[].class);
        Object values = new WidgetAppearance[]{WidgetAppearance.BORDERED};
        m.invoke(a, values);

    }

    @Test
    public void testCreateArray() throws InstantiationException, IllegalAccessException {
        Class<?> type = WidgetAppearance.class;

        Object arr1 = java.lang.reflect.Array.newInstance(type, 2);
        WidgetAppearance[] arr2 = new WidgetAppearance[2];

        Assertions.assertEquals(arr2.getClass(), arr1.getClass());



    }
}
