/* ***********************************************************************
 * 83incs CONFIDENTIAL
 * ***********************************************************************
 *
 *  [2017] - [2022] 83incs Ltd.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of 83incs Ltd, IoT83 Ltd, its suppliers (if any), its subsidiaries (if any) and
 * Source Code Licensees (if any).  The intellectual and technical concepts contained
 * herein are proprietary to 83incs Ltd, IoT83 Ltd, its subsidiaries (if any) and
 * Source Code Licensees (if any) and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from 83incs Ltd or IoT83 Ltd.
 ****************************************************************************
 */

package com.incs.spendtracking.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * This class is used for internationalization purpose in which current locale is gotten, and resolving messages from resource bundles for different locales.
 * <br><br>{@link #localeResolver()} This method is used to determine the current locale based on the Accept-Language header.
 * <br><br>{@link #messageSource()} This method is used to resolves messages from resource bundles for different locales.
 */
@Component
public class Localization  {

    /**
     * This method is used to determine the current locale based on the Accept-Language header.
     * @return the current locale
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    /**
     * This method is used to resolves messages from resource bundles for different locales.
     * @return messageSource of type {ResourceBundleMessageSource}
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("locale/messages", "locale/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
