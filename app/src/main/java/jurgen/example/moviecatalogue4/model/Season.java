package jurgen.example.moviecatalogue4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import jurgen.example.moviecatalogue4.BuildConfig;

public class Season {
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("season_number")
    @Expose
    private Integer seasonNumber;

    public String getAirDate() {
        return airDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return BuildConfig.IMAGE_DB_BASE_URL + posterPath;
    }
}
