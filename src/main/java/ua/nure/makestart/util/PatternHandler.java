package ua.nure.makestart.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PatternHandler {

    public static final String PASSWORD = "(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])(?=\\S+$)[\\w\\p{Punct}]+";
    public static final String USERNAME = "(?=\\S+$)[\\wА-Яа-яЁёҐЄІЇієїґ\\p{Punct}&&[^@]]+";
    public static final String EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
}
