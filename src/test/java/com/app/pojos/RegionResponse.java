package com.app.pojos;

import java.util.List;
import java.util.Map;

public class RegionResponse {

    private List<Map<String, String>> links;
    private Integer region_id;
    private String region_name;

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }



    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }
}
