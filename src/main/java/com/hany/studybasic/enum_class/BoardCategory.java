package com.hany.studybasic.enum_class;

public enum BoardCategory {
    FREE, GREETING;

    public static BoardCategory of(String category) {
        if (category.equalsIgnoreCase("free")) return BoardCategory.FREE;
        else if (category.equalsIgnoreCase("greeting")) return BoardCategory.GREETING;
        else return null;
    }
}
