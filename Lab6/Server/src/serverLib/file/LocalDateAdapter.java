package serverLib.file;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * Класс для корректного парсинга даты
 */
public class LocalDateAdapter extends XmlAdapter<String , LocalDateTime> {

    protected LocalDateAdapter() {
        super();
    }

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s);
    }

    @Override
    public String marshal(LocalDateTime localDate) throws Exception {
        return localDate.toString();
    }
}
