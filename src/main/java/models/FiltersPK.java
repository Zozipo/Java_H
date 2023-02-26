package models;

import lombok.Data;

import java.io.Serializable;
@Data
public class FiltersPK implements Serializable {
private FilterNames filterNames;
private FilterValues filterValues;
private Products products;
}
