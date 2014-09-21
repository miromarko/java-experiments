CREATE INDEX indx_gma_ticker_activ_name ON SYMBOLS(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON AP_EQUITIES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON AP_FUTURES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON CAD_EQUITIES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON EU_EQUITIES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON EU_FUTURES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON FX(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON US_EQUITIES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON US_FUTURES(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON US_FUTURE_OPTIONS(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON US_INDEX(gma_ticker,activ_name);
CREATE INDEX indx_gma_ticker_activ_name ON US_OPTIONS(gma_ticker,activ_name);



    CREATE INDEX indx_gma_ticker ON SYMBOLS(gma_ticker);
    CREATE INDEX indx_DTYPE ON SYMBOLS(DTYPE);
    CREATE INDEX indx_asset_type ON SYMBOLS(asset_type);
    CREATE INDEX indx_generated_time ON SYMBOLS(generated_time);
