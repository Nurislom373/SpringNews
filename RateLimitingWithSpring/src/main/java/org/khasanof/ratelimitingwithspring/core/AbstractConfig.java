package org.khasanof.ratelimitingwithspring.core;

import org.khasanof.ratelimitingwithspring.core.json.ReadLimit;

import java.io.IOException;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 11:08 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.core
 */
public interface AbstractConfig {

    List<ReadLimit> readLimitList() throws IOException;

}
