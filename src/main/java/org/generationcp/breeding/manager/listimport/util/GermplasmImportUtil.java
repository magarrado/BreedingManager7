package org.generationcp.breeding.manager.listimport.util;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.generationcp.breeding.manager.application.Message;
import org.generationcp.commons.vaadin.spring.SimpleResourceBundleMessageSource;
import org.generationcp.commons.vaadin.util.MessageNotifier;

import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;

/**
 * Created with IntelliJ IDEA.
 * User: Efficio.Daniel
 * Date: 8/20/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class GermplasmImportUtil {

    /**
         * Displays an error in window if field's string value is null: "<fieldName> must be specified."
         *
         * @param field - field to check if empty string. If empty, display warning
         * @param messageSource - resource bundle where the error message will be retrieved from
         * @param fieldName - name of the required Field which will appear in error message.
         *                         If this is null, gets the field's caption as field name.
         * @return false if field is null. Else, return true;
         */
        public static boolean validateRequiredStringField(AbstractField field,
                SimpleResourceBundleMessageSource messageSource, String fieldName){

            if (validateRequiredField(field, messageSource, fieldName)){
                if (StringUtils.isEmpty(((String) field.getValue()).trim())){
                    showFieldIsRequiredMessage(messageSource, fieldName != null ? fieldName : field.getCaption());
                    return false;
                }
                return true;
            }

            return false;
        }
    /**
         * Displays an error in window if field's value is null: "<fieldName> must be specified."
         *
         * @param field - field to check if null. If null, displays warning.
         * @param messageSource - resource bundle where the error message will be retrieved from
         * @param fieldName - name of the required Field which will appear in error message.
         *                         If this is null, gets the field's caption as field name.
         * @return false if field is null. Else, return true;
         */
        public static boolean validateRequiredField(AbstractField field,
                SimpleResourceBundleMessageSource messageSource, String fieldName){

            assert field.getCaption() !=null || fieldName != null; //either the field caption or fieldName param must be available

            if (field.getValue() == null){
                showFieldIsRequiredMessage(messageSource, fieldName != null ? fieldName : field.getCaption());
                return false;
            }

            return true;
        }
    /**
         * Displays a error in window: "<fieldName> must be specified."
         *
         * @param field - field to check if null. If null, displays warning.
         * @param messageSource - resource bundle where the error message will be retrieved from
         * @param fieldName - name of the required Field which will appear in error message.
         *                         If this is null, gets the field's caption as field name.
         *
         */
        public static void showFieldIsRequiredMessage(SimpleResourceBundleMessageSource messageSource, String fieldName){

            assert fieldName != null; //either the field caption or fieldName param must be available
            assert messageSource != null;

            MessageNotifier.showError(MessageFormat.format(
                    messageSource.getMessage(Message.ERROR_MUST_BE_SPECIFIED), fieldName), "", Position.MIDDLE_CENTER);

        }
}
