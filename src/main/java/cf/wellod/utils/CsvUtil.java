package cf.wellod.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

public class CsvUtil {

     /* 解析csv文件并转成bean
     * @param file csv文件
     * @param clazz 类
     * @param <T> 泛型
     * @return 泛型bean集合
     */
    public <T> List<T> getCsvData(MultipartFile file, Class<T> clazz) {
        InputStreamReader in;
        CsvToBean<T> csvToBean;
        try {
            in = new InputStreamReader(file.getInputStream(), "gbk");
            //System.out.println("s1");
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            //System.out.println("s2");
            csvToBean = new CsvToBeanBuilder<T>(in)
                    .withSeparator(',')
                    .withQuoteChar('\'')
                    .withMappingStrategy(strategy).build();
            //System.out.println(csvToBean.parse());
            //System.out.println("s3");
            return csvToBean.parse();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
