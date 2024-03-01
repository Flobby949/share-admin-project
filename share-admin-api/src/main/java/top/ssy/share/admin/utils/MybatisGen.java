package top.ssy.share.admin.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.io.File;
import java.util.*;

/**
 * @ClassName : MybatusGen  //类名
 * @Description :   //描述
 * @Author : wzngzifan  //作者
 * @Date: 2023-03-10 14:49  //时间
 */
public class MybatisGen {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir").concat(File.separator).concat("src").concat(File.separator).concat("main")
                .concat(File.separator).concat("java").concat(File.separator).concat("com").concat(File.separator).concat("common").concat(File.separator);
        String pathXml = System.getProperty("user.dir").concat(File.separator).concat("src").concat(File.separator).concat("main")
                .concat(File.separator).concat("resources").concat(File.separator).concat("mapper");

        Map<OutputFile, String> outputFileStringMap = new HashMap<>();
        outputFileStringMap.put(OutputFile.controller, path + "controller");
        outputFileStringMap.put(OutputFile.service, path + "service");
        outputFileStringMap.put(OutputFile.serviceImpl, path + "service/impl");
        outputFileStringMap.put(OutputFile.entity,path +  "entity");
        outputFileStringMap.put(OutputFile.mapper,path +  "mapper");
        outputFileStringMap.put(OutputFile.xml, pathXml);
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/db_common", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("ycshang").enableSwagger(); // 设置作者// 开启 swagger 模式

                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("common") // 设置父包模块名
                            .pathInfo(outputFileStringMap);
                         // 设置mapperXml生成路径
                })
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"))) // 设置需要生成的表名
                            .addTablePrefix("t_","sys_")
                            .entityBuilder()
                            .disableSerialVersionUID()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .logicDeleteColumnName("delete_flag")
                            .logicDeletePropertyName("delete_flag")
                            .naming(NamingStrategy.underline_to_camel)
                            .addTableFills(new Column("delete_flag", FieldFill.INSERT))
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("creator", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("updater", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO)
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}

