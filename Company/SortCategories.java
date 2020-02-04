package Company.Company;

public enum SortCategories {
    NAME(1),
    SURNAME(2),
    SALARY(3),
    AGE(4),
    NO_ACTION(100);

    int number;

    public int getNumber() {
        return number;
    }

    SortCategories(int number) {
        this.number = number;
    }

    static SortCategories sortChoice(int number) {
        SortCategories choise = null;
        for (SortCategories sort : values()) {
            if (sort.getNumber() == number) {
                choise = sort;
                break;
            }
        }
        if (choise == null) {
            choise = NO_ACTION;
        }
        return choise;
    }
}


