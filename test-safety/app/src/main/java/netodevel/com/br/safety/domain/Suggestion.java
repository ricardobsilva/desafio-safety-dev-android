package netodevel.com.br.safety.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author NetoDevel
 */

public class Suggestion {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("reason_to_learn")
    @Expose
    private String reasonToLearn;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Suggestion() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param username
     * @param reasonToLearn
     * @param createdAt
     * @param description
     * @param imei
     * @param name
     */
    public Suggestion(Integer id, String name, String description, String imei, String reasonToLearn, String username, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.imei = imei;
        this.reasonToLearn = reasonToLearn;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Suggestion(String name, String description, String imei, String reasonToLearn, String username) {
        this.name = name;
        this.description = description;
        this.imei = imei;
        this.reasonToLearn = reasonToLearn;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getReasonToLearn() {
        return reasonToLearn;
    }

    public void setReasonToLearn(String reasonToLearn) {
        this.reasonToLearn = reasonToLearn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

