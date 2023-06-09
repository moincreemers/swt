package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.demo.*;
import com.philips.dmis.swt.ui.forview.LoginPage;
import com.philips.dmis.swt.ui.forview.PatientDetails;
import com.philips.dmis.swt.ui.forview.PatientDocuments;
import com.philips.dmis.swt.ui.forview.PatientSearch;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DependencyFinderTest {
    @Test
    public void test() throws Exception {
        List<Page> pages = new ArrayList<>(Arrays.asList(
                new LoginPage(),
                new PatientSearch(),
                new PatientDocuments(),
                new PatientDetails()
        ));
        List<Page> expected = new ArrayList<>(pages);

        // unrelated page
        pages.add(new MainDemoPage());

        DependencyFinder dependencyFinder = new DependencyFinder(LoginPage.class, pages);
        List<Page> actual = dependencyFinder.find();

        Assertions.assertTrue(actual.containsAll(expected));
    }
}
