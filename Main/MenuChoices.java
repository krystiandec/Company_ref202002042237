package Company.Main;

public enum MenuChoices {
    WRONG_NUMBER(0),
    ADD_EMPLOYEE(1),
    DISPLAY_EMPLOYEE_LIST(2),
    AVERANGE_OF_AGE_OF_EMPLOYEE(3),
    REMOVE_EMPLOYEE(4),
    DISPLAY_SUM_OF_SALLARY(5),
    DISPLAY_SUM_OF_SALLARY_SEX_DEPENDING(6),
    COLLECT_INFORMATION_ABOUT_PROGRAMRS_DEPENDING_OF_TECHNOLOGY(7),
    SAVE_EMPLOYEELIST(8),
    SORTING(9),
    EXIT(100);


    int number;

    public int getNumber() {
        return number;
    }

    MenuChoices(int number) {
        this.number = number;
    }

    static MenuChoices convertNumberToAction(int importNumber) {
        MenuChoices choice = null;
        for (MenuChoices menus : values()) {
            if (importNumber == menus.getNumber() && importNumber > 0) {
                choice = menus;
                break;
            }
/*            else if (importNumber == CLEAN_CONSOLE.getNumber()) {
                choice = CLEAN_CONSOLE;
                break;
            }*/
        }
        if (choice == null) {
            choice = WRONG_NUMBER;
        }

        return choice;
    }
}
