
    drop table if exists AP_EQUITIES;

    drop table if exists AP_FUTURES;

    drop table if exists CAD_EQUITIES;

    drop table if exists CURRENT;

    drop table if exists EU_EQUITIES;

    drop table if exists EU_FUTURES;

    drop table if exists FX;

    drop table if exists SYMBOLS;

    drop table if exists Symbol_generated_time;

    drop table if exists US_EQUITIES;

    drop table if exists US_FUTURES;

    drop table if exists US_FUTURE_OPTIONS;

    drop table if exists US_INDEX;

    drop table if exists US_OPTIONS;

    drop table if exists hibernate_sequences;

    create table AP_EQUITIES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extensions varchar(255),
        company_name varchar(255),
        currency varchar(255),
        gma_name varchar(255),
        sedol varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table AP_FUTURES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extensions varchar(255),
        company_name varchar(255),
        currency varchar(255),
        currency_multiplier double precision not null,
        expiration_date date,
        expiration_date_short date,
        last_trading_date date,
        multiplier integer not null,
        root varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table CAD_EQUITIES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extensions varchar(255),
        company_name varchar(255),
        currency varchar(255),
        gma_name varchar(255),
        sedol varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table CURRENT (
        id bigint not null auto_increment,
        asset varchar(255),
        generated_time datetime,
        primary key (id)
    );

    create table EU_EQUITIES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extension varchar(255),
        activ_table_name varchar(255),
        activ_table_number integer not null,
        company_name varchar(255),
        currency varchar(255),
        gma_name varchar(255),
        sedol varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table EU_FUTURES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extensions varchar(255),
        company_name varchar(255),
        currency varchar(255),
        currency_multiplier double precision not null,
        expiration_date date,
        expiration_date_short date,
        multiplier integer not null,
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table FX (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        currency varchar(255),
        gma_name varchar(255),
        status integer not null,
        tick_size double precision not null,
        to_currency varchar(255),
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table SYMBOLS (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        primary key (id)
    );

    create table Symbol_generated_time (
        Symbol_id bigint not null,
        generated_time datetime
    );

    create table US_EQUITIES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        company_name varchar(255),
        currency varchar(255),
        gma_name varchar(255),
        sedol varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table US_FUTURES (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extension varchar(255),
        company_name varchar(255),
        currency varchar(255),
        currency_multiplayer double precision not null,
        expiration_date date,
        expiration_date_short date,
        last_trading_date date,
        multiplier integer not null,
        root varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table US_FUTURE_OPTIONS (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        activ_extensions varchar(255),
        callput char(1) not null,
        currency varchar(255),
        currency_multiplier double precision not null,
        expiration_date date,
        multiplier integer not null,
        publisher varchar(255),
        root varchar(255),
        status integer not null,
        strike_price double precision not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table US_INDEX (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        company_name varchar(255),
        currency varchar(255),
        gma_name varchar(255),
        sedol varchar(255),
        status integer not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table US_OPTIONS (
        id bigint not null,
        activ_name varchar(255),
        asset_type varchar(255),
        gma_ticker varchar(255),
        callput char(1) not null,
        currency varchar(255),
        currency_multiplayer double precision not null,
        expiration_date date,
        multiplier integer not null,
        publisher varchar(255),
        root varchar(255),
        status integer not null,
        strkie_price double precision not null,
        tick_size double precision not null,
        underlying_gma_ticker varchar(255),
        primary key (id)
    );

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value integer 
    ) ;
