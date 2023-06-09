package com.philips.dmis.swt.ui.toolkit;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface ViewController {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}
