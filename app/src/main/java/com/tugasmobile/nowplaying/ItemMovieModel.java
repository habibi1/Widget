package com.tugasmobile.nowplaying;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class ItemMovieModel implements Parcelable {
    String judul;
    String deskripsi;
    String popularitas;
    String rating;
    String tanggalRilis;
    String imageMovie;

    public ItemMovieModel(){

    }

    public ItemMovieModel(JSONObject object){
        try{
            this.judul = object.getString("title");
            this.deskripsi = object.getString("overview");
            this.popularitas = object.getString("popularity");
            this.rating = object.getString("vote_average");
            this.tanggalRilis = object.getString("release_date");
            this.imageMovie = object.getString("poster_path");
        } catch (Exception e){
        }
    }

    protected ItemMovieModel(Parcel in) {
        judul = in.readString();
        deskripsi = in.readString();
        popularitas = in.readString();
        rating = in.readString();
        tanggalRilis = in.readString();
        imageMovie = in.readString();
    }

    public static final Creator<ItemMovieModel> CREATOR = new Creator<ItemMovieModel>() {
        @Override
        public ItemMovieModel createFromParcel(Parcel in) {
            return new ItemMovieModel(in);
        }

        @Override
        public ItemMovieModel[] newArray(int size) {
            return new ItemMovieModel[size];
        }
    };

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPopularitas() {
        return popularitas;
    }

    public void setPopularitas(String popularitas) {
        this.popularitas = popularitas;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTanggalRilis() {
        return tanggalRilis;
    }

    public void setTanggalRilis(String tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }

    public String getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(judul);
        parcel.writeString(deskripsi);
        parcel.writeString(popularitas);
        parcel.writeString(rating);
        parcel.writeString(tanggalRilis);
        parcel.writeString(imageMovie);
    }
}
