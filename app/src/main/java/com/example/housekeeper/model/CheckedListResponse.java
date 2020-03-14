
package com.example.housekeeper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckedListResponse {

    @SerializedName("checkListList")
    @Expose
    private List<ModelChecklist> checklists = null;

    /**
     * No args constructor for use in serialization
     */
    public CheckedListResponse() {
    }

    /**
     * @param checklists
     */
    public CheckedListResponse(List<ModelChecklist> checklists) {
        super();
        this.checklists = checklists;
    }

    public List<ModelChecklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<ModelChecklist> checklists) {
        this.checklists = checklists;
    }
}
