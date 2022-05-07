package com.lireddit.example.configuration

import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.session.web.http.CookieSerializer
import org.springframework.session.web.http.DefaultCookieSerializer

class CookieConfig {

    @Bean
    fun cookieSerializer(): CookieSerializer? {

        val serializer = DefaultCookieSerializer()
        serializer.setCookieName("qid")
        serializer.setSameSite("none")
        serializer.setCookieMaxAge(1000 * 60 * 60 * 24)
        serializer.setUseHttpOnlyCookie(false)
        serializer.setUseSecureCookie(true)

        return serializer
    }


}