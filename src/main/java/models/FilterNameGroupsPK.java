package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilterNameGroupsPK implements Serializable {
    private FilterValues filterValues;
    private FilterNames filterNames;
}
