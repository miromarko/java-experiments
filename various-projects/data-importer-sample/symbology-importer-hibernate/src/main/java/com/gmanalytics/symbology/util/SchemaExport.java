package com.gmanalytics.symbology.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;

import java.io.*;

public class SchemaExport {

    public static void main(String[] args) throws IOException {
        boolean drop = true;
        boolean create = true;
        String outFile = "D:/schema.sql";
        String delimiter = ";";
        String unitName = "SymbologyPU";



        Formatter formatter = FormatStyle.DDL.getFormatter();

        Ejb3Configuration jpaConfiguration = new Ejb3Configuration().configure(unitName, null);
        Configuration hibernateConfiguration = jpaConfiguration.getHibernateConfiguration();

        String[] dropSQL = hibernateConfiguration.generateDropSchemaScript(
                Dialect.getDialect(hibernateConfiguration.getProperties()));

        String[] createSQL = hibernateConfiguration.generateSchemaCreationScript(
                Dialect.getDialect(hibernateConfiguration.getProperties()));

        
        FileWriter writer = null;
        try {
            writer = new FileWriter(outFile);
            if (drop) {
                export(writer, delimiter, formatter, dropSQL);
            }
            if (create) {
                export(writer, delimiter, formatter, createSQL);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static void export(FileWriter writer, String delimiter, Formatter formatter, String[] createSQL) throws IOException {


        for (String string : createSQL) {
            writer.write(formatter.format(string)  + delimiter + "\n");
        }

    }
}