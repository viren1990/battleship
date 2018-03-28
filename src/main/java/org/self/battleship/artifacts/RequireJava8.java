package org.self.battleship.artifacts;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Viren
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface RequireJava8 {
	String value() default "Underlying type , field or method requires java 8+ run time.";
}
