package serialPack.collection;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс для корректного парсинга даты
 */
public class LocalDateAdapter extends XmlAdapter<String , LocalDate> {

    protected LocalDateAdapter() {
        super();
    }

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.toString();
    }
}
