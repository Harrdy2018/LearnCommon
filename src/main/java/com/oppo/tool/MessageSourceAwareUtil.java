package com.oppo.tool;

import com.oppo.model.NationalLanguage;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Locale;

public class MessageSourceAwareUtil implements MessageSourceAware {
    @Setter
    private String code;

    @Setter
    private MessageSource messageSource;

    public NationalLanguage readLocaleSpecificMessage() {
        NationalLanguage nationalLanguage = new NationalLanguage();
        /**
         * 占位符方式
         * msg=hello,{0}! today is {1} in English
         * this.messageSource.getMessage("msg", new Object[]{"oppo",new Date()}, Locale.US)
         */
        nationalLanguage.setMessageCN(messageSource.getMessage(this.code, null, Locale.SIMPLIFIED_CHINESE));
        nationalLanguage.setMessageUS(messageSource.getMessage(this.code, null, Locale.US));
        return nationalLanguage;
    }
}
