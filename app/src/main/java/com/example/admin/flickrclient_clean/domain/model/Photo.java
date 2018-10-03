
package com.example.admin.flickrclient_clean.domain.model;

import org.json.JSONObject;

public class Photo{
    private String mHeightS;
    private String mId;
    private Long mIsfamily;
    private Long mIsfriend;
    private Long mIspublic;
    private String mTitle;
    private String mUrlS;
    private String mWidthS;

    public Photo() {
    }

    public Photo(String mId, String mTitle, Long mIspublic, Long mIsfriend, Long mIsfamily, String mUrlS, String mHeightS, String mWidthS) {
        this.mHeightS = mHeightS;
        this.mId = mId;
        this.mIsfamily = mIsfamily;
        this.mIsfriend = mIsfriend;
        this.mIspublic = mIspublic;
        this.mTitle = mTitle;
        this.mUrlS = mUrlS;
        this.mWidthS = mWidthS;
    }

    public Photo(JSONObject jsonObject){
        mHeightS = jsonObject.optString("height_s");
        mId = jsonObject.optString("id");
        mIsfamily = jsonObject.optLong("isfamily");
        mIsfriend = jsonObject.optLong("isfriend");
        mIspublic = jsonObject.optLong("ispublic");
        mTitle = jsonObject.optString("title");
        mUrlS = jsonObject.optString("url_s");
        mWidthS = jsonObject.optString("width_s");
    }

    public String getHeightS() {
        return mHeightS;
    }

    public String getId() {
        return mId;
    }

    public Long getIsfamily() {
        return mIsfamily;
    }

    public Long getIsfriend() {
        return mIsfriend;
    }

    public Long getIspublic() {
        return mIspublic;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrlS() {
        return mUrlS;
    }

    public String getWidthS() {
        return mWidthS;
    }


    public boolean equals(Object obj){
        if(obj instanceof Photo){
            Photo photo2 = (Photo) obj;
            if (this.mId.equals(photo2.getId())){
                if (this.mTitle.equals(photo2.getTitle())) return true;
            }else return false;
        }else return false;

        return false;
    }



}
