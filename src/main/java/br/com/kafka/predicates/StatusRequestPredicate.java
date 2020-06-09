package br.com.kafka.predicates;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StatusRequestPredicate implements Predicate<Integer>, Matcher {

    @Override
    public boolean test(Integer integer) {
        return Pattern.compile("^[2][0-9][0-9]").asPredicate().test(integer.toString());
    }

    @Override
    public boolean matches(Object o) {
        return Pattern.compile("^[2][0-9][0-9]").asPredicate().test(o.toString());
    }

    @Override
    public void describeMismatch(Object o, Description description) {
        description.appendText("found " + o);
    }

    @Override
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }

    @Override
    public void describeTo(Description description) {
        description.appendText("between 200 and 299");
    }
}
