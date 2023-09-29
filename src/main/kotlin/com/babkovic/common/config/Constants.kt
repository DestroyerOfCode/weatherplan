package com.babkovic.common.config

class Constants {
    companion object {
        private const val SCHEMA = "https"
        private const val SUBDOMAIN = "api"
        private const val SECOND_LEVEL_DOMAIN = "openweathermap"
        private const val TOP_DOMAIN = "org"
        private const val DOMAIN = "$SUBDOMAIN.$SECOND_LEVEL_DOMAIN.$TOP_DOMAIN"
        private const val VERSION = "3.0"
        private const val PATH = "data/${VERSION}/onecall"
        const val OPEN_WEATHER_URL = "$SCHEMA://$DOMAIN/$PATH"
        const val APP_ID = "appid"
    }


}