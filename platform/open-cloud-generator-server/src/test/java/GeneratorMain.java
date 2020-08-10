import com.opencloud.generator.server.service.GenerateConfig;
import com.opencloud.generator.server.service.GeneratorService;


import java.io.File;

public class GeneratorMain {

    public static void main(String[] args) {
        String outputDir = System.getProperty("user.dir") + File.separator + "generator";
        GenerateConfig config = new GenerateConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.0.199:3306/micro-cloud?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        config.setJdbcUserName("root");
        config.setJdbcPassword("root@123");
        config.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        config.setAuthor("szz");
        config.setParentPackage("com.opencloud");
        config.setModuleName("base");
        config.setIncludeTables(new String[]{"base_tentant"});
        config.setTablePrefix(new String[]{"base_"});
        config.setOutputDir(outputDir);
        GeneratorService.execute(config);
    }


}
