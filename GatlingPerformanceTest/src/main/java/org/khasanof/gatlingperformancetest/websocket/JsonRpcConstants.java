package org.khasanof.gatlingperformancetest.websocket;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.websocket
 * @since 8/29/2023 4:07 PM
 */
public abstract class JsonRpcConstants {

    public static final String PAYMENT_MS_GET_TRANS = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:243\\n\\n{\\\"service\\\":\\\"PAYMENTMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"${username}\\\",\\\"data\\\":{\\\"method\\\":\\\"getPayment\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"pageable\\\":{\\\"page\\\":0,\\\"size\\\":10,\\\"sort\\\":null,\\\"orderBy\\\":\\\"DESC\\\",\\\"properties\\\":[\\\"id\\\"]},\\\"criteria\\\":{}}}}\\u0000\"]";
    public static final String PROFILE_MS_CREATE_DEPARTMENT = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:260\\n\\n{\\\"service\\\":\\\"PROFILEMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"${username}\\\",\\\"data\\\":{\\\"id\\\":\\\"1\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"method\\\":\\\"saveDepartment\\\",\\\"params\\\":{\\\"department\\\":{\\\"id\\\":null,\\\"code\\\":\\\"${code}\\\",\\\"nameUz\\\":\\\"${nameUz}\\\",\\\"nameRu\\\":\\\"${nameRu}\\\",\\\"status\\\":\\\"NEW\\\",\\\"employeeIds\\\":null}}}}\\u0000\"]";
    public static final String PROFILE_MS_GET_DEPARTMENTS = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:259\\n\\n{\\\"service\\\":\\\"PROFILEMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"${username}\\\",\\\"data\\\":{\\\"method\\\":\\\"getDepartmentByMerchantInn\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"pageable\\\":{\\\"page\\\":0,\\\"size\\\":10,\\\"sort\\\":null,\\\"orderBy\\\":\\\"DESC\\\",\\\"properties\\\":[\\\"id\\\"]},\\\"criteria\\\":{}}}}\\u0000\"]";
    public static final String PRODUCT_MS_MERCHANT_OFFERS = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:261\\n\\n{\\\"service\\\":\\\"PRODUCTMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"${username}\\\",\\\"data\\\":{\\\"method\\\":\\\"getAllMerchantsProductOffers\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"pageable\\\":{\\\"page\\\":0,\\\"size\\\":10,\\\"sort\\\":null,\\\"orderBy\\\":\\\"DESC\\\",\\\"properties\\\":[\\\"id\\\"]},\\\"criteria\\\":{}}}}\\u0000\"]";
    public static final String PRODUCT_MS_CREATE_CATEGORY = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:237\\n\\n{\\\"service\\\":\\\"PRODUCTMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"${username}\\\",\\\"data\\\":{\\\"method\\\":\\\"createCategory\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"Category\\\":{\\\"id\\\":null,\\\"code\\\":null,\\\"nameUz\\\":\\\"${nameUz}\\\",\\\"nameRu\\\":\\\"${nameRu}\\\",\\\"status\\\":\\\"NEW\\\"}}}}\\u0000\"]";
    public static final String PRODUCT_MS_GET_CATEGORIES = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:244\\n\\n{\\\"service\\\":\\\"PRODUCTMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"${username}\\\",\\\"data\\\":{\\\"method\\\":\\\"getCategory\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"pageable\\\":{\\\"page\\\":0,\\\"size\\\":10,\\\"sort\\\":null,\\\"orderBy\\\":\\\"DESC\\\",\\\"properties\\\":[\\\"id\\\"]},\\\"criteria\\\":{}}}}\\u0000\"]";

    public static String formatter(String var, Map<String, String> stringMap) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(stringMap);
        return stringSubstitutor.replace(var);
    }

    public static String phoneNumber() {
        return "99899".concat(RandomStringUtils.random(7, false, true));
    }

    public static RegisterDTO.PartnerType randomPartner() {
        return Arrays.asList(RegisterDTO.PartnerType.values())
                .get(RandomUtils.nextInt(0, 1));
    }

}
