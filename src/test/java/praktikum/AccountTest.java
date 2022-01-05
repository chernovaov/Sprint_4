package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String clientName;
    private final boolean isNamePrinted;

    public AccountTest(String clientName, boolean isNamePrinted) {
        this.clientName = clientName;
        this.isNamePrinted = isNamePrinted;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {null, false}, // null не разрешен
                {"", false}, // пустая строка не разрешена
                {" ", false}, // строка из 1 пробела не разрешена
                {"   ", false}, // строка пробелов не разрешена
                {"ЖуравлёвАртём ", false}, // 1 пробел в конце строки не разрешен
                {" ЖуравлёвАртём", false}, // 1 пробел в начале строки не разрешен
                {"Журавлёв  Артём", false}, // 2 пробела подряд внутри строки не разрешены
                {"Сен Ли Мин", false}, // более 2 слов не разрешено
                {"ЖуравлёвАртём", false}, // отсутствие пробела не разрешено
                {"Жу-равлёв Ар-тём", false}, // дефис не разрешен
                {"Ж1 А2", false}, // цифры+кириллица не разрешены
                {"1Z 2J", false}, // цифры+латиница не разрешены
                {"11 22", false}, // цифры не разрешены
                {"&!'#$% *()^@", false}, // спецсимволы не разрешены
                {"Журавлёв! Артём", false}, // кириллица+спецсимволы не разрешены
                {"Noize #MC", false}, // латиница+спецсимволы не разрешены
                {"ЖА", false}, // 2 символа, по условию не меньше 3
                {"Журавлёвушкинъ Артём", false}, // 20 символов, по условию не больше 19

                {"Ж Ж", true}, // кириллица, 3 символа
                {"Журавлёвчиков Артём", true}, // кириллица, 19 символов
                {"Журавлёв Артём", true}, // кириллица, 14 символов
                {"Jason Statham", true}, // латиница, 13 символов
                {"Jack Журавлёвчиков", true} // латиница+кириллица, 18 символов
        };
    }


    @Test
    @DisplayName("Проверка имени и фамилии на соответствие требованиям.")
    @Description("1. null. Не печатаем"
            + "\n"+ "2. Пустая строка. Не печатаем"
            + "\n"+ "3. Строка из 1 пробела. Не печатаем"
            + "\n"+ "4. Строка из пробелов. Не печатаем"
            + "\n"+ "5. 1 пробел в конце строки. Не печатаем"
            + "\n"+ "6. 1 пробел в начале строки. Не печатаем"
            + "\n"+ "7. 2 пробела подряд внутри строки. Не печатаем"
            + "\n"+ "8. Более 2 слов. Не печатаем"
            + "\n"+ "9. Пробел отсутствует. Не печатаем"
            + "\n"+ "10. Дефис. Не печатаем"
            + "\n"+ "11. Цифры+кириллица. Не печатаем"
            + "\n"+ "12. Цифры+латиница. Не печатаем"
            + "\n"+ "13. Цифры. Не печатаем"
            + "\n"+ "14. Спецсимволы. Не печатаем"
            + "\n"+ "15. Кириллица+спецсимволы. Не печатаем"
            + "\n"+ "16. Латиница+спецсимволы. Не печатаем"
            + "\n"+ "17. Менее 3 символов. Не печатаем"
            + "\n"+ "18. Более 19 символов. Не печатаем"
            + "\n"+ "19. 3 символа кириллицы. Печатаем"
            + "\n"+ "20. 19 символов кириллицы. Печатаем"
            + "\n"+ "21. 14 символов кириллицы. Печатаем"
            + "\n"+ "22. 13 символов латиницы. Печатаем"
            + "\n"+ "23. 18 символов латиница+кириллица. Печатаем")

    public void checkNameToEmbossTest() {
        Account account = new Account(clientName);
        boolean actual = account.checkNameToEmboss();
        assertEquals(isNamePrinted, actual);
    }
}
