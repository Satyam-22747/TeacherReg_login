package com.satdroid.teacherreg_login;

public class ImageDataModal {
    private String imageName,ImageUri;

    public ImageDataModal(String imageName, String imageUri) {
        this.imageName = imageName;
        ImageUri = imageUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }
}
