package com.java.spark_sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @Author: Lei
 * @E-mail: 843291011@qq.com
 * @Date: Created in 4:50 下午 2020/3/8
 * @Version: 1.0
 * @Modified By:
 * @Description: sparkSQL操作dataFrame示例
 */
public class Java_SparkSQL02_SQL {
    public static void main(String[] args) {
        // 1.初始化spark配置信息并建立与spark的连接
        SparkConf config = new SparkConf().setMaster("local[*]").setAppName("SparkSQL02_SQL");
        JavaSparkContext sc = new JavaSparkContext(config);


        //SparkSession SparkSession = new SparkSession(sc.sc()); // 方法私有，不能正常创建
        SparkSession session = SparkSession.builder().config(config).getOrCreate();

        //读取数据，构建DataFrame
        Dataset<Row> frame = session.read().json("in/user.json");

        //将DataFrame转成一张表
        frame.createOrReplaceTempView("user");
        session.sql("select * from user").show();

        //展示数据
        frame.show();

        // 释放资源
        session.stop();
    }
}
