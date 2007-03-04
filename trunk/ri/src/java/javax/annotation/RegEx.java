package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.meta.Qualifier;
import javax.annotation.meta.QualifierChecker;
import javax.annotation.meta.When;

/** This qualifier is used to denote String values that should be 
 * a Regular expression.
 *
 */
@Documented
@Qualifier
public @interface RegEx {
    When when() default When.ALWAYS;
    ElementType[] applyTo() default {};
	static class Checker implements QualifierChecker<RegEx> {

		public When forConstantValue(RegEx annotation, Object value) {
			if (!(value instanceof String)) return When.MAYBE_NOT;

			try {
				Pattern p = Pattern.compile((String) value);
			} catch (PatternSyntaxException e) {
				return When.MAYBE_NOT;
			}
			return When.ALWAYS;

		}
		
	}
	When when() default When.ALWAYS;
	ElementType[] applyTo() default {};

}