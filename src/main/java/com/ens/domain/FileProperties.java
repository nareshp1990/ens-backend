package com.ens.domain;

import java.awt.Dimension;
import lombok.Data;

@Data
public class FileProperties {

    private String name;
    private String extension;
    private long size;
    private String type;
    private Dimension dimension;
    private long videoDuration;

}
