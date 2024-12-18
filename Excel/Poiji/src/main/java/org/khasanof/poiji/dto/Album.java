package org.khasanof.poiji.dto;

import com.poiji.annotation.ExcelCellsJoinedByName;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.poiji.dto
 * @since 12/18/2024 11:29 PM
 */
public class Album {

    @ExcelCellsJoinedByName(expression = "Artist")
    private MultiValuedMap<String, String> artists = new ArrayListValuedHashMap<>();

    @ExcelCellsJoinedByName(expression = "Track[0-9]+")
    private MultiValuedMap<String, String> tracks = new ArrayListValuedHashMap<>();
}
