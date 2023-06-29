package com.philips.dmis.swt.ui.toolkit.utils;

import com.philips.dmis.swt.ui.toolkit.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class JavaNameDetector {
    private JavaNameDetector() {
    }

    public static String detectDeclaredName(Class<?> clz) {
        return detectDeclaredName(clz, "main");
    }

    public static String detectDeclaredName(Class<?> clz, String sourceRootName) {
        String name = "unknown";
        if (!Constants.DEBUG) {
            return name;
        }
        StackTraceElement stackTraceElement = findStackTraceElement(Thread.currentThread().getStackTrace(), clz);
        if (stackTraceElement == null) {
            return name;
        }
        File projectDir = new File(System.getProperty("user.dir"));
        File srcMainJava = new File(projectDir, "/src/" + sourceRootName + "/java");
        String[] p = stackTraceElement.getClassName().split("\\.");
        p[p.length - 1] = stackTraceElement.getFileName();
        String path = "/" + String.join("/", p);
        File srcFile = new File(srcMainJava, path);
        if (srcFile.exists()) {
            String line = getLine(srcFile, stackTraceElement.getLineNumber() - 1);
            if (line == null) {
                return name;
            }
            int i = line.indexOf("new " + clz.getSimpleName());
            if (i == -1) {
                return name;
            }
            int j = line.indexOf("=");
            if (j == -1 || j >= i) {
                return name;
            }
            line = line.substring(0, j).trim();
            j = line.indexOf(" ");
            name = j == -1 ? line : line.substring(j + 1);
        }
        return name;
    }

    static StackTraceElement findStackTraceElement(StackTraceElement[] stackTraceElements, Class<?> clz) {
        boolean found = false;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (!found && stackTraceElement.getClassName().equals(clz.getName())) {
                found = true;
            } else if (found && !stackTraceElement.getClassName().equals(clz.getName())) {
                return stackTraceElement;
            }
        }
        return null;
    }

    static String getLine(File srcFile, int lineNumber) {
        try {
            return Files.readAllLines(srcFile.toPath()).get(lineNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
