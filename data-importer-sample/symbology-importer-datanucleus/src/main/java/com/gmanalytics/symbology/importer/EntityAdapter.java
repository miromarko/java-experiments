/*
 *  Copyright (c) 2005-2013 Green Mountain Analytics.
 *  This software is the confidential and proprietary information 
 *  and shall use it only in accordance with the terms
 *  of the license agreement you entered into with Green Mountain Analytics.
 */
package com.gmanalytics.symbology.importer;

import com.gmanalytics.symbology.persistence.entities.ApEquitie;
import com.gmanalytics.symbology.persistence.entities.ApFuture;
import com.gmanalytics.symbology.persistence.entities.CadEquitie;
import com.gmanalytics.symbology.persistence.entities.EuEquitie;
import com.gmanalytics.symbology.persistence.entities.EuFuture;
import com.gmanalytics.symbology.persistence.entities.Fx;
import com.gmanalytics.symbology.persistence.entities.Symbol;
import com.gmanalytics.symbology.persistence.entities.UsEquitie;
import com.gmanalytics.symbology.persistence.entities.UsFuture;
import com.gmanalytics.symbology.persistence.entities.UsFutureOption;
import com.gmanalytics.symbology.persistence.entities.UsIndex;
import com.gmanalytics.symbology.persistence.entities.UsOption;
import com.gmanalytics.symbology.persistence.util.AssetClass;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Miroslav MARKO <mmarko@gmanalitycs.com>
 */
public class EntityAdapter {

    private static final Logger LOG = Logger.getLogger(EntityAdapter.class.getName());
    private String line;
    private AssetClass asset;
    private Date generatedTimestamp;

    public EntityAdapter(AssetClass asset, String line, Date generatedTime) {
        this.line = line;
        this.asset = asset;
        this.generatedTimestamp = generatedTime;
    }

    public Symbol getEntity() {
        Symbol symbol = null;        
        
        try {
            switch (asset) {
                case US_OPTIONS:
                    symbol = getUsOption();
                    break;
                case US_INDEX:
                    symbol = getUsIndex();
                    break;
                case US_FUTURES:
                    symbol = getUsFuture();
                    break;
                case US_FUTURE_OPTIONS:
                    symbol = getUsFutureOption();
                    break;
                case US_EQUITIES:
                    symbol = getUsEquitie();
                    break;
                case FX:
                    symbol = getFx();
                    break;
                case EU_FUTURES:
                    symbol = getEuFuture();
                    break;
                case EU_EQUITIES:
                    symbol = getEuEquitie();
                    break;
                case CAD_EQUITIES:
                    symbol = getCadEquitie();
                    break;
                case AP_FUTURES:
                    symbol = getApFuture();
                    break;
                case AP_EQUITIES:
                    symbol = getApEquitie();
                    break;
            }
        } catch (Exception e) {
            LOG.severe("!!!!!!!!!! Exception for asset: " + asset);
            LOG.severe(line);
            e.printStackTrace();
        }
        return symbol;
    }

    private Symbol getUsOption() throws ParseException {
        Map<String, String> values = parse(line);
        UsOption usOption = new UsOption();
        usOption.setGenerated_time(generatedTimestamp);
        usOption.setStatus(Integer.parseInt(values.get("STATUS")));
        usOption.setGma_ticker(values.get("GMA_TICKER"));
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (values.get("EXPIRATION_DATE") != null) {
            usOption.setExpiration_date(dateFormat.parse(values.get("EXPIRATION_DATE")));
        }
        usOption.setCallput(values.get("CALLPUT").charAt(0));
        usOption.setStrkie_price(Double.parseDouble(values.get("STRIKE_PRICE")));
        usOption.setCurrency_multiplayer(Double.parseDouble(values.get("CURRENCY_MULTIPLIER")));
        usOption.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        usOption.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        usOption.setPublisher(values.get("PUBLISHER"));
        usOption.setActiv_name(values.get("ACTIV_NAME"));
        usOption.setCurrency(values.get("CURRENCY"));
        usOption.setMultiplier(Integer.parseInt(values.get("MULTIPLIER")));
        usOption.setRoot(values.get("ROOT"));
        return usOption;
    }

    private Symbol getUsIndex() {
        Map<String, String> values = parse(line);
        UsIndex usIndex = new UsIndex();
        usIndex.setGenerated_time(generatedTimestamp);
        usIndex.setGma_ticker(values.get("GMA_TICKER"));
        usIndex.setStatus(Integer.parseInt(values.get("STATUS")));
        usIndex.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        usIndex.setCompany_name(values.get("COMPANY_NAME"));
        usIndex.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        usIndex.setSedol(values.get("SEDOL"));
        usIndex.setGma_name(values.get("GMA_NAME"));
        usIndex.setCurrency(values.get("CURRENCY"));
        usIndex.setActiv_name(values.get("ACTIV_NAME"));
        return usIndex;
    }

    private Symbol getUsFuture() throws ParseException {
        Map<String, String> values = parse(line);
        UsFuture usFutere = new UsFuture();
        usFutere.setGenerated_time(generatedTimestamp);
        usFutere.setStatus(Integer.parseInt(values.get("STATUS")));
        usFutere.setGma_ticker(values.get("GMA_TICKER"));
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (values.get("EXPIRATION_DATE") != null) {
            usFutere.setExpiration_date(dateFormat.parse(values.get("EXPIRATION_DATE")));
        }
        usFutere.setCompany_name(values.get("COMPANY_NAME"));
        DateFormat dateFormatShort = new SimpleDateFormat("yyyyMM");
        if (values.get("EXPIRATION_DATE_SHORT") != null) {
            usFutere.setExpiration_date_short(dateFormatShort.parse(values.get("EXPIRATION_DATE_SHORT")));
        }
        usFutere.setCurrency_multiplayer(Double.parseDouble(values.get("CURRENCY_MULTIPLIER")));
        usFutere.setActiv_extension(values.get("ACTIV_EXTENSIONS"));
        usFutere.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        usFutere.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        if (values.get("LAST_TRADING_DATE") != null) {
            usFutere.setLast_trading_date(dateFormat.parse(values.get("LAST_TRADING_DATE")));
        }
        usFutere.setActiv_name(values.get("ACTIV_NAME"));
        usFutere.setCurrency(values.get("CURRENCY"));
        usFutere.setMultiplier(Integer.parseInt(values.get("MULTIPLIER")));
        usFutere.setRoot(values.get("ROOT"));
        return usFutere;
    }

    private Symbol getUsFutureOption() throws ParseException {
        Map<String, String> values = parse(line);
        UsFutureOption usFutureOption = new UsFutureOption();
        usFutureOption.setGenerated_time(generatedTimestamp);
        usFutureOption.setStatus(Integer.parseInt(values.get("STATUS")));
        usFutureOption.setGma_ticker(values.get("GMA_TICKER"));
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (values.get("EXPIRATION_DATE") != null) {
            usFutureOption.setExpiration_date(dateFormat.parse(values.get("EXPIRATION_DATE")));
        }
        usFutureOption.setCallput(values.get("CALLPUT").charAt(0));
        usFutureOption.setStrike_price(Double.parseDouble(values.get("STRIKE_PRICE")));
        usFutureOption.setCurrency_multiplier(Double.parseDouble(values.get("CURRENCY_MULTIPLIER")));
        usFutureOption.setActiv_extensions(values.get("ACTIV_EXTENSIONS"));
        usFutureOption.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        usFutureOption.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        usFutureOption.setPublisher(values.get("PUBLISHER"));
        usFutureOption.setActiv_name(values.get("ACTIV_NAME"));
        usFutureOption.setCurrency(values.get("CURRENCY"));
        usFutureOption.setMultiplier(Integer.parseInt(values.get("MULTIPLIER")));
        usFutureOption.setRoot(values.get("ROOT"));
        return usFutureOption;
    }

    private Symbol getUsEquitie() {
        Map<String, String> values = parse(line);
        UsEquitie usEquitie = new UsEquitie();
        usEquitie.setGenerated_time(generatedTimestamp);
        usEquitie.setGma_ticker(values.get("GMA_TICKER"));
        usEquitie.setStatus(Integer.parseInt(values.get("STATUS")));
        usEquitie.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        usEquitie.setCompany_name(values.get("COMPANY_NAME"));
        usEquitie.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        usEquitie.setSedol(values.get("SEDOL"));
        usEquitie.setGma_name(values.get("GMA_NAME"));
        usEquitie.setCurrency(values.get("CURRENCY"));
        usEquitie.setActiv_name(values.get("ACTIV_NAME"));
        return usEquitie;
    }

    private Symbol getFx() {
        Map<String, String> values = parse(line);
        Fx fx = new Fx();
        fx.setGenerated_time(generatedTimestamp);
        fx.setTo_currency(values.get("TO_CURRENCY"));
        fx.setGma_ticker(values.get("GMA_TICKER"));
        fx.setStatus(Integer.parseInt(values.get("STATUS")));
        fx.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        fx.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        fx.setGma_name(values.get("GMA_NAME"));
        fx.setCurrency(values.get("CURRENCY"));
        fx.setActiv_name(values.get("ACTIV_NAME"));
        return fx;
    }

    private Symbol getEuFuture() throws ParseException {
        Map<String, String> values = parse(line);
        EuFuture euFuture = new EuFuture();
        euFuture.setGenerated_time(generatedTimestamp);
        euFuture.setCurrency_multiplier(Double.parseDouble(values.get("CURRENCY_MULTIPLIER")));
        euFuture.setActiv_extensions(values.get("ACTIV_EXTENSIONS"));
        euFuture.setGma_ticker(values.get("GMA_TICKER"));
        euFuture.setStatus(Integer.parseInt(values.get("STATUS")));
        euFuture.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (values.get("EXPIRATION_DATE") != null) {
            euFuture.setExpiration_date(dateFormat.parse(values.get("EXPIRATION_DATE")));
        }
        euFuture.setCompany_name(values.get("COMPANY_NAME"));
        euFuture.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        DateFormat dateFormatShort = new SimpleDateFormat("yyyyMM");
        if (values.get("EXPIRATION_DATE_SHORT") != null) {
            euFuture.setExpiration_date_short(dateFormatShort.parse(values.get("EXPIRATION_DATE_SHORT")));
        }
        euFuture.setMultiplier(Integer.parseInt(values.get("MULTIPLIER")));
        euFuture.setCurrency(values.get("CURRENCY"));
        euFuture.setActiv_name(values.get("ACTIV_NAME"));
        return euFuture;
    }

    private Symbol getEuEquitie() {
        Map<String, String> values = parse(line);
        EuEquitie euEquitie = new EuEquitie();
        euEquitie.setGenerated_time(generatedTimestamp);
        euEquitie.setActiv_extension(values.get("ACTIV_EXTENSIONS"));
        euEquitie.setGma_ticker(values.get("GMA_TICKER"));
        euEquitie.setStatus(Integer.parseInt(values.get("STATUS")));
        euEquitie.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        euEquitie.setCompany_name(values.get("COMPANY_NAME"));
        euEquitie.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        euEquitie.setActiv_table_number(Integer.parseInt(values.get("ACTIV_TABLE_NUMBER")));
        euEquitie.setSedol(values.get("SEDOL"));
        euEquitie.setGma_name(values.get("GMA_NAME"));
        euEquitie.setActiv_table_name(values.get("ACTIV_TABLE_NAME"));
        euEquitie.setCurrency(values.get("CURRENCY"));
        euEquitie.setActiv_name(values.get("ACTIV_NAME"));
        return euEquitie;
    }

    private Symbol getCadEquitie() {
        Map<String, String> values = parse(line);
        CadEquitie cadEquitie = new CadEquitie();
        cadEquitie.setGenerated_time(generatedTimestamp);
        cadEquitie.setActiv_extensions(values.get("ACTIV_EXTENSIONS"));
        cadEquitie.setGma_ticker(values.get("GMA_TICKER"));
        cadEquitie.setStatus(Integer.parseInt(values.get("STATUS")));
        cadEquitie.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        cadEquitie.setCompany_name(values.get("COMPANY_NAME"));
        cadEquitie.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        cadEquitie.setSedol(values.get("SEDOL"));
        cadEquitie.setGma_name(values.get("GMA_NAME"));
        cadEquitie.setCurrency(values.get("CURRENCY"));
        cadEquitie.setActiv_name(values.get("ACTIV_NAME"));
        return cadEquitie;
    }

    private Symbol getApFuture() throws ParseException {
        Map<String, String> values = parse(line);
        ApFuture apFuture = new ApFuture();
        apFuture.setGenerated_time(generatedTimestamp);
        apFuture.setStatus(Integer.parseInt(values.get("STATUS")));
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (values.get("EXPIRATION_DATE") != null) {
            apFuture.setExpiration_date(dateFormat.parse(values.get("EXPIRATION_DATE")));
        }
        DateFormat dateFormatShort = new SimpleDateFormat("yyyyMM");
        if (values.get("EXPIRATION_DATE_SHORT") != null) {
            apFuture.setExpiration_date_short(dateFormatShort.parse(values.get("EXPIRATION_DATE_SHORT")));
        }
        apFuture.setRoot(values.get("ROOT"));
        apFuture.setCurrency_multiplier(Double.parseDouble(values.get("CURRENCY_MULTIPLIER")));
        apFuture.setCompany_name(values.get("COMPANY_NAME"));
        apFuture.setMultiplier(Integer.valueOf(values.get("MULTIPLIER")));
        apFuture.setGma_ticker(values.get("GMA_TICKER"));
        apFuture.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        apFuture.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        apFuture.setCurrency(values.get("CURRENCY"));
        apFuture.setActiv_name(values.get("ACTIV_NAME"));
        apFuture.setActiv_extensions(values.get("ACTIV_EXTENSIONS"));
        if (values.get("LAST_TRADING_DATE") != null) {
            apFuture.setLast_trading_date(dateFormat.parse(values.get("LAST_TRADING_DATE")));
        }
        return apFuture;
    }

    private Symbol getApEquitie() {
        Map<String, String> values = parse(line);
        ApEquitie apEquitie = new ApEquitie();
        apEquitie.setGenerated_time(generatedTimestamp);
        apEquitie.setSedol(values.get("SEDOL"));
        apEquitie.setCurrency(values.get("CURRENCY"));
        apEquitie.setGma_name(values.get("GMA_NAME"));
        apEquitie.setStatus(Integer.parseInt(values.get("STATUS")));
        apEquitie.setActiv_extensions(values.get("ACTIV_EXTENSIONS"));
        apEquitie.setCompany_name(values.get("COMPANY_NAME"));
        apEquitie.setGma_ticker(values.get("GMA_TICKER"));
        apEquitie.setUnderlying_gma_ticker(values.get("UNDERLYING_GMA_TICKER"));
        apEquitie.setTick_size(Double.parseDouble(values.get("TICK_SIZE")));
        apEquitie.setActiv_name(values.get("ACTIV_NAME"));
        return apEquitie;
    }

    public Map<String, String> parse(String line) {

        Map<String, String> props = new HashMap<String, String>();
        String[] parts = line.split("\\|");
        for (String part : parts) {
            String[] values = part.split("\\=");
            if (values.length == 2) {
                props.put(values[0], values[1]);
            }
        }
        return props;
    }
}
